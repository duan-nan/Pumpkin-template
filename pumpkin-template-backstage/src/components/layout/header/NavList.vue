<template>
  <div class="nav-container">
    <!-- 历史菜单按钮 -->
    <div class="nav-list">
      <a-tag class="nav-item"
             @click="toPage(welcome.path)"
             :style="isSpanActive({name:welcome.name})  ? 'color:#ffffff;':'color:black;'"
             :color="isSpanActive({name:welcome.name})  ? '#1890ff':'#ffffff'">
        控制台
      </a-tag>
      <a-tag v-for="item in routerHistory"
             :key="item.path"
             class="nav-item"
             :closable="true"
             :style="isSpanActive(item) ? 'color:#ffffff;':'color:black;'"
             :color="isSpanActive(item) ? '#1890ff':'#ffffff'"
             @close="removeRoute(item)"
             @click="toPage(item.path)">
        {{ item.name }}
      </a-tag>
    </div>
    <!-- 关闭全部 -->
    <a-tag color="#ff7875"
           class="close-all"
           @click="closeAll">
      关闭全部
    </a-tag>

  </div>
</template>

<script>
import {mapMutations, mapState} from "vuex";

export default {
  name: "NavList",
  data() {
    return {
      welcome: {
        name: '控制台',
        path: '/welcome',
      }
    }
  },
  mounted() {
    this.SAVE_ROUTER_HISTORY(this.$route);
  },
  computed: {
    //=======================  state数据获取  ===========================
    ...mapState(["routerHistory"]),
    //=======================  校验方法  ===========================
    isSpanActive() {
      return function (tab) {
        return tab.name === this.$route.name;
      };
    },
  },
  methods: {
    //=======================  Vuex 方法引入  ===========================
    ...mapMutations([
      "SAVE_ROUTER_HISTORY",
      "REMOVE_ROUTE_HISTORY",
      "RESET_ROUTE_HISTORY",
    ]),
    //=======================  Vuex 方法实现  ===========================
    removeRoute(tab) {
      this.REMOVE_ROUTE_HISTORY(tab);
      if (tab.path === this.$route.path) {
        const navList = this.routerHistory;
        if (navList === 1) {
          this.$router.push(this.welcome.path);
        } else {
          this.$router.push({path: navList[navList.length - 1].path});
        }
      }
    },
    closeAll() {
      this.RESET_ROUTE_HISTORY();
      this.$router.push({path: "/welcome"});
    },
    //=======================  点击事件方法  ===========================
    toPage(path) {
      this.$router.push(path);
    },
  },
};
</script>

<style lang="scss" scoped>

.nav-container {
  overflow: hidden;
  width: 100%;
  height: 30px;
  line-height: 30px;
  background-color: #ffffff;
  display: flex;
  justify-content: space-between;
  font-size: 13px;
  box-shadow: 10px 1px 3px 0 rgba(0, 0, 0, 0.12), 10px 0 3px 0 rgba(0, 0, 0, 0.04);
  padding: 3px 10px;
  user-select: none;

  .nav-list {
    width: 90%;
    overflow: hidden;
    height: 100%;
    line-height: 25px;

    .nav-item {
      border-radius: 1px;
      border: 1px solid #dadee6;
      color: black;
      cursor: pointer;

    }
  }

  .close-all {
    cursor: pointer;
    line-height: 25px;
  }


}
</style>
