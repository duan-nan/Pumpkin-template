<template>
  <a-card class="main-container animate__animated animate__fadeIn" style="border-radius: 5px">
    <!-- 页面标题 -->
    <page-title/>
    <div class="icon-container">
      <a-result title="待开发页面...">
        <template #icon>
          <a-icon type="smile" theme="twoTone" />
        </template>
      </a-result>
    </div>
  </a-card>
</template>

<script>
import PageTitle from "@/components/layout/page/PageTitle";

export default {
  name: "ColorIcon",
  components: {
    PageTitle,
  },
}
</script>

<style lang="scss" scoped>
.icon-container {
  margin-top: 20px;
}
</style>
