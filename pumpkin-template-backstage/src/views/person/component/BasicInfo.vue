<template>
  <div class="main-container animate__animated animate__fadeIn">
    <a-form-model ref="formData" :model="formData" :rules="rules" :label-col="{span:2}" :wrapper-col="{span:12}">
      <a-form-model-item label="用户名" prop="username">
        <a-input v-model="formData.username" disabled placeholder="请输入用户名"/>
      </a-form-model-item>
      <a-form-model-item label="昵称" prop="nickname">
        <a-input v-model="formData.nickname" placeholder="请输入昵称"/>
      </a-form-model-item>
      <a-form-model-item label="邮箱" prop="email">
        <a-input v-model="formData.email" placeholder="请输入邮箱"/>
      </a-form-model-item>
      <a-form-model-item label="性别" prop="gender">
        <a-select v-model="formData.gender" placeholder="请选择性别">
          <a-select-option :value="1">
            男生
          </a-select-option>
          <a-select-option :value="0">
            女生
          </a-select-option>
          <a-select-option :value="-1">
            未知
          </a-select-option>
        </a-select>
      </a-form-model-item>
      <a-form-model-item label="个人简介">
        <a-input v-model="formData.personIntro" type="textarea" :rows="8" placeholder="请输入个人简介"/>
      </a-form-model-item>
      <a-form-model-item class="submit-btn">
        <a-button type="primary" @click="doUpdate">
          保存修改
        </a-button>
      </a-form-model-item>
    </a-form-model>
  </div>
</template>

<script>
import {mapMutations, mapState} from "vuex";
import {updateUserInfo} from "@/api/userInfoApi";
import {checkResult} from "@/utils/result/resultUtil";

export default {
  name: "BasicInfo",
  data() {
    return {
      //================== 初始化数据 ====================
      rules: {
        nickname: [
          {required: true, message: "请输入昵称！", trigger: 'blur'},
        ],
        email: [
          {required: true, message: "请输入邮箱！", trigger: 'blur'},
          {
            pattern: /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/,
            message: "邮箱格式不正确！",
            trigger: 'blur'
          },
        ],
        gender: [
          {required: true, message: "请选择性别！", trigger: 'blur'},
        ]
      },
      //================== form表单 ====================
      formData: {
        username: null,
        nickname: null,
        email: null,
        gender: -1,
        personIntro: null
      },
    }
  },
  computed: {
    ...mapState(['userInfo']),
  },
  created() {
    this.initData();
  },
  methods: {
    ...mapMutations(['UPDATE_USERINFO']),
    //================== 初始化方法 ===================
    initData() {
      this.formData.username = this.userInfo.username;
      this.formData.nickname = this.userInfo.nickname;
      this.formData.email = this.userInfo.email;
      this.formData.gender = this.userInfo.gender;
      this.formData.personIntro = this.userInfo.personIntro;
    },
    //===================== 执行更新 =====================
    doUpdate(formName) {
      this.$refs.formData.validate(async valid => {
        if (valid) {
          checkResult(await updateUserInfo(this.formData));
          this.UPDATE_USERINFO(null);
          window.location.reload();
        } else {
          return false;
        }
      });
    }
  }
}
</script>

<style lang="scss" scoped>
.submit-btn {
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>
