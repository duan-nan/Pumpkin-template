<template>
  <div class="website-container">
    <!-- QQ账号 -->
    <div class="item-box">
      <span class="title">QQ：</span>
      <a-input class="input" v-model="socialInfo.qqNumber" placeholder="请输入QQ账号...">
        <a-tooltip slot="suffix" title="站长QQ">
          <a-icon type="qq" style="color: rgba(0,0,0,.45)"/>
        </a-tooltip>
      </a-input>
      <a-checkbox class="checkbox" :checked="socialInfo.showQq" v-model="socialInfo.showQq">
        展示
      </a-checkbox>
    </div>
    <!-- 微信账号 -->
    <div class="item-box">
      <span class="title">WeChat：</span>
      <a-input class="input" v-model="socialInfo.weChatNumber" placeholder="请输入微信账号...">
        <a-tooltip slot="suffix" title="站长微信">
          <a-icon type="wechat" style="color: rgba(0,0,0,.45)"/>
        </a-tooltip>
      </a-input>
      <a-checkbox class="checkbox" :checked="socialInfo.showWeChat" v-model="socialInfo.showWeChat">
        展示
      </a-checkbox>
    </div>
    <!-- Gitee -->
    <div class="item-box">
      <span class="title">Gitee：</span>
      <a-input class="input" v-model="socialInfo.giteeUrl" placeholder="请输入Gitee首页地址...">
        <a-tooltip slot="suffix" title="站长Gtiee">
          <a-icon type="info-circle" style="color: rgba(0,0,0,.45)"/>
        </a-tooltip>
      </a-input>
      <a-checkbox class="checkbox" :checked="socialInfo.showGitee" v-model="socialInfo.showGitee">
        展示
      </a-checkbox>
    </div>
    <!-- Github -->
    <div class="item-box">
      <span class="title">Github：</span>
      <a-input class="input" v-model="socialInfo.githubUrl" placeholder="请输入Github首页地址...">
        <a-tooltip slot="suffix" title="站长Github">
          <a-icon type="info-circle" style="color: rgba(0,0,0,.45)"/>
        </a-tooltip>
      </a-input>
      <a-checkbox class="checkbox" :checked="socialInfo.showGithub" v-model="socialInfo.showGithub">
        展示
      </a-checkbox>
    </div>
    <!-- Bilibili账号 -->
    <div class="item-box">
      <span class="title">Bilibili：</span>
      <a-input class="input" v-model="socialInfo.bilibiliUrl" placeholder="请输入Bilibili首页地址...">
        <a-tooltip slot="suffix" title="站长Bilibili">
          <a-icon type="video-camera" style="color: rgba(0,0,0,.45)"/>
        </a-tooltip>
      </a-input>
      <a-checkbox class="checkbox" :checked="socialInfo.showBilibili" v-model="socialInfo.showBilibili">
        展示
      </a-checkbox>
    </div>
    <!-- CSDN -->
    <div class="item-box">
      <span class="title">CSDN：</span>
      <a-input class="input" v-model="socialInfo.csdnUrl" placeholder="请输入CSDN首页地址...">
        <a-tooltip slot="suffix" title="站长CSDN">
          <a-icon type="info-circle" style="color: rgba(0,0,0,.45)"/>
        </a-tooltip>
      </a-input>
      <a-checkbox class="checkbox" :checked="socialInfo.showCsdn" v-model="socialInfo.showCsdn">
        展示
      </a-checkbox>
    </div>
    <!-- QQ交流 -->
    <div class="item-box">
      <span class="title">QQ交流群：</span>
      <a-input class="input" v-model="socialInfo.groupNumber" placeholder="请输入QQ交流群群号...">
        <a-tooltip slot="suffix" title="站长QQ群">
          <a-icon type="message" style="color: rgba(0,0,0,.45)"/>
        </a-tooltip>
      </a-input>
      <a-checkbox class="checkbox" :checked="socialInfo.showGroup" v-model="socialInfo.showGroup">展示
      </a-checkbox>
    </div>
    <!-- 修改按钮 -->
    <div class="button-submit">
      <a-popconfirm
          title="确定要更新社交信息配置吗?"
          ok-text="确定"
          cancel-text="取消"
          @confirm="doUpdate"
      >
        <a-button icon="cloud-sync" class="button" type="primary">更新社交信息</a-button>
      </a-popconfirm>
    </div>
  </div>
</template>

<script>


import {getWebsiteConfig, updateWebsiteConfig} from "@/api/websiteConfigApi";

export default {
  name: "SocialConfig",
  data() {
    return {
      socialInfo: {},
      formData: {
        configJsonData: "",
        websiteModuleCode: 3
      }
    }
  },
  created() {
    this.getSocialConfig();
  },
  methods: {
    //================== 查询配置信息 ===================
    async getSocialConfig() {
      const res = await getWebsiteConfig(3);
      if (res.success) {
        this.socialInfo = res.data;
      }
    },
    //================== 更新配置信息 ===================
    async doUpdate() {
      this.formData.configJsonData = JSON.stringify(this.socialInfo);
      const res = await updateWebsiteConfig(this.formData);
      if (res.success) {
        this.$notification.success({message: res.message});
      }
    },
  }
}
</script>

<style lang="scss" scoped>
.website-container {
  width: 90%;
  margin: 0 auto;
  height: auto;

  .item-box {
    display: flex;
    justify-content: left;
    align-items: center;
    margin: 10px 0;
    width: 100%;
    height: auto;

    .title {
      width: 10%;
      font-size: 15px;
      margin: 10px 0;
    }

    .input {
      width: 60%;
    }

    .checkbox {
      margin-left: 10px;
      width: 10%;
    }
  }

  .button-submit {
    display: flex;
    justify-content: center;
    align-items: center;


    .button {
      margin-top: 20px;
      width: 30%;
    }
  }

}


</style>
