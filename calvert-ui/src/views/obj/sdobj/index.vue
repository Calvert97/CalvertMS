<template>
  <div>
    <el-form ref="elForm" :model="formData" :rules="rules" size="medium" label-width="100px"
             label-position="top">
      <el-form-item label="姓名" prop="field101">
        <el-input v-model="formData.user" placeholder="请输入姓名" clearable :style="{width: '100%'}">
        </el-input>
      </el-form-item>
      <el-form-item label="年龄" prop="age">
        <el-input v-model="formData.age" placeholder="请输入年龄" clearable :style="{width: '100%'}">
        </el-input>
      </el-form-item>
      <el-form-item size="large">
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
import {addSduser} from "@/api/obj/sduser";

export default {
  name: "Sduser",
  components: {},
  props: [],
  data() {
    return {
      formData: {},
      rules: {
        user: [{
          required: true,
          message: '请输入姓名',
          trigger: 'blur'
        }],
        age: [{
          required: true,
          message: '请输入年龄',
          trigger: 'blur'
        }],
      },
    }
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {},
  methods: {
    submitForm() {
      this.$refs['elForm'].validate(valid => {
        if (valid) {
            addSduser(this.formData).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
        }
      })
    },
    resetForm() {
      this.$refs['elForm'].resetFields()
    },
  }
}

</script>
<style>
</style>
