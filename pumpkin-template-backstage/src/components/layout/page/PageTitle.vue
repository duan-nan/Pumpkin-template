<template>
  <div class="page-title-container">
    <div class="page-title">{{ this.$route.name }}</div>
  </div>
</template>

<script>
export default {
  name: "PageTitle",

}
</script>

<style lang="scss" scoped>
.page-title-container {
  position: absolute;
  top: 0;
  left: 0;

  .page-title {
    user-select: none;
    display: inline;
    font-weight: bolder;
    color: white;
    font-size: 17px;
    background: #40a9ff;
    padding: 5px 5px 5px 0;
    border-top-right-radius: 10px;
    border-bottom-right-radius: 10px;

    &:before {
      content: "|";
      color: #0a8ef1;
      border-radius: 20px;
      margin-right: 5px;
      font-size: 25px;

    }

  }
}
</style>
