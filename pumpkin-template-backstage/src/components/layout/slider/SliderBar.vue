<template>
  <div class="slider-bar-container">
    <!-- Logo -->
    <logo :collapsed="collapsed"/>
    <!-- 侧边栏菜单 -->
    <a-menu theme="dark"  mode="inline">
      <template v-for="menuRouter in menuRouterList">
        <!-- 目录 -->
        <a-menu-item v-if="menuRouter.children.length === 0 && menuRouter.menuType === 'C'" :key="menuRouter.id">
          <router-link :to="menuRouter.path">
            <a-icon :type="menuRouter.icon"/>
            <span>{{ menuRouter.name }}</span>
          </router-link>
        </a-menu-item>

        <!--级菜单 -->
        <a-sub-menu v-if="menuRouter.children.length >= 0 && menuRouter.menuType === 'M'" :key="menuRouter.id">
          <!-- 二级菜单名称 -->
          <span slot="title">
                  <a-icon :type="menuRouter.icon"/>
                  <span>{{ menuRouter.name }}</span>
          </span>


          <!-- 二级菜单 + 目录 -->
          <a-menu-item v-for="child in menuRouter.children"
                       v-if="child.children.length === 0 && child.menuType === 'C'"
                       :key="child.id">
            <router-link :to="child.path">
              <a-icon :type="child.icon"/>
              <span>{{ child.name }}</span>
            </router-link>
          </a-menu-item>


          <!-- 三级菜单 + 目录 -->
          <a-sub-menu v-for="child in menuRouter.children" v-if="child.children.length > 0 && child.menuType === 'M'"
                      :key="child.id">
              <span slot="title">
                <a-icon :type="child.icon"/>
                <span>{{ child.name }}</span>
              </span>
            <a-menu-item v-for="content in child.children" :key="content.id">
              <router-link :to="content.path">
                <a-icon :type="content.icon"/>
                <span>{{ content.name }}</span>
              </router-link>
            </a-menu-item>
          </a-sub-menu>


        </a-sub-menu>

      </template>
    </a-menu>
  </div>
</template>

<script>
import {mapState} from "vuex";
import Logo from "@/components/layout/slider/Logo";

export default {
  name: "SliderBar",
  props: ['collapsed'],
  // data() {
  //   return {
  //     defaultOpenKeys: [this.$route.meta.currentId],
  //   }
  // },
  components: {
    Logo
  },
  computed: {
    ...mapState(['menuRouterList'])
  },
  created() {

  }

}
</script>

<style lang="scss" scoped>

.slider-bar-container {
  user-select: none;
}

</style>
