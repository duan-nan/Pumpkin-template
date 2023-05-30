<template>
  <div class="my-info-container">
    <a-upload
        name="avatar"
        list-type="picture-card"
        style="display:flex;justify-content: center;border-radius: 50%"
        accept="image/*"
        :customRequest="uploadAvatar"
        :show-upload-list="false">
      <a-avatar v-if="userAvatar" :src="userAvatar" class="avatar-item"/>
      <div v-else>
        <a-icon :type="uploadLoading ? 'loading' : 'plus'"/>
        <div class="ant-upload-text">
          图片正在上传中...
        </div>
      </div>
    </a-upload>
    <div class="nickname-item">
      <a-tag class="gender-item" :color="getGender(userInfo.gender).color">
        {{ userInfo === null ? "获取失败" : getGender(userInfo.gender).name }}
      </a-tag>
      <span>{{ userInfo === null ? "获取失败" : userInfo.nickname }}</span>
    </div>

    <a-tooltip>
      <template slot="title">
        {{ userInfo === null ? "获取失败" : userInfo.personIntro }}
      </template>
      <div class="intro-item">
        <a-icon type="highlight"/>
        <span class="intro">{{ userInfo === null ? "获取失败" : userInfo.personIntro }}</span>
      </div>
    </a-tooltip>

    <a-divider dashed/>

    <div class="tag-item">
      <a-tag color="#f50">
        SpringBoot2
      </a-tag>
      <a-tag color="#2db7f5">
        MySQL
      </a-tag>
      <a-tag color="#87d068">
        Vue 2
      </a-tag>
      <a-tag color="#108ee9">
        Antd Vue
      </a-tag>
    </div>

  </div>
</template>

<script>
import {mapMutations, mapState} from "vuex";
import Logo from "@/assets/images/logo/logo.png";
import {uploadPicture} from "@/api/uploadApi";
import {updateUserAvatar} from "@/api/userInfoApi";

export default {
  name: "MyInfo",
  data() {
    return {
      Logo,
      uploadLoading: false,
      userAvatar: null
    }
  },
  computed: {
    ...mapState(['userInfo']),
    getGender() {
      return function (genderNumber) {
        switch (genderNumber) {
          case 1:
            return {name: "男生", color: "blue"};
          case 0:
            return {name: "女生", color: "pink"};
          case -1:
            return {name: "未知", color: "orange"};
        }
      }
    }
  },
  created() {
    this.userAvatar = this.userInfo.avatar;
  },
  methods: {
    ...mapMutations(['UPDATE_USERINFO']),
    async uploadAvatar(info) {
      this.uploadLoading = true;
      const res = await uploadPicture(info.file, "/user", `/avatar/${this.userInfo.id}/`);
      if (res.success) {
        this.userAvatar = res.data;
        await updateUserAvatar(this.userAvatar);
        this.UPDATE_USERINFO(null);
        window.location.reload();
        this.uploadLoading = false;
      }
    }
  }
}
</script>

<style lang="scss" scoped>

.my-info-container {
  display: flex;
  flex-direction: column;
  align-items: center;

  .avatar-item {
    width: 200px;
    height: 200px;
    cursor: pointer;
    user-select: none;
  }

  .nickname-item {
    margin: 15px 0;
    font-weight: bolder;
    font-size: 20px;
    color: rgba(0, 0, 0, .85);
    display: flex;
    justify-content: center;
    align-content: center;

    .gender-item {
      cursor: pointer;
      user-select: none;
      padding: 4px 10px;
    }

  }

  .intro-item {
    display: flex;
    justify-content: flex-start;
    align-items: center;

    .intro {
      overflow: hidden;
      text-overflow: ellipsis;
      display: -webkit-box;
      -webkit-line-clamp: 1;
      -webkit-box-orient: vertical;
    }
  }


  .tag-item {
    margin-top: 20px;
  }
}

</style>
