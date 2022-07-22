<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="公告标题" prop="noticeTitle">
        <el-input
          v-model="queryParams.noticeTitle"
          placeholder="请输入公告标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="操作人员" prop="createBy">
        <el-input
          v-model="queryParams.createBy"
          placeholder="请输入操作人员"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="类型" prop="noticeType">
        <el-select v-model="queryParams.noticeType" placeholder="公告类型" clearable>
          <el-option
            v-for="dict in dict.type.sys_notice_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-view"
          size="mini"
          :disabled="multiple"
          @click = "handleUpdateNoticeToRead"
        >标记已读</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="noticeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="noticeId" width="150" />
      <el-table-column
        label="公告标题"
        align="center"
        prop="noticeTitle"
        :show-overflow-tooltip="true"
      />
      <el-table-column label="公告类型" align="center" prop="noticeType" width="150">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_notice_type" :value="scope.row.noticeType"/>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="readNotice" width="150">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_notice_read_status"  :value="isReadFormat(scope)"/>
        </template>
      </el-table-column>
      <el-table-column label="创建者" align="center" prop="createBy" width="150" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="150">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="openDetailDialog(scope.row.noticeId, scope.row)"
          >查看</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!--通知公告详情 -->
    <el-dialog :title="form.noticeTitle" :visible.sync="openDetail" width="800px" append-to-body>
      <div style="margin-top:-20px;margin-bottom:10px;">
        <el-tag size="mini" effect="dark" type="warning" v-if="form.noticeType==1">通知</el-tag>
        <el-tag size="mini" effect="dark" type="warning" v-else-if="form.noticeType==2">公告</el-tag>
<!--        <el-tag size="mini" effect="dark" v-else>信息</el-tag>-->
        <span style="margin-left:20px;">{{form.createTime}}</span>
      </div>
      <div v-loading="loadingDetail" class="content">
        <div v-html="form.noticeContent"></div>
      </div>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="closeDetail"> 关 闭 </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  getNotice,
  listNoticeByUser,
  getNoticeByUser,
  updateNoticesToRead,
  insertNoticeInfoToUserRead,
  updateUserIdToUserRead, updateNotice, updateNoticeToRead
} from "@/api/system/notice";

export default {
  name: "Notice",
  dicts: ['sys_notice_read_status', 'sys_notice_type'],
  data() {
    return {
      //选择公告信息的Key
      selectedRowKeys: [],
      //选择公告信息
      selectedRows: [],
      //详情加载
      loadingDetail: false,
      // 打开详情
      openDetail: false,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 公告表格数据
      noticeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        noticeTitle: undefined,
        createBy: undefined,
        status: undefined
      },
      // 表单参数
      form: {},
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询公告列表 */
    getList() {
      this.loading = true;
      listNoticeByUser(this.queryParams).then(response => {
        for (let i = 0; i < response.rows.length; i++) {
          if (response.rows[i].status === "0") {
            this.$set(this.noticeList, i, response.rows[i]);
          }
        }
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        noticeId: undefined,
        noticeTitle: undefined,
        noticeType: undefined,
        noticeContent: undefined,
        isRead : "0",
        readNotice : "0",
        status: "0"
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    //是否已读
    isReadFormat(data){
      if (data.row.isRead !== null && data.row.isRead !== '') {
        return data.row.isRead;
      }else{
        return 0;
      }
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.noticeId)
      this.single = selection.length!=1
      this.multiple = !selection.length
    },
    /** 批量已读按钮操作 */
    handleUpdateNoticeToRead(row){
      const noticeIds = row.id || this.ids;
      this.$modal.confirm('确认标记所选数据?').then(function () {
        return updateNoticesToRead(noticeIds);
      }).then(() => {
          this.$modal.msgSuccess("标记成功");
          this.getList();
        });
    },
    /** 阅读按钮操作 */
    openDetailDialog(id,row) {
      this.openDetail = true;
      this.loadingDetail = true;
      getNotice(id).then(response => {
        this.form = response.data;
        this.openDetail = true;
        this.loadingDetail = false;
        this.loading = true;
        // console.log(row.isRead)
        if (row.isRead == null && row.readNotice == null) {
          insertNoticeInfoToUserRead(this.form).then(() => {
            row.isRead = "1";
            // console.log(row.isRead)
            // this.getList();
            this.$children[0].$children[0].$forceUpdate();
            this.loading = false;
          })

        }
      });
      // console.log(row.isRead);
    },
    // 取消按钮
    closeDetail() {
      this.titleDetail = "详情";
      this.openDetail = false;
      this.reset();
    },
  }
};
</script>

<style>


</style>
