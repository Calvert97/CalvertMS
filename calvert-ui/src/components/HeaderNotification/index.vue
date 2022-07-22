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
import {listNotice, listNoticeByUser} from "@/api/system/notice";

export default {
  name: 'Notification',
  data() {
    return {
      noticeCount: 0,
      unreadNoticeCount: 0,
    }
  },
  mounted() {
    listNoticeByUser(this.queryParams).then(response => {
      this.noticeCount = response.rows.length;
      console.log(response.rows[0].isRead);
      for (let i = 0; i < this.noticeCount; i++) {
        if (response.rows[i].isRead === null && response.rows[i].status === "0") {
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
