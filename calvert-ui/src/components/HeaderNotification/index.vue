<template>
  <div>
    <router-link to="/notice/readNotice">
    <el-badge :max="10" :hidden="unreadNoticeCount === 0" :value="unreadNoticeCount" class="item">
        <svg-icon class-name="notification" icon-class="notification" />
    </el-badge>
    </router-link>
  </div>
</template>

<script>
import {listNotice} from "@/api/system/notice";

export default {
  name: 'Notification',
  data() {
    return {
      noticeCount: 0,
      unreadNoticeCount: 0,
    }
  },
  mounted() {
    listNotice(this.queryParams).then(response => {
      this.noticeCount = response.rows.length;
      for (let i = 0; i < this.noticeCount; i++) {
        if (response.rows[i].readNotice === "0" && response.rows[i].status === "0") {
          this.unreadNoticeCount++;
        }
      }
      this.$children[0].$children[0].$forceUpdate();
    });
  }
}
</script>

<style>
.el-badge__content {
  top: 15px !important;
}

</style>
