<template>
  <div class="about-container">
    <!-- 头像部分 -->
    <div class="avatar-container">
      <img class="avatar" :src="Logo">
    </div>
    <!-- 主要内容部分 -->
    <div class="main-container">
      <!-- 标题 -->
      <div class="title-box">Pumpkin模板后台管理系统</div>
      <!-- 标签 -->
      <div class="tag-box">
        <span v-for="tag in tagList">
          <a-badge style="margin-right: -10px" :color="tag.tagColor"/>
          <a-tag style="cursor:pointer;" :color="tag.tagColor">
            {{ tag.tagName }}
          </a-tag>
        </span>
      </div>
      <!-- 描述信息 -->
      <div class="desc-box">
        <strong>Pumpkin模板后台管理系统</strong>
        是基于SpringBoot + Vue 2.* 研发的前后端分离的一套系统。
        <br>
        后台管理使用Vue2 + Antd Vue UI 进行设计编写，左侧菜单栏层级清晰，用户操作简单。
        <br>
        使用阿里云Rds数据库管理系统，保障数据的安全稳定，不用担心数据丢失。文件存储采用多平台对象存储方式，
        保障图片上传服务稳定使用。
        <br>
      </div>
    </div>
    <!-- 其他部分 -->
    <div class="other-container">
      <a-timeline>
        <a-timeline-item><span class="date-item">2022-12-06</span>  SpringBoot、Vue项目基础框架搭建</a-timeline-item>
        <a-timeline-item><span class="date-item">2022-12-12</span>  整合SpringSecurity基础权限服务实现</a-timeline-item>
        <a-timeline-item><span class="date-item">2023-03-20</span>  后台管理界面以及前台资讯搭建</a-timeline-item>
        <a-timeline-item><span class="date-item">2023-04-20</span>  阿里云devops流水线项目部署</a-timeline-item>
      </a-timeline>
    </div>
  </div>
</template>

<script>

import TagList from "@/assets/json/TagList.json";
import Logo from "@/assets/images/logo/logo.png";

export default {
  name: "AboutMe",
  data() {
    return {
      tagList: TagList,
      Logo,
    }
  }
}
</script>

<style lang="scss" scoped>

.about-container {
  display: flex;
  justify-content: space-around;
  margin-bottom: -40px;

  .avatar-container {
    width: 10%;
    overflow: hidden;

    .avatar {
      padding: 5px;
      width: 100%;
      border: 2px solid gainsboro;
      height: auto;
      border-radius: 50%;
      cursor: pointer;
    }
  }

  .main-container {
    width: 60%;
    padding-left: 30px;
    display: flex;
    flex-direction: column;

    .title-box {
      font-size: 20px;
      font-weight: bolder;
      margin-bottom: 5px;
    }

    .tag-box {
      user-select: none;
      font-size: 16px;
      color: #9ba6ac;
      margin-bottom: 5px;
    }

    .desc-box {
      font-size: 14px;
      color: #9ba6ac;
    }
  }

  .other-container {
    width: 30%;
    margin-left: 50px;
    padding-top: 10px;

    .date-item{
      font-weight: bolder;
    }
  }

}



</style>
