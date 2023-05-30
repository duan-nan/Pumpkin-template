<template>
  <a-card class="animate__animated animate__fadeIn" style="border-radius: 5px">
    <!-- 配置信息部分 -->
    <div class="card-container">
      <a-tabs tabPosition="left" :activeKey="activeKey" @change="changePane">
        <a-tab-pane key="format">
          <span slot="tab">
            <a-icon type="laptop"/>
            网站前台配置
          </span>
          <format-config class="animate__animated animate__fadeIn"/>
        </a-tab-pane>
        <a-tab-pane key="backstage">
          <span slot="tab">
            <a-icon type="bar-chart"/>
            网站后台配置
          </span>
          <backstage-config class="animate__animated animate__fadeIn"/>
        </a-tab-pane>
        <a-tab-pane key="social">
          <span slot="tab">
            <a-icon type="qq"/>
            社交信息配置
          </span>
          <social-config class="animate__animated animate__fadeIn"/>
        </a-tab-pane>
        <a-tab-pane key="other">
          <span slot="tab">
            <a-icon type="tool"/>
            其他信息配置
          </span>
          <other-config class="animate__animated animate__fadeIn"/>
        </a-tab-pane>
      </a-tabs>
    </div>
  </a-card>
</template>

<script>

import FormatConfig from "@/views/system/setting/component/FormatConfig";
import BackstageConfig from "@/views/system/setting/component/BackstageConfig";
import SocialConfig from "@/views/system/setting/component/SocialConfig";
import OtherConfig from "@/views/system/setting/component/OtherConfig";

export default {
  name: "Website",
  components: {
     FormatConfig, BackstageConfig, SocialConfig, OtherConfig
  },
  data() {
    return {
      activeKey: 'format',
    }
  },
  methods: {
    changePane(activeKey) {
      this.activeKey = activeKey;
    }
  }
}
</script>

<style scoped>

</style>
