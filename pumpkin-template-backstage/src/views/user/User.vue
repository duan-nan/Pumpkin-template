<template>
  <a-card class="main-container animate__animated animate__fadeIn" style="border-radius: 5px">
    <!-- 页面标题 -->
    <page-title/>
    <!-- 页面主要内容 -->
    <div class="page-content">
      <!-- 搜索部分 -->
      <div class="search-container animate__animated animate__flipInX" v-if="!hideSearch">
        <!-- 搜索条件 -->
        <div class="search-box">
          <!-- 用户名 -->
          <div class="search-item">
            <span class="label-title">用户名</span>
            <a-input class="label-component"
                     v-model="condition.keywords"
                     allowClear
                     placeholder="请输入用户名..."/>
          </div>
          <!-- 性别 -->
          <div class="search-item">
            <span class="label-title">性别</span>
            <a-select
                allowClear
                ref="genderSelect"
                placeholder="请选择性别..."
                class="label-component"
                v-model="condition.gender">
              <a-select-option :value="1">
                <a-icon type="smile" theme="twoTone" two-tone-color="#108ee9" class="icon-item"/>
                男生
              </a-select-option>
              <a-select-option :value="0">
                <a-icon type="smile" theme="twoTone" two-tone-color="#f50" class="icon-item"/>
                女生
              </a-select-option>
              <a-select-option :value="-1">
                <a-icon type="frown" theme="twoTone" two-tone-color="red" class="icon-item"/>
                未知
              </a-select-option>
            </a-select>
          </div>
          <!-- 用户角色 -->
          <div class="search-item">
            <span class="label-title">用户角色</span>
            <a-select
                allowClear
                placeholder="请选角色..."
                class="label-component"
                v-model="condition.roleId">
              <a-select-option
                  v-for="item in basicRoleList"
                  :key="item.id"
                  :value="item.id">
                {{ item.roleName }}
              </a-select-option>
            </a-select>
          </div>
          <!-- 创建时间 -->
          <div class="search-item">
            <span class="label-title">创建时间</span>
            <a-range-picker
                v-model="timeValue"
                :locale="locale"
                @change="dateChange"
                class="label-component"/>
          </div>
        </div>
        <!-- 搜索按钮 -->
        <div class="search-box">
          <!-- 是否禁用 -->
          <div class="search-item">
            <span class="label-title">是否禁用</span>
            <a-select
                allowClear
                placeholder="请选择禁用状态..."
                class="label-component"
                v-model="condition.isDisabled">
              <a-select-option :value="0">
                <a-icon type="check-circle" theme="twoTone" class="icon-item"/>
                正常
              </a-select-option>
              <a-select-option :value="1">
                <a-icon type="close-circle" theme="twoTone" two-tone-color="red" class="icon-item"/>
                禁用
              </a-select-option>
            </a-select>
          </div>
          <!-- 登录方式 -->
          <div class="search-item">
            <span class="label-title">登录方式</span>
            <a-select
                allowClear
                placeholder="请选择登录方式..."
                class="label-component"
                v-model="condition.loginType">
              <a-select-option :value="1">
                <a-icon type="user" class="icon-item"/>
                用户名
              </a-select-option>
              <a-select-option :value="2">
                <a-icon type="mail" class="icon-item"/>
                邮箱
              </a-select-option>
              <a-select-option :value="3">
                <a-icon type="qq" class="icon-item"/>
                QQ
              </a-select-option>
              <a-select-option :value="4">
                <a-icon type="wechat" class="icon-item"/>
                微信
              </a-select-option>
            </a-select>
          </div>
          <!-- 操作按钮 -->
          <div class="search-item">
            <a-button style="margin-right: 10px"
                      size="small"
                      type="primary"
                      icon="search"
                      @click="initData">
              查询
            </a-button>
            <a-button icon="reload"
                      size="small"
                      @click="resetCondition">
              重置
            </a-button>
          </div>
        </div>
      </div>
      <a-divider style="margin: 15px 0"></a-divider>
      <!-- 操作部分 -->
      <div class="operate-btn-container">
        <div class="operate-left-box">
          <a-button class="operate-left-item"
                    size="small"
                    type="primary"
                    icon="plus"
                    @click="toSaveOrUpdate(null)">
            新增
          </a-button>
          <a-button class="operate-left-item"
                    :disabled="deleteList.length === 0"
                    size="small"
                    @click="deleteMany"
                    type="danger"
                    icon="delete">
            批量删除
          </a-button>
        </div>
        <div class="operate-right-box">
          <a-tooltip placement="top">
            <template slot="title">
              <span>{{ hideSearch ? '展示搜索' : '隐藏搜索' }}</span>
            </template>
            <a-button

                style="margin-right: 10px"
                size="small"
                @click="hideSearch = !hideSearch"
                :icon="hideSearch ? 'zoom-in':'zoom-out'"/>
          </a-tooltip>

          <a-tooltip placement="top">
            <template slot="title">
              <span>刷新</span>
            </template>
            <a-button
                @click="initData"
                style="margin-right: 10px"
                size="small"
                icon="sync"></a-button>
          </a-tooltip>
        </div>
      </div>
      <!-- 表格数据 -->
      <a-spin
          :spinning="loading"
          tip="正在努力拉取数据...">
        <!--表格内容-->
        <a-table
            :scroll="{x: 1650}"
            :rowSelection="rowSelectList"
            size="small"
            bordered
            :data-source="userList"
            :rowKey="(user) => user.id"
            :pagination="false">
          <a-table-column
              align="center"
              width="10%"
              ellipsis="true"
              title="用户名"
              data-index="username">
            <template slot-scope="username">
              <a-tooltip>
                <template slot="title">
                  {{ username }}
                </template>
                {{ username }}
              </a-tooltip>
            </template>
          </a-table-column>
          <a-table-column
              align="center"
              width="10%"
              ellipsis="true"
              title="用户昵称"
              data-index="nickname">
            <template slot-scope="nickname">
              <a-tooltip>
                <template slot="title">
                  {{ nickname }}
                </template>
                {{ nickname }}
              </a-tooltip>
            </template>
          </a-table-column>
          <a-table-column
              align="center"
              width="8%"
              title="用户头像"
              data-index="avatar">
            <template slot-scope="avatar">
              <viewer :images="[avatar]">
                <a-avatar style="cursor: pointer" shape="square" size="large" :src="avatar" alt="无"/>
              </viewer>
            </template>
          </a-table-column>
          <a-table-column
              align="center"
              width="6%"
              ellipsis="true"
              title="性别"
              data-index="gender">
            <template slot-scope="gender">
              <a-tag :color="getColor(gender).color" class="tag-item">
                {{ getColor(gender).name }}
              </a-tag>
            </template>
          </a-table-column>
          <a-table-column
              align="center"
              width="8%"
              ellipsis="true"
              title="用户身份"
              data-index="roleName">
            <template slot-scope="roleName">
              <a-tag :color="getColor(roleName)" class="tag-item">
                {{ roleName }}
              </a-tag>
            </template>
          </a-table-column>
          <a-table-column
              align="center"
              width="10%"
              ellipsis="true"
              title="邮箱"
              data-index="email">
            <template slot-scope="email">
              <a-tooltip>
                <template slot="title">
                  {{ email }}
                </template>
                {{ email }}
              </a-tooltip>
            </template>
          </a-table-column>
          <a-table-column
              align="center"
              width="10%"
              ellipsis="true"
              title="ip地址"
              data-index="ipAddress">
            <template slot-scope="ipAddress">
              <a-tooltip>
                <template slot="title">
                  {{ ipAddress }}
                </template>
                {{ ipAddress }}
              </a-tooltip>
            </template>
          </a-table-column>
          <a-table-column
              align="center"
              width="10%"
              ellipsis="true"
              title="ip来源地"
              data-index="ipSource">
            <template slot-scope="ipSource">
              <a-tooltip>
                <template slot="title">
                  {{ ipSource }}
                </template>
                {{ ipSource }}
              </a-tooltip>
            </template>
          </a-table-column>
          <a-table-column
              align="center"
              width="7%"
              title="用户状态"
              data-index="isDisabled">
            <template slot-scope="isDisabled">
              <a-switch
                  disabled
                  checked-children="正常"
                  un-checked-children="禁用"
                  :default-checked="!isDisabled"/>
            </template>
          </a-table-column>
          <a-table-column
              align="center"
              width="15%"
              title="创建时间"
              data-index="gmtCreate">
            <template slot-scope="gmtCreate">
              <a-icon type="bell"/>
              {{ gmtCreate }}
            </template>
          </a-table-column>
          <a-table-column
              align="center"
              width="15%"
              title="上次登录时间"
              data-index="lastLoginTime">
            <template slot-scope="lastLoginTime">
              <a-icon type="bell"/>
              {{ lastLoginTime === null ? "账号仅注册未登陆" : lastLoginTime }}
            </template>
          </a-table-column>
          <a-table-column
              width="280px"
              align="center"
              fixed="right"
              title="操作">
            <template slot-scope="scope"
                      v-if="scope.roleName !== '超级管理员'">
              <a-button
                  style="margin-right: 10px"
                  size="small"
                  icon="edit"
                  @click="toSaveOrUpdate(scope)">
                编辑
              </a-button>
              <a-button
                  style="margin-right: 10px"
                  type="primary"
                  icon="control"
                  size="small"
                  @click="toResetPassword(scope.id)">
                重置密码
              </a-button>
              <a-button
                  size="small"
                  type="danger"
                  icon="delete"
                  @click="deleteOne(scope.id)">
                删除
              </a-button>
            </template>
          </a-table-column>
        </a-table>
      </a-spin>
      <!-- 分页 -->
      <a-pagination
          class="page-center"
          :total="total"
          show-size-changer
          :defaultPageSize="8"
          :pageSizeOptions="['8', '16', '24', '32']"
          :show-total="(total) => `共有 ${total} 个用户`"
          @change="currentChange"
          @showSizeChange="sizeChange">
        <template slot="buildOptionText" slot-scope="props">
          <span v-if="props.value !== '50'">{{ props.value }}条/页</span>
        </template>
      </a-pagination>
    </div>

    <!-- 抽屉弹窗 -->
    <a-drawer
        :title="drawerIsSave ? '新增用户':'修改用户信息'"
        :width="680"
        :visible="saveOrUpdateDrawerShow"
        @close="cancelSaveOrUpdate">
      <a-form-model
          ref="formData"
          :model="formData"
          :rules="rules"
          layout="vertical">
        <!-- 用户头像 -->
        <a-row :gutter="16">
          <a-form-model-item label="用户头像：" prop="avatar">
            <a-upload name="file"
                      class="upload-item-avatar"
                      :show-upload-list="false"
                      accept="image/*"
                      :customRequest="uploadAvatar">
              <img class="img-item"
                   :src="formData.avatar"
                   v-if="formData.avatar !== undefined && formData.avatar !== ''"
                   alt="avatar"/>
              <div class="text-item" v-else>
                <a-icon :type="uploadLoading ? 'loading' : 'contacts'"/>
                <div class="ant-upload-text">用户头像</div>
              </div>
            </a-upload>
          </a-form-model-item>
        </a-row>

        <!-- 用户名/昵称 -->
        <a-row :gutter="16">
          <a-col :span="12">
            <a-form-model-item label="用户名：" prop="username">
              <a-input v-model="formData.username" :disabled="!drawerIsSave" placeholder="请输入用户名..."/>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="用户昵称：" prop="nickname">
              <a-input v-model="formData.nickname" placeholder="请输入用户昵称..."/>
            </a-form-model-item>
          </a-col>
        </a-row>

        <!-- 密码/确认密码 -->
        <a-row :gutter="16" v-if="drawerIsSave">
          <a-col :span="12">
            <a-form-model-item label="用户密码：" prop="password">
              <a-input-password v-model="formData.password" placeholder="请输入用户密码..."/>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="确认密码：" prop="rePassword">
              <a-input-password v-model="formData.rePassword" placeholder="请再次输入用户密码..."/>
            </a-form-model-item>
          </a-col>
        </a-row>

        <!-- 性别/邮箱 -->
        <a-row :gutter="16">
          <a-col :span="12">
            <a-form-model-item label="性别：" prop="gender">
              <a-select
                  allowClear
                  ref="genderSelect"
                  placeholder="请选择性别..."
                  class="label-component"
                  v-model="formData.gender">
                <a-select-option :value="1">
                  <a-icon type="smile" theme="twoTone" two-tone-color="#108ee9" class="icon-item"/>
                  男生
                </a-select-option>
                <a-select-option :value="0">
                  <a-icon type="smile" theme="twoTone" two-tone-color="#f50" class="icon-item"/>
                  女生
                </a-select-option>
                <a-select-option :value="-1">
                  <a-icon type="frown" theme="twoTone" two-tone-color="red" class="icon-item"/>
                  未知
                </a-select-option>
              </a-select>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="用户邮箱：" prop="email">
              <a-input v-model="formData.email" placeholder="请输入用户邮箱..."/>
            </a-form-model-item>
          </a-col>
        </a-row>

        <!-- 角色/状态 -->
        <a-row :gutter="16">
          <a-col :span="12">
            <a-form-model-item label="角色：" prop="roleId">
              <a-select
                  allowClear
                  placeholder="请选角色..."
                  class="label-component"
                  v-model="formData.roleId">
                <a-select-option
                    v-for="item in basicRoleList"
                    :key="item.id"
                    :value="item.id">
                  {{ item.roleName }}
                </a-select-option>
              </a-select>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="状态" prop="isDisabled">
              <a-radio-group v-model="formData.isDisabled">
                <a-radio :value="0">
                  在用
                </a-radio>
                <a-radio :value="1">
                  禁用
                </a-radio>
              </a-radio-group>
            </a-form-model-item>
          </a-col>
        </a-row>

        <!--  设备来源/注册来源  -->
        <a-row :gutter="16">
          <a-col :span="12">
            <a-form-model-item label="设备来源：">
              <a-select
                  allowClear
                  ref="genderSelect"
                  placeholder="请选择设备来源..."
                  class="label-component"
                  v-model="formData.deviceType">
                <a-select-option v-for="item in deviceTypeList"
                                 :key="item.value"
                                 :value="item.value">
                  {{ item.name }}
                </a-select-option>
              </a-select>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="注册来源">
              <a-select
                  allowClear
                  ref="genderSelect"
                  placeholder="请选择注册来源..."
                  class="label-component"
                  v-model="formData.registeredSource">
                <a-select-option v-for="item in registeredSourceList"
                                 :key="item.value"
                                 :value="item.value">
                  {{ item.name }}
                </a-select-option>
              </a-select>
            </a-form-model-item>
          </a-col>
        </a-row>

        <a-row :gutter="16">
          <a-form-model-item label="个人简介">
            <a-input v-model="formData.personIntro" type="textarea" :rows="8" placeholder="请输入个人简介"/>
          </a-form-model-item>
        </a-row>
      </a-form-model>

      <div class="drawer-footer-btn">
        <a-button style="margin-right: 10px" @click="cancelSaveOrUpdate">取消</a-button>
        <a-button type="primary" @click="doSaveOrUpdate">确定</a-button>
      </div>
    </a-drawer>

    <!-- 修改密码弹窗 -->
    <a-modal
        v-model="resetPasswordModelShow"
        title="修改密码"
        okText="确定"
        cancelText="取消"
        @ok="doResetPassword">
      <a-form-model ref="passwordForm" :model="passwordForm" :rules="rules" :label-col="{span:5}"
                    :wrapper-col="{span:18}">
        <a-form-model-item label="新密码" prop="password">
          <a-input-password v-model="passwordForm.password" placeholder="请输入新密码"/>
        </a-form-model-item>
        <a-form-model-item label="新密码" prop="rePassword">
          <a-input-password v-model="passwordForm.rePassword" placeholder="请再次输入新密码"/>
        </a-form-model-item>
      </a-form-model>
    </a-modal>
  </a-card>
</template>

<script>
import PageTitle from "@/components/layout/page/PageTitle";
import locale from "ant-design-vue/lib/date-picker/locale/zh_CN";
import {listUser, resetPassword, saveUser, updateUser,batchDeleteUser} from "@/api/userApi";
import {listRoleBasic} from "@/api/roleApi";
import {uploadPicture} from "@/api/uploadApi";
import {mapState} from "vuex";
import {listDeviceType, listRegisteredSource} from "@/api/EnumApi";
import {checkResult} from "@/utils/result/resultUtil";
import {clearFormData, deleteManyData} from "@/utils/common/common";

export default {
  name: "User",
  data() {
    return {
      //================= 初始化参数 =====================
      locale,
      loading: false,
      uploadLoading: false,
      hideSearch: false,
      saveOrUpdateDrawerShow: false,
      resetPasswordModelShow: false,
      drawerIsSave: null,
      timeValue: [],
      deleteList: [],
      userList: [],
      total: 0,
      currentImages: [],
      basicRoleList: [],
      deviceTypeList: [],
      registeredSourceList: [],
      rules: {
        username: [{required: true, message: "请输入用户名！", trigger: 'blur'}],
        nickname: [{required: true, message: "请输入昵称！", trigger: 'blur'}],
        password: [{required: true, message: "请输入密码！", trigger: 'blur'}],
        rePassword: [{required: true, message: "请再次输入密码！", trigger: 'blur'}],
        email: [
          {
            pattern: /^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\.[a-zA-Z0-9-]+)*\.[a-zA-Z0-9]{2,6}$/,
            message: "邮箱格式不正确！",
            trigger: 'blur'
          }
        ],
        gender: [{required: true, message: "请选择性别！", trigger: 'blur'}],
        roleId: [{required: true, message: "请选择角色！", trigger: 'blur'}],
        isDisabled: [{required: true, message: "请选择用户状态！", trigger: 'blur'}]
      },
      //===================  form表单  ==================
      condition: {
        pageNow: 1,
        pageSize: 8,
        keywords: undefined,
        gender: undefined,
        roleId: undefined,
        isDisabled: undefined,
        loginType: undefined,
        beginTime: undefined,
        endTime: undefined
      },
      formData: {
        id: undefined,
        avatar: undefined,
        username: undefined,
        nickname: undefined,
        password: undefined,
        rePassword: undefined,
        gender: -1,
        email: undefined,
        roleId: undefined,
        isDisabled: 0,
        deviceType: undefined,
        registeredSource: undefined,
        personIntro: undefined
      },
      passwordForm: {
        id: undefined,
        password: undefined,
        rePassword: undefined
      }
    }
  },
  components: {
    PageTitle,
  },
  computed: {
    ...mapState(['userInfo']),
    rowSelectList() {
      return {
        onChange: (keys, rows) => {
          this.deleteList = keys;
        },
      };
    },
    getColor() {
      return function (roleName) {
        switch (roleName) {
          case "超级管理员":
            return "#108ee9";
          case "管理员":
            return "#2db7f5";
          case 0:
            return {
              name: "女",
              color: "pink"
            };
          case 1:
            return {
              name: "男",
              color: "cyan"
            };
          case -1:
            return {
              name: "未知",
              color: "purple"
            };
          default:
            return "blue";
        }
      }
    },
  },
  created() {
    this.initData();
    this.initSelect();
  },
  methods: {
    //=======================  初始化  ===========================
    async initData() {
      this.loading = true;
      const res = await listUser(this.condition);
      if (res.success) {
        this.userList = res.data.dataList;
        this.total = res.data.totalCount;
      }
      this.loading = false;
    },
    async initSelect() {
      // 获取角色下拉框信息
      const roleRes = await listRoleBasic();
      if (roleRes.success) {
        this.basicRoleList = roleRes.data;
      }

      // 获取设备类型枚举
      const deviceRes = await listDeviceType();
      if (deviceRes.success) {
        this.deviceTypeList = deviceRes.data;
      }

      // 获取注册来源枚举
      const registeredRes = await listRegisteredSource();
      if (registeredRes.success) {
        this.registeredSourceList = registeredRes.data;
      }

    },
    //=======================  交互方法  ===========================
    dateChange(date, dateString) {
      if (dateString[0] !== '' && dateString[1] !== '') {
        dateString[0] = dateString[0] + " 00:00:01";
        dateString[1] = dateString[1] + " 23:59:59";
      }
      this.condition.beginTime = dateString[0];
      this.condition.endTime = dateString[1];
    },
    resetCondition() {
      this.condition.keywords = undefined;
      this.condition.gender = undefined;
      this.condition.roleId = undefined;
      this.condition.isDisabled = undefined;
      this.condition.loginType = undefined;
      this.condition.beginTime = undefined;
      this.condition.endTime = undefined;
      this.timeValue = [];
      this.initData();
    },
    resetFormData() {
      //============== 用户表单 ===============
      this.saveOrUpdateDrawerShow = false;
      this.drawerIsSave = null;
      this.formData.id = undefined;
      this.formData.avatar = undefined;
      this.formData.username = undefined;
      this.formData.nickname = undefined;
      this.formData.password = undefined;
      this.formData.rePassword = undefined;
      this.formData.gender = -1;
      this.formData.email = undefined;
      this.formData.roleId = undefined;
      this.formData.isDisabled = 0;
      this.formData.deviceType = undefined;
      this.formData.registeredSource = undefined;
      this.formData.personIntro = undefined;
      //============== 密码表单 ===============
      this.resetPasswordModelShow = false;
      this.passwordForm.id = undefined;
      this.passwordForm.password = undefined;
      this.passwordForm.rePassword = undefined;
      this.initData();
    },
    //=======================  新增/更新  ===========================
    toSaveOrUpdate(data) {
      this.drawerIsSave = data === null;
      this.formData = this.drawerIsSave ? this.formData : data;
      this.saveOrUpdateDrawerShow = true;
    },
    async doSaveOrUpdate() {
      this.$refs.formData.validate(async valid => {
        if (valid) {
          if (this.drawerIsSave && this.formData.password !== this.formData.rePassword) {
            this.$message.error("两次密码不一致！");
            return;
          }
          this.drawerIsSave ? checkResult(await saveUser(this.formData)) : checkResult(await updateUser(this.formData));
          this.resetFormData();
        }
      });
    },
    cancelSaveOrUpdate() {
      clearFormData(this.resetFormData);
    },
    toResetPassword(userId) {
      this.passwordForm.id = userId;
      this.resetPasswordModelShow = true;
    },
    doResetPassword() {
      this.$refs.passwordForm.validate(async valid => {
        if (valid) {
          if (this.passwordForm.password !== this.passwordForm.rePassword) {
            this.$message.error("两次密码不一致！");
            return;
          }
          checkResult(await resetPassword(this.passwordForm.id, this.passwordForm.password));
          this.resetFormData();
        }
      });
    },
    //=======================  删除  ===========================
    deleteOne(userId) {
      deleteManyData("用户", batchDeleteUser, [userId], this.initData);
    },
    deleteMany() {
      deleteManyData("用户", batchDeleteUser, this.deleteList, this.initData);
    },
    //=======================  分页  ===========================
    sizeChange(current, size) {
      this.condition.pageSize = size;
      this.initData();
    },
    currentChange(current, size) {
      this.condition.pageNow = current;
      this.initData();
    },
    //=======================  上传  ===========================
    async uploadAvatar(info) {
      this.uploadLoading = true;
      const res = await uploadPicture(info.file, "/user", `/avatar/${this.userInfo.id}/`);
      if (res.success) {
        this.formData.avatar = res.data;
        this.uploadLoading = false;
        this.$notification.success({message: res.message});
      }
    },
  }
}
</script>

<style lang="scss" scoped>

.tag-item {
  user-select: none;
  cursor: pointer;
}

.icon-item {
  margin-right: 10px;
}

</style>
