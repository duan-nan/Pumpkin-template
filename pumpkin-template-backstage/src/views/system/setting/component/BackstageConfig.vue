<template>
  <div class="website-container">
    <!-- 网站图标 -->
    <div class="item-box">
      <span class="title">网站Logo：</span>
      <a-upload
          name="file"
          accept="image/*"
          class="upload-item-avatar"
          :customRequest="uploadLogo"
          :show-upload-list="false">
        <img class="img-item"
             v-if="backstageConfig.logo !== ''"
             :src="backstageConfig.logo"
             alt="avatar"/>
        <div class="text-item" v-else>
          <a-icon :type="logoLoading ? 'loading' : 'picture'"/>
          <div class="ant-upload-text">
            网站Logo
          </div>
        </div>
      </a-upload>
    </div>
    <!-- 网站标题 -->
    <div class="item-box">
      <span class="title">网站标题：</span>
      <a-input class="input" v-model="backstageConfig.websiteTitle" placeholder="请输入后台标题...">
        <a-tooltip slot="suffix" title="博客后台网站标题">
          <a-icon type="form" style="color: rgba(0,0,0,.45)"/>
        </a-tooltip>
      </a-input>
    </div>
    <!-- 网站名称 -->
    <div class="item-box">
      <span class="title">网站名称：</span>
      <a-input class="input" v-model="backstageConfig.websiteName" placeholder="请输入后台名称...">
        <a-tooltip slot="suffix" title="博客后台网站名称">
          <a-icon type="appstore" style="color: rgba(0,0,0,.45)"/>
        </a-tooltip>
      </a-input>
    </div>
    <!-- 网站标语 -->
    <div class="item-box">
      <span class="title">网站标语：</span>
      <a-textarea class="input" v-model="backstageConfig.websitePoster"
                  :auto-size="{ minRows: 5, maxRows: 5 }"
                  placeholder="请输入网站后台标语...">
        <a-tooltip slot="suffix" title="博客后台网站标语">
          <a-icon type="reconciliation" style="color: rgba(0,0,0,.45)"/>
        </a-tooltip>
      </a-textarea>
    </div>
    <!-- 版权信息 -->
    <div class="item-box">
      <span class="title">网站版权：</span>
      <a-input
          class="input"
          v-model="backstageConfig.copyright"
          placeholder="请输入网站版权声明..."
      >
        <a-tooltip slot="suffix" title="网站版权声明...">
          <a-icon type="copyright" style="color: rgba(0, 0, 0, 0.45)"/>
        </a-tooltip>
      </a-input>
    </div>
    <!-- 网站背景图片 -->
    <div class="item-box">
      <span class="title">网站背景：</span>
      <a-upload-dragger
          name="file"
          accept="image/*"
          class="upload-item-backstage"
          :show-upload-list="false"
          :customRequest="uploadBackground">
        <div v-if="(backstageConfig.background === '' || backstageConfig.background === null)
          && backgroundLoading === false">
          <p class="ant-upload-drag-icon">
            <a-icon type="picture"/>
          </p>
          <p class="ant-upload-text">
            后台管理系统背景图片
          </p>
          <p class="ant-upload-hint">
            上传后台管理系统背景图片，建议100kb～5MB大小。
          </p>
        </div>
        <div v-if="backgroundLoading === true">
          <p class="ant-upload-drag-icon">
            <a-icon spin type="redo"/>
          </p>
          <p class="ant-upload-text">图片上传中...</p>
          <p class="ant-upload-hint">请稍等，正在火速搬运图片！</p>
        </div>
        <img class="img-item"
             v-if="backstageConfig.background !== '' && backgroundLoading === false"
             :src="backstageConfig.background">
      </a-upload-dragger>
    </div>
    <!-- 第三方登陆 -->
    <div class="item-box">
      <span class="title">第三方登陆：</span>
      <a-select
          mode="multiple"
          style="width: 70%"
          v-model="backstageConfig.backstageLoginType">
        <a-select-option value="QQ">
          QQ
        </a-select-option>
        <a-select-option value="weChat">
          微信
        </a-select-option>
      </a-select>
    </div>
    <!-- 修改按钮 -->
    <div class="button-submit">
      <a-popconfirm
          title="确定要更新网站后台信息配置吗?"
          ok-text="确定"
          cancel-text="取消"
          @confirm="doUpdate">
        <a-button
            icon="cloud-sync"
            class="button"
            type="primary">
          更新网站后台信息
        </a-button>
      </a-popconfirm>
    </div>
  </div>
</template>

<script>
import fileType from "@/assets/json/fileType.json";
import {getWebsiteConfig, updateWebsiteConfig} from "@/api/websiteConfigApi";
import {uploadPicture} from "@/api/uploadApi";


export default {
  name: "BackstageConfig",
  data() {
    return {
      backstageConfig: {},
      logoLoading: false,
      backgroundLoading: false,
      fileType: fileType,
      formData: {
        configJsonData: "",
        websiteModuleCode: 2
      }
    }
  },
  created() {
    this.getBackstageConfig();
  },
  methods: {
    //================== 获取网站配置 ===================
    async getBackstageConfig() {
      const res = await getWebsiteConfig(2);
      if (res.success) {
        this.backstageConfig = res.data;
      }
    },
    //================== 更新配置信息 ===================
    async doUpdate() {
      this.formData.configJsonData = JSON.stringify(this.backstageConfig);
      const res = await updateWebsiteConfig(this.formData);
      if (res.success) {
        this.$notification.success({message: res.message});
      }
    },
    //=======================  logo上传  ===========================
    async uploadLogo(info) {
      this.logoLoading = true;
      const res = await uploadPicture(info.file, "/website", "/backstage/");
      if (res.success) {
        this.backstageConfig.logo = res.data;
        this.logoLoading = false;
        this.$notification.success({message: '文件上传成功！'});
      }
    },
    //=======================  背景上传  ===========================
    async uploadBackground(info) {
      this.backgroundLoading = true;
      const res = await uploadPicture(info.file, "/website", "/backstage/");
      if (res.success) {
        this.backstageConfig.background = res.data;
        this.backgroundLoading = false;
        this.$notification.success({message: '文件上传成功！'});
      }
    }
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
      width: 70%;
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
