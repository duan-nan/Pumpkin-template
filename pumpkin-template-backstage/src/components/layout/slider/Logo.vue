<template>
  <div class="sidebar-logo-container" :class="{ collapsed: collapsed }">
    <transition name="sidebarLogoFade">
      <router-link v-if="collapsed" key="collapse" class="sidebar-logo-link" to="/home">
        <img v-if="logo" :src="logo" class="sidebar-logo"/>
        <h1 v-else class="sidebar-title">{{ title }}</h1>
      </router-link>
      <!--展开-->
      <router-link v-else key="expand" class="sidebar-logo-link" to="/home">
        <img v-if="logo" :src="logo" class="sidebar-logo"/>
        <h1 class="sidebar-title">{{ title }}</h1>
      </router-link>
    </transition>
  </div>
</template>

<script>
import LogoImg from "@/assets/images/logo/logo.png";
import {mapState} from "vuex";

export default {
  name: "Logo",
  props:['collapsed'],
  data() {
    return {
      title: "Pumpkin后台管理系统",
      logo: LogoImg,
    };
  }
};
</script>

<style scoped>
.sidebarLogoFade-enter-active {
  transition: opacity 1.5s;
}

.sidebarLogoFade-enter,
.sidebarLogoFade-leave-to {
  opacity: 0;
}

.sidebar-logo-container {
  position: relative;
  width: 100%;
  height: 55px;
  line-height: 55px;
  background: #001529;
  text-align: center;
  overflow: hidden;
  border: 1px solid black;
  border-radius: 5px;
  box-shadow: 2px 10px 15px;
  margin-bottom: 5px;
}

.sidebar-logo-link {
  height: 100%;
  width: 100%;
  display: flex;
  align-items: center;
  justify-content: center;
}

.sidebar-logo {
  width: 28px;
  height: 28px;
  text-align: center;
}

.sidebar-title {
  display: inline-block;
  margin: 0;
  color: #fff;
  font-weight: 600;
  line-height: 50px;
  font-size: 14px;
  font-family: Avenir, Helvetica Neue, Arial, Helvetica, sans-serif;
  vertical-align: middle;
}

.sidebar-logo {
  margin-right: 3px;
}
</style>
