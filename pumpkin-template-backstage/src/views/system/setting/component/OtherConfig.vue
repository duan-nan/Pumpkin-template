<template>
  <div class="website-container">
    <!-- 路由菜单类型 -->
    <div class="item-box">
      <span class="title">路由菜单类型：</span>
      <a-select
          style="width: 30%"
          v-model="otherConfig.menuRouteType">
        <a-select-option
            v-for="item in menuRouteTypeList"
            :key="item.value"
            :value="item.value"
            :label="item.name">
          {{ item.name }}
        </a-select-option>
      </a-select>
    </div>
    <!-- 用户/游客头像 -->
    <div class="item-box">
      <span class="title">用户头像：</span>
      <a-upload name="file"
                accept="image/*"
                class="upload-item-avatar"
                :show-upload-list="false"
                :customRequest="userAvatarUploadChange">
        <img class="img-item" v-if="otherConfig.userAvatar !== ''" :src="otherConfig.userAvatar" alt="avatar"/>
        <div class="text-item" v-else>
          <a-icon :type="userAvatarLoading ? 'loading' : 'smile'"/>
          <div class="ant-upload-text">
            50kb~2MB
          </div>
        </div>
      </a-upload>
      <span class="title" style="margin-left: 50px">游客头像：</span>
      <a-upload
          name="file"
          accept="image/*"
          class="upload-item-avatar"
          :show-upload-list="false"
          :customRequest="visitorAvatarUploadChange">
        <img class="img-item" v-if="otherConfig.visitorAvatar !== ''" :src="otherConfig.visitorAvatar" alt="avatar"/>
        <div class="text-item" v-else>
          <a-icon :type="visitorAvatarLoading ? 'loading' : 'meh'"/>
          <div class="ant-upload-text">
            50kb~1MB
          </div>
        </div>
      </a-upload>
    </div>
    <!-- 对象存储服务 -->
    <div class="item-box">
      <span class="title">对象存储：</span>
      <a-select
          style="width: 30%"
          v-model="otherConfig.uploadType">
        <a-select-option
            v-for="item in objectStoreList"
            :key="item.label"
            :value="item.label"
            :label="item.name"
            :disabled="item.disabled">
          {{ item.name }}
        </a-select-option>
      </a-select>
    </div>
    <!-- 文章审核 -->
    <div class="item-box">
      <span class="title">文章审核：</span>
      <a-select
          style="width: 30%"
          v-model="otherConfig.articleVerify">
        <a-select-option :value="1">
          <a-icon type="check-circle" theme="twoTone" class="icon-item"/>
          开启
        </a-select-option>
        <a-select-option :value="0">
          <a-icon type="close-circle" theme="twoTone" two-tone-color="red" class="icon-item"/>
          关闭
        </a-select-option>
      </a-select>
    </div>
    <!-- 留言审核 -->
    <div class="item-box">
      <span class="title">留言审核：</span>
      <a-select
          style="width: 30%"
          v-model="otherConfig.leaveVerify">
        <a-select-option :value="1">
          <a-icon type="check-circle" theme="twoTone" class="icon-item"/>
          开启
        </a-select-option>
        <a-select-option :value="0">
          <a-icon type="close-circle" theme="twoTone" two-tone-color="red" class="icon-item"/>
          关闭
        </a-select-option>
      </a-select>
    </div>
    <!-- 评论审核 -->
    <div class="item-box">
      <span class="title">评论审核：</span>
      <a-select
          style="width: 30%"
          v-model="otherConfig.commentVerify">
        <a-select-option :value="1">
          <a-icon type="check-circle" theme="twoTone" class="icon-item"/>
          开启
        </a-select-option>
        <a-select-option :value="0">
          <a-icon type="close-circle" theme="twoTone" two-tone-color="red" class="icon-item"/>
          关闭
        </a-select-option>
      </a-select>
    </div>
    <!-- 微信/支付宝收款码 -->
    <div class="item-box">
      <span class="title">微信收款码：</span>
      <a-upload
          name="file"
          accept="image/*"
          class="upload-item-avatar"
          :show-upload-list="false"
          :customRequest="wechatPayUploadChange">
        <img class="img-item" v-if="otherConfig.weChatPay !== ''" :src="otherConfig.weChatPay" alt="avatar"/>
        <div class="text-item" v-else>
          <a-icon :type="wechatPayLoading ? 'loading' : 'red-envelope'"/>
          <div class="ant-upload-text">
            50kb~2MB
          </div>
        </div>
      </a-upload>
      <span class="title" style="margin-left: 50px">支付宝收款码：</span>
      <a-upload
          name="file"
          accept="image/*"
          class="upload-item-avatar"
          :show-upload-list="false"
          :customRequest="aliPayUploadChange">
        <img class="img-item" v-if="otherConfig.alipayPay !== ''" :src="otherConfig.alipayPay" alt="avatar"/>
        <div class="text-item" v-else>
          <a-icon :type="aliPayPayLoading ? 'loading' : 'red-envelope'"/>
          <div class="ant-upload-text">
            50kb~1MB
          </div>
        </div>
      </a-upload>
    </div>
    <!-- 邮箱通知 -->
    <div class="item-box">
      <span class="title">邮箱通知：</span>
      <a-select
          style="width: 30%"
          v-model="otherConfig.emailNotify">
        <a-select-option :value="1">
          <a-icon type="check-circle" theme="twoTone" class="icon-item"/>
          开启
        </a-select-option>
        <a-select-option :value="0">
          <a-icon type="close-circle" theme="twoTone" two-tone-color="red" class="icon-item"/>
          关闭
        </a-select-option>
      </a-select>
    </div>
    <!-- 聊天室状态 -->
    <div class="item-box">
      <span class="title">聊天室状态：</span>
      <a-select
          style="width: 30%"
          v-model="otherConfig.chatRoomStatus">
        <a-select-option :value="1">
          <a-icon type="check-circle" theme="twoTone" class="icon-item"/>
          开启
        </a-select-option>
        <a-select-option :value="0">
          <a-icon type="close-circle" theme="twoTone" two-tone-color="red" class="icon-item"/>
          关闭
        </a-select-option>
      </a-select>
    </div>
    <!-- 聊天室配置 -->
    <div class="item-box" v-if="otherConfig.chatRoomStatus">
      <span class="title">WebSocket地址：</span>
      <a-input class="input" v-model="otherConfig.webSocketUrl" placeholder="请输入WebSocket地址...">
        <a-tooltip slot="suffix" title="WebSocket地址...">
          <a-icon type="info-circle" style="color: rgba(0,0,0,.45)"/>
        </a-tooltip>
      </a-input>
    </div>
    <!-- 音乐播放器 -->
    <div class="item-box">
      <span class="title">音乐播放器：</span>
      <a-select
          style="width: 30%"
          v-model="otherConfig.musicStatus">
        <a-select-option :value="1">
          <a-icon type="check-circle" theme="twoTone" class="icon-item"/>
          开启
        </a-select-option>
        <a-select-option :value="0">
          <a-icon type="close-circle" theme="twoTone" two-tone-color="red" class="icon-item"/>
          关闭
        </a-select-option>
      </a-select>
    </div>
    <!-- 修改按钮 -->
    <div class="button-submit">
      <a-popconfirm
          title="确定要更新其他信息配置吗?"
          ok-text="确定"
          cancel-text="取消"
          @confirm="doUpdate"
      >
        <a-button icon="cloud-sync" class="button" type="primary">更新其他信息</a-button>
      </a-popconfirm>
    </div>
  </div>
</template>

<script>


import {getWebsiteConfig, listMenuRoute, updateWebsiteConfig} from "@/api/websiteConfigApi";
import {listObjectStoreBasicInfo} from "@/api/objectStoreApi";
import {uploadPicture} from "@/api/uploadApi";

export default {
  name: "OtherConfig",
  data() {
    return {
      objectStoreList: [],
      menuRouteTypeList: [],
      otherConfig: {},
      userAvatarLoading: false,
      visitorAvatarLoading: false,
      wechatPayLoading: false,
      aliPayPayLoading: false,
      formData: {
        configJsonData: "",
        websiteModuleCode: 4
      }
    }
  },
  created() {
    this.initData();
    this.getOtherConfig();

  },
  methods: {
    //================== 获取配置信息 ===================
    async initData() {
      const res = await listObjectStoreBasicInfo();
      if (res.success) {
        this.objectStoreList = res.data;
      }

      const menuRouteRes = await listMenuRoute();
      if (menuRouteRes.success) {
        this.menuRouteTypeList = menuRouteRes.data;
      }
    },
    async getOtherConfig() {
      const res = await getWebsiteConfig(4);
      if (res.success) {
        this.otherConfig = res.data;
      }
    },
    //================== 更新配置信息 ===================
    async doUpdate() {
      this.formData.configJsonData = JSON.stringify(this.otherConfig);
      const res = await updateWebsiteConfig(this.formData);
      if (res.success) {
        this.$notification.success({message: res.message});
      }
    },
    //=======================  用户头像上传  ===========================
    async userAvatarUploadChange(info) {
      this.userAvatarLoading = true;
      const res = await uploadPicture(info.file, "/website", "/other/");
      if (res.success) {
        this.otherConfig.userAvatar = res.data;
        this.userAvatarLoading = false;
        this.$notification.success({message: '文件上传成功！'});
      }
    },
    //=======================  游客头像上传  ===========================
    async visitorAvatarUploadChange(info) {
      this.visitorAvatarLoading = true;
      const res = await uploadPicture(info.file, "/website", "/other/");
      if (res.success) {
        this.otherConfig.visitorAvatar = res.data;
        this.visitorAvatarLoading = false;
        this.$notification.success({message: '文件上传成功！'});
      }
    },
    //=======================  微信收款码上传  ===========================
    async wechatPayUploadChange(info) {
      this.wechatPayLoading = true;
      const res = await uploadPicture(info.file, "/website", "/other/");
      if (res.success) {
        this.otherConfig.weChatPay = res.data;
        this.wechatPayLoading = false;
        this.$notification.success({message: '文件上传成功！'});
      }
    },
    //=======================  支付宝收款码上传  ===========================
    async aliPayUploadChange(info) {
      this.aliPayPayLoading = true;
      const res = await uploadPicture(info.file, "/website", "/other/");
      if (res.success) {
        this.otherConfig.alipayPay = res.data;
        this.aliPayPayLoading = false;
        this.$notification.success({message: '文件上传成功！'});
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
      width: 15%;
      font-size: 15px;
      margin: 10px 0;
    }

    .input {
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

.icon-item {
  margin-right: 5px;
}
</style>
