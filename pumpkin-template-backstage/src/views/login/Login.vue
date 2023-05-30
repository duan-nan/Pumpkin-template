<template>
  <div class="main-container"
       :style="`background: url(${backstageInfo=== null ? Backstage:backstageInfo.background}) center center / cover no-repeat;`">
    <!-- 登录部分 -->
    <div class="login-container">
      <!-- 登录界面 -->
      <div class="login-box">
        <!-- 标题部分 -->
        <div class="title-span">
          <img class="logo" :src="backstageInfo.logo">
          <span class="title">{{ backstageInfo === null ? "后台管理系统" : backstageInfo.websiteTitle }}</span>
        </div>
        <!-- 登录表单 -->
        <a-form-model class="login" ref="ruleForm" @submit="login('ruleForm')" @submit.native.prevent :model="loginForm"
                      :rules="rules">
          <a-form-model-item prop="username">
            <a-input v-model="loginForm.username" placeholder="请输入用户名..."/>
          </a-form-model-item>
          <a-form-model-item prop="password">
            <a-input-password v-model="loginForm.password" placeholder="请输入密码..."/>
          </a-form-model-item>
          <!-- 滑块验证码 -->
          <a-form-model-item prop="captcha">
            <SliderVerificationCode height="35px"
                                    content="请拖动滑块进行验证"
                                    inactiveValue="未验证"
                                    activeValue="已验证"
                                    v-model="isPass"/>
          </a-form-model-item>
          <div class="remember-me">
            <a-checkbox dio>记住我</a-checkbox>
            <a href="#">忘记密码</a>
          </div>
          <a-form-model-item>
            <a-button
                block
                class="login-button"
                type="primary"
                html-type="submit"
                @submit.native.prevent
                :disabled="loginForm.username === '' || loginForm.password === ''"
            >
              <a-icon type="login"/>
              登陆
            </a-button>
          </a-form-model-item>
        </a-form-model>
        <!--第三方登陆-->
        <a-divider style="margin: -15px 0 10px 0">第三方登陆</a-divider>
        <div class="three-box">
          <a-button type="primary" shape="circle" icon="qq"></a-button>
          <a-button type="primary" shape="circle" icon="alipay" style="margin-left: 10px"></a-button>
        </div>
      </div>
      <!-- 页脚部分 -->
      <div class="footer-span">
        <a-divider>{{ backstageInfo === null ? "Copyright © 2023 by ~ 段楠" : backstageInfo.copyright }}</a-divider>
      </div>
    </div>
    <!--  其他部分 -->
    <div class="other-container">
      <!-- 网站标题 -->
      <div class="website-detail-title">
        <span>{{ backstageInfo === null ? "后台管理系统" : backstageInfo.websiteName }}</span>
      </div>
      <!-- 网站标语 -->
      <div class="website-desc">
        <span>———&emsp;{{ backstageInfo === null ? "生如芥子，心藏须弥。" : backstageInfo.websitePoster }}&emsp;———</span>
      </div>
    </div>
  </div>
</template>

<script>


import Backstage from "@/assets/images/backstage.jpg";
import Logo from "@/assets/images/logo/logo.png";
import {userLogin} from "@/api/systemApi";
import {mapMutations} from "vuex";
import {getWebsiteConfig} from "@/api/websiteConfigApi";

export default {
  name: "Login",
  data() {
    return {
      //==============   初始化   ===============
      rules: {
        username: [{required: true, message: '用户名不能为空', trigger: 'blur'}],
        password: [{required: true, message: '密码不能为空', trigger: 'blur'}],
      },
      backstageInfo: {},
      isPass: false,
      //==============   参数对象   ===============
      loginForm: {
        username: "Test",
        password: "123456",
      },
      Backstage: Backstage,
      Logo: Logo,
    }
  },
  created() {
    this.initData();
  },
  methods: {
    ...mapMutations(['UPDATE_USERINFO']),
    //================== 初始化方法 ===================
    async initData() {
      const res = await getWebsiteConfig(2);
      if (res.success) {
        this.backstageInfo = res.data;
      }
    },
    //================== 登陆方法 ==================
    login(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          if (this.isPass) {
            userLogin(this.loginForm).then(async res => {
              if (res.success) {
                window.sessionStorage.setItem('Authorization', res.data);
                this.$notification.success({message: res.message});
                this.$router.replace('/home');
              }
            });
          } else {
            this.$message.error("未通过滑块验证");
          }
        }
      });
    }

  }
}
</script>

<style lang="scss" scoped>
.main-container {
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: space-between;

  .login-container {
    position: relative;
    width: 26%;
    height: 100%;
    opacity: 0.95;
    background: white;
    border-right: 1px solid gray;
    border-radius: 10px;
    box-shadow: 2px 10px 20px gray;


    .login-box {
      position: absolute;
      width: 100%;
      height: auto;
      padding: 10px 0 20px 0;
      margin: 0 auto;
      top: 15%;

      .title-span {
        display: flex;
        justify-content: center;
        margin-bottom: 40px;

        .logo {
          user-select: none;
          width: 38px;
          height: 38px;
          margin-right: 10px;
          border-radius: 50%;
        }

        .title {
          user-select: none;
          font-size: 24px;
          font-weight: bolder;

        }
      }

      .login {
        padding: 0 30px;

        .remember-me {
          display: flex;
          justify-content: space-between;
        }

        .login-button {
          margin-top: 10px;
          width: 100%;
        }
      }

      .three-box {
        display: flex;
        justify-content: center;
      }

    }


    .footer-span {
      user-select: none;
      position: absolute;
      bottom: 1%;
      left: 0;
      right: 0;
    }
  }

  .other-container {
    width: 74%;
    position: relative;

    .website-detail-title {
      position: absolute;
      width: 100%;
      text-align: center;
      top: 8%;

      span {
        color: white;
        user-select: none;
        font-weight: bolder;
        font-size: 40px;
      }
    }

    .website-desc {
      position: absolute;
      width: 100%;
      text-align: center;
      bottom: 5%;


      span {
        user-select: none;
        font-size: 20px;
        color: white;
      }
    }
  }

}
</style>
