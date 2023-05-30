<template>
  <a-row :gutter="26" class="main-container">
    <a-col v-for="item in projectInfo" :key="item.title" :span="5">
      <a-card class="card-container">
        <!-- 图片内容 -->
        <div class="picture-item">
          <img :src="item.picture" alt="....">
        </div>
        <!-- 标题内容 -->
        <div class="content-box">
          <span class="content-title">{{ item.title }}</span>
          <span class="content-desc">{{ item.desc }}</span>
        </div>
      </a-card>
    </a-col>
  </a-row>
</template>

<script>

import {projectInfo} from "@/views/welcome/config/ProjectInfo";

export default {
  name: "ProjectInfo",
  data() {
    return {
      projectInfo: projectInfo
    }
  }
}
</script>

<style lang="scss" scoped>

.main-container {
  display: flex;
  justify-content: center;
  align-items: center;

  .card-container {
    width: 100%;
    display: flex;
    flex-direction: column;
    border-radius: 10px;


    .picture-item {
      margin: 0 auto 20px;
      width: 150px;
      height: 150px;
      overflow: hidden;

      img{
        width: 100%;
        height: auto;
      }
    }

    .content-box{
      display: flex;
      flex-direction: column;
      align-items: center;
      text-align: center;

      .content-title{
        color: #74bdf4;
        font-size: 17px;
        font-weight: bolder;
      }

      .content-desc{
        margin-top: 20px;
      }
    }
  }

}

</style>
