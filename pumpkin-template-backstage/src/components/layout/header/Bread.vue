<template>
  <a-breadcrumb class="bread-box">
    <span v-if="isHome"><a-icon type="home"/> 首页</span>
    <a-breadcrumb-item v-if="!isHome" v-for="bread in breadList" :key="bread.path" :href="bread.path">
      <a-icon :type="bread.icon"/>
      <span @click="toPage(bread.path)">{{ bread.name }}</span>
    </a-breadcrumb-item>
  </a-breadcrumb>
</template>

<script>

import {mapState} from "vuex";

export default {
  name: "Bread",
  data() {
    return {
      breadList: [],
      bread: {
        path: '',
        name: '',
      },
      isHome: false,
    };
  },
  computed: {
    ...mapState(['menuRouterList'])
  },
  created() {

    var currentPath = this.$route.path;
    var currentName = this.$route.name;

    if (currentPath === '/welcome' || currentName === '/home') {
      this.isHome = true;
    } else {
      this.breadList.push({'name': '首页', 'icon': 'home', 'path': '/home'});
      var grandFather = this.$route.meta.grandFather;
      var father = this.$route.meta.father;

      if (grandFather){
        if (grandFather.parentName !== null) {
          this.breadList.push({'name': grandFather.parentName, 'icon': grandFather.parentIcon, 'path': '#'});
        }
        this.breadList.push({'name': father.parentName, 'icon': father.parentIcon, 'path': '#'});
        this.breadList.push({'name': currentName, 'icon': this.$route.meta.currentIcon, 'path': currentPath});
      }
    }


  },
  methods: {
    toPage(path) {
      this.$router.push(path);
    }
  }
};
</script>

<style lang="scss" scoped>

.bread-box {
  font-size: 14px;
  font-weight: bolder;
  user-select: none;
}

</style>
