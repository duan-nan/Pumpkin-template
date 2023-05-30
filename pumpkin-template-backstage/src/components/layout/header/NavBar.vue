<template>
  <div class="main-container">
    <!-- 顶部栏 -->
    <div class="nav-bar-container">
      <div class="left-container">
        <!-- 侧边栏控制 -->
        <div class="collapse-span">
          <a-icon
              class="collapse-icon"
              :type="collapsed?'menu-unfold':'menu-fold'"
              @click="changeMenuStatus"/>
        </div>
        <!--面包屑-->
        <bread :key="$route.fullPath" class="bread-container"/>
      </div>
      <!--右侧部分-->
      <div class="right-container">

        <!-- 全屏图标 -->
        <a-tooltip>
          <template slot="title">全屏</template>
          <a-icon
              @click="updateScreenState()"
              class="full-screen"
              type="fullscreen"/>
        </a-tooltip>
        <!-- 用户身份 -->
        <a-tooltip>
          <template slot="title">用户身份</template>
          <a-tag color="#87d068" style="cursor: pointer;user-select:none;padding: 4px 10px;margin-right: 10px">
            {{ userInfo.role.roleName }}
          </a-tag>
        </a-tooltip>

        <!-- 头像部分 -->
        <a-dropdown class="avatar-box" :trigger="['click']">
          <div>
            <a-avatar class="avatar" :src="userInfo === null?Logo:userInfo.avatar"/>
            <span class="nickname">
              {{ userInfo === null ? '用户' : userInfo.nickname }}
            </span>
          </div>
          <a-menu slot="overlay">
            <a-menu-item disabled>
              <span style="font-weight: bolder;user-select: none">
                {{ getNowStatus }}{{ userInfo === null ? '用户' : userInfo.nickname }}
              </span>
            </a-menu-item>
            <a-menu-divider/>
            <a-menu-item @click="toPerson" style="text-align: center">
              <a-icon type="user"/>
              个人中心
            </a-menu-item>
            <a-menu-item @click="openChangeModel" style="text-align: center">
              <a-icon type="edit"/>
              修改密码
            </a-menu-item>
            <a-menu-divider/>
            <a-menu-item @click="logout" style="text-align: center;">
              <a-icon type="logout"/>
              退出登录
            </a-menu-item>
          </a-menu>
        </a-dropdown>
      </div>
    </div>
    <!-- 历史菜单记录 -->
    <nav-list :key="$route.fullPath" class="nav-list-container"/>

    <!-- 修改密码弹窗 -->
    <a-modal
        v-model="showChangePasswordModel"
        title="修改密码"
        okText="确定"
        cancelText="取消"
        @ok="doUpdate">
      <a-form-model ref="formData" :model="formData" :rules="rules" :label-col="{span:5}" :wrapper-col="{span:18}">
        <a-form-model-item label="旧密码" prop="oldPassword">
          <a-input-password v-model="formData.oldPassword" placeholder="请输入旧密码"/>
        </a-form-model-item>
        <a-form-model-item label="新密码" prop="newPassword">
          <a-input-password v-model="formData.newPassword" placeholder="请输入新密码"/>
        </a-form-model-item>
        <a-form-model-item label="新密码" prop="repeatPassword">
          <a-input-password v-model="formData.repeatPassword" placeholder="请再次输入新密码"/>
        </a-form-model-item>
      </a-form-model>
    </a-modal>
  </div>
</template>


<script>

import NavList from "@/components/layout/header/NavList";
import Bread from "@/components/layout/header/Bread";
import {mapMutations, mapState} from "vuex";
import screenfull from 'screenfull'
import {userLogout} from "@/api/systemApi";
import Logo from "@/assets/images/logo/logo.png";
import {checkResult} from "@/utils/result/resultUtil";
import {updateUserPassword} from "@/api/userAuthApi";

export default {
  name: "Header",
  data() {
    return {
      Logo,
      showChangePasswordModel: false,
      //================== 数据表单 =====================
      rules: {
        oldPassword: [
          {required: true, message: "请输入旧密码！", trigger: 'blur'},
        ],
        newPassword: [
          {required: true, message: "请输入新密码！", trigger: 'blur'},
        ],
        repeatPassword: [
          {required: true, message: "请再次输入新密码！", trigger: 'blur'},
        ],
      },
      formData: {
        oldPassword: null,
        newPassword: null,
        repeatPassword: null
      }
    }
  },
  components: {
    Bread, NavList
  },
  computed: {
    ...mapState(['collapsed', 'userInfo']),
    getNowStatus() {
      let timeNow = new Date();
      let hours = timeNow.getHours();
      let text = ``;
      // 判断当前时间段
      if (hours >= 0 && hours <= 10) {
        text = `早上好，`;
      } else if (hours > 10 && hours <= 14) {
        text = `中午好，`;
      } else if (hours > 14 && hours <= 18) {
        text = `下午好，`;
      } else if (hours > 18 && hours <= 24) {
        text = `晚上好，`;
      }
      return text;
    }
  },
  methods: {
    ...mapMutations(['CHANGE_COLLAPSE_STATUS']),
    // 改变菜单状态
    changeMenuStatus() {
      this.CHANGE_COLLAPSE_STATUS(!this.collapsed);
    },
    // 改变全屏状态
    updateScreenState() {
      screenfull.toggle();
    },
    // 跳转至个人中心
    toPerson() {
      this.$router.push("/person")
    },
    // 打开修改密码弹出层
    openChangeModel() {
      this.showChangePasswordModel = true;
    },
    //执行修改
    doUpdate() {
      this.$refs.formData.validate(async valid => {
        if (valid) {

          if (this.formData.newPassword !== this.formData.repeatPassword) {
            this.$message.error("两次输入的密码不同！");
            return;
          }

          const res = await updateUserPassword(this.formData);
          checkResult(res);
          if (res.success) {
            window.sessionStorage.clear();
            window.location.reload();
            this.$message.success("密码修改成功，请重新登陆！");
          }
        } else {
          return false;
        }
      });
    },
    // 注销登陆
    logout() {
      var that = this;
      this.$confirm({
        title: '提示',
        content: '确定要退出当前登陆用户吗?',
        centered: true,
        okText: '是的',
        okType: 'danger',
        cancelText: '取消',
        onOk() {
          userLogout().then(res => {
            window.sessionStorage.clear();
            that.$router.replace("/login");
          });
        },
      });
    },
  }
}
</script>

<style lang="scss" scoped>

.main-container {
  width: 100%;
  height: 100%;

  .nav-bar-container {
    width: 100%;
    height: 55px;
    display: flex;
    justify-content: space-between;

    .left-container {
      width: 40%;
      display: flex;
      justify-content: left;
      margin-left: 20px;

      .collapse-span {
        height: 100%;
        line-height: 60px;

        .collapse-icon {
          font-size: 24px;
          cursor: pointer;
        }

      }

      .bread-container {
        height: 100%;
        line-height: 50px;
        margin-left: 20px;
      }
    }


    .right-container {
      width: 50%;
      display: flex;
      justify-content: right;
      align-items: center;
      margin-right: 20px;

      .notice-container {
        margin-right: 30px;
        font-size: 24px;
        cursor: pointer;
        position: relative;
      }

      .full-screen {
        font-size: 24px;
        cursor: pointer;
        margin-right: 20px;
      }

      .avatar-box {
        cursor: pointer;
        margin-right: 20px;

        .avatar {
          width: 38px;
          height: 38px;
          margin-right: 10px;
        }

        .nickname {
          font-weight: bold;
          user-select: none
        }
      }
    }
  }

  .nav-list-container {
    width: 100%;

  }
}

</style>
