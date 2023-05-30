<template>
  <div class="website-container">
    <!-- 网站图标 -->
    <div class="item-box">
      <span class="title">网站Logo：</span>
      <a-upload name="file"
                class="upload-item-avatar"
                :show-upload-list="false"
                accept="image/*"
                :customRequest="uploadLogo">
        <img class="img-item"
             :src="formatConfig.logo"
             v-if="formatConfig.logo !== undefined && formatConfig.logo !== ''"
             alt="avatar"/>
        <div class="text-item" v-else>
          <a-icon :type="logoLoading ? 'loading' : 'contacts'"/>
          <div class="ant-upload-text">Logo</div>
        </div>
      </a-upload>
    </div>
    <!-- 网站名称 -->
    <div class="item-box">
      <span class="title">网站名称：</span>
      <a-input class="input"
               v-model="formatConfig.websiteName"
               placeholder="请输入网站名称...">
        <a-tooltip slot="suffix" title="博客首页网站名称">
          <a-icon type="form" style="color: rgba(0, 0, 0, 0.45)"/>
        </a-tooltip>
      </a-input>
    </div>
    <!-- 网站作者 -->
    <div class="item-box">
      <span class="title">网站作者：</span>
      <a-input class="input"
               v-model="formatConfig.author"
               placeholder="请输入作者名称...">
        <a-tooltip slot="suffix" title="博客作者名称">
          <a-icon type="user" style="color: rgba(0, 0, 0, 0.45)"/>
        </a-tooltip>
      </a-input>
    </div>
    <!-- 网站motto -->
    <div class="item-box">
      <span class="title">墓志铭：</span>
      <a-input class="input"
               v-model="formatConfig.motto"
               placeholder="请输入网站墓志铭...">
        <a-tooltip slot="suffix" title="网站墓志铭">
          <a-icon type="user" style="color: rgba(0, 0, 0, 0.45)"/>
        </a-tooltip>
      </a-input>
    </div>
    <!-- 网站公告 -->
    <div class="item-box">
      <span class="title">网站公告：</span>
      <a-textarea class="input"
                  v-model="formatConfig.announcement"
                  :auto-size="{ minRows: 6, maxRows: 6 }"
                  placeholder="请输入网站公告信息..."/>
    </div>
    <!-- 网站背景图片 -->
    <div class="item-box">
      <span class="title">评论背景：</span>
      <a-upload-dragger
          name="file"
          :show-upload-list="false"
          class="upload-item-backstage"
          accept="image/*"
          :customRequest="uploadCommentBack">
        <div v-if="
            (formatConfig.commentBack === '' ||formatConfig.commentBack === undefined ||formatConfig.commentBack === null)&& commentBackLoading === false
          ">
          <p class="ant-upload-drag-icon">
            <a-icon type="picture"/>
          </p>
          <p class="ant-upload-text">评论区背景图片</p>
          <p class="ant-upload-hint">
            上传评论区背景图片，建议100kb～1MB大小。
          </p>
        </div>
        <div v-if="commentBackLoading === true">
          <p class="ant-upload-drag-icon">
            <a-icon spin type="redo"/>
          </p>
          <p class="ant-upload-text">图片上传中...</p>
          <p class="ant-upload-hint">请稍等，阿星正在火速搬运图片！</p>
        </div>
        <img
            v-if="formatConfig.commentBack !== '' && commentBackLoading === false"
            class="img-item"
            :src="formatConfig.commentBack"
        />
      </a-upload-dragger>
    </div>
    <!-- 网站创建时间 -->
    <div class="item-box">
      <span class="title">网站创建时间：</span>
      <a-date-picker
          placeholder="请输入网站创建时间..."
          :value="formatConfig.createDate === null ? null:formatConfig.createDate"
          @change="(date, dateString) => (formatConfig.createDate = dateString)"
          class="date"
      />
    </div>
    <!-- 版权信息 -->
    <div class="item-box">
      <span class="title">网站版权：</span>
      <a-input
          class="input"
          v-model="formatConfig.copyright"
          placeholder="请输入网站版权声明..."
      >
        <a-tooltip slot="suffix" title="网站版权声明...">
          <a-icon type="copyright" style="color: rgba(0, 0, 0, 0.45)"/>
        </a-tooltip>
      </a-input>
    </div>
    <!-- footer Word -->
    <div class="item-box">
      <span class="title">页脚美文：</span>
      <a-input
          class="input"
          v-model="formatConfig.footerWord"
          placeholder="请输入页脚美文..."
      >
        <a-tooltip slot="suffix" title="页脚美文...">
          <a-icon type="copyright" style="color: rgba(0, 0, 0, 0.45)"/>
        </a-tooltip>
      </a-input>
    </div>
    <!-- 备案号 -->
    <div class="item-box">
      <span class="title">网站备案号：</span>
      <a-input
          class="input"
          v-model="formatConfig.caseNumber"
          placeholder="请输入网站备案号..."
      >
        <a-tooltip slot="suffix" title="工信部颁发的网站备案号...">
          <a-icon type="project" style="color: rgba(0, 0, 0, 0.45)"/>
        </a-tooltip>
      </a-input>
    </div>
    <!-- 第三方登陆 -->
    <div class="item-box">
      <span class="title">第三方登陆：</span>
      <a-select
          mode="multiple"
          style="width: 70%"
          v-model="formatConfig.formatLoginType">
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
      <a-popconfirm title="确定要更新前台网站信息吗?" ok-text="确定" cancel-text="取消" @confirm="doUpdate">
        <a-button icon="cloud-sync" class="button" type="primary">更新前台网站信息</a-button>
      </a-popconfirm>
    </div>
  </div>
</template>

<script>
import fileType from "@/assets/json/fileType.json";
import {getWebsiteConfig, updateWebsiteConfig} from "@/api/websiteConfigApi";
import {uploadPicture} from "@/api/uploadApi";


export default {
  name: "FormatConfig",
  data() {
    return {
      formatConfig: {},
      loading: false,
      commentBackLoading: false,
      logoLoading: false,
      fileType: fileType,
      formData: {
        configJsonData: "",
        websiteModuleCode: 1
      }
    };
  },
  created() {
    this.getFormatConfig();
  },
  methods: {
    //================== 获取配置信息 ===================
    async getFormatConfig() {
      const res = await getWebsiteConfig(1);
      if (res.success) {
        this.formatConfig = res.data;
      }
    },
    //================== 更新配置信息 ===================
    async doUpdate() {
      this.formData.configJsonData = JSON.stringify(this.formatConfig);
      const res = await updateWebsiteConfig(this.formData);
      if (res.success) {
        this.$notification.success({message: res.message});
      }
    },
    //=======================  前台Logo上传  ===========================
    async uploadLogo(info) {
      this.logoLoading = true;
      const res = await uploadPicture(info.file, "/website", "/format/");
      if (res.success) {
        this.formatConfig.logo = res.data;
        this.logoLoading = false;
        this.$notification.success({message: '文件上传成功！'});
      }
    },
    async uploadCommentBack(info) {
      this.commentBackLoading = true;
      const res = await uploadPicture(info.file, '/website', '/format/');
      if (res.success) {
        this.formatConfig.commentBack = res.data;
        this.commentBackLoading = false;
        this.$notification.success({message: '文件上传成功！'});
      }
    },

  },
};
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
      width: 13%;
      font-size: 15px;
      margin: 10px 0;
    }

    .input {
      width: 70%;
    }

    .date {
      width: 30%;
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
