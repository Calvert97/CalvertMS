<template>
  <div>
    <router-link to="/notice/readNotice">
      <svg-icon class-name="notification" icon-class="notification" />
    </router-link>
  </div>
</template>

<script>
export default {
  name: 'Notification',
  data() {
    return {
      // to="/user/profile"
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>
