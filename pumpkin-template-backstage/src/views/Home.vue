<template>
  <a-layout class="home-container">
    <!-- 侧边栏 -->
    <a-layout-sider width="220" :collapsed="collapsed">
      <slider-bar :collapsed="collapsed"/>
    </a-layout-sider>
    <a-layout>
      <!-- 顶部部分 -->
      <a-layout-header>
        <nav-bar/>
      </a-layout-header>
      <!-- 内容部分 -->
      <a-layout-content class="content">
        <router-view></router-view>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>

<script>

import SliderBar from "@/components/layout/slider/SliderBar";
import {mapState} from "vuex";
import NavBar from "@/components/layout/header/NavBar";

export default {
  name: "Home",
  components: {SliderBar, NavBar},
  computed: {
    ...mapState(['collapsed'])
  }
}
</script>

<style lang="scss" scoped>
.home-container {
  width: 100%;
  height: 100%;
  background: #f5f6fa;

  .ant-layout-header {
    padding: 0;
    height: 85px;
    background: white;
    border-bottom: 1px solid;
    box-shadow: 1px 1px 1px #e2dede;
  }

  .ant-layout-slider {
    border-right: 1px solid;
    box-shadow: 1px 5px 10px;
    border-radius: 3px;
    overflow-y: scroll;
    overflow-x: hidden;
    user-select: none;
  }


  .content {
    margin: 5px 10px 0 10px;
  }
}
</style>
