<template>
  <a-card class="main-container animate__animated animate__fadeIn" style="border-radius: 5px">
    <!-- 页面标题 -->
    <page-title/>
    <!-- 图标容器 -->
    <div class="icon-container">
      <a-row type="flex">
        <a-col v-for="icon in iconList" :key="icon" :span="3">
          <div class="icon-item" @click="copyIcon(icon)">
            <a-icon class="icon-icon" :type="icon"/>
            <span class="icon-span">
              {{ icon }}
            </span>
          </div>
        </a-col>
      </a-row>
    </div>
  </a-card>
</template>

<script>
import PageTitle from "@/components/layout/page/PageTitle";
import IconList from "@/assets/json/IconList.json";

export default {
  name: "Icon",
  components: {
    PageTitle,
  },
  data() {
    return {
      iconList: IconList,
    }
  },
  methods: {
    copyIcon(icon) {
      const message = `<a-icon type="${icon}" />`;
      this.$message.success(`${message}`);
    }
  }
}
</script>

<style lang="scss" scoped>

.icon-container {
  width: 100%;
  margin-top: 20px;


  .icon-item {
    display: flex;
    flex-direction: column;
    font-size: 30px;
    padding: 20px 0;
    margin: 0 auto 20px;
    text-align: center;
    cursor: pointer;
    transition: 0.5s;
    border-radius: 5px;


    &:hover {
      background-color: #1890ff;

      .icon-icon {
        color: white;
      }

      .icon-span {
        color: white;
      }
    }

    .icon-span {
      font-size: 13px;
    }
  }
}

</style>
