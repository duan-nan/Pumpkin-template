<template>
  <a-card class="animate__animated animate__fadeIn" style="border-radius: 5px">

    <!-- 页面标题 -->
    <page-title/>

    <div class="page-content">
      <!-- 搜索部分 -->
      <div class="search-container animate__animated animate__flipInX" v-if="!hideSearch">
        <!-- 搜索条件 -->
        <div class="search-box">
          <!-- 角色名称 -->
          <div class="search-item">
            <span class="label-title">角色名称</span>
            <a-input class="label-component"
                     v-model="condition.keywords"
                     allowClear
                     placeholder="请输入角色名称..."/>
          </div>
          <!-- 角色标识 -->
          <div class="search-item">
            <span class="label-title">角色标识</span>
            <a-input class="label-component"
                     v-model="condition.roleLabel"
                     allowClear
                     placeholder="请输入角色标识..."/>
          </div>
          <!-- 角色状态 -->
          <div class="search-item">
            <span class="label-title">角色状态</span>
            <a-select
                allowClear
                placeholder="请选择角色状态..."
                class="label-component"
                v-model="condition.isDisabled">
              <a-select-option value="false">
                <a-icon type="check-circle" theme="twoTone" class="icon-item"/>
                正常
              </a-select-option>
              <a-select-option value="true">
                <a-icon type="close-circle" theme="twoTone" two-tone-color="red" class="icon-item"/>
                禁用
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
                    @click="toSaveOrUpdate()">
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
            :scroll="{x: 1300}"
            :rowSelection="rowSelectList"
            size="small"
            bordered
            :data-source="roleList"
            :rowKey="(role) => role.id"
            :pagination="false">
          <a-table-column
              align="center"
              width="20%"
              title="角色名称"
              data-index="roleName"/>
          <a-table-column
              align="center"
              width="20%"
              title="角色标识"
              data-index="roleLabel"/>
          <a-table-column
              align="center"
              width="10%"
              title="权重"
              data-index="orderNum"/>
          <a-table-column
              align="center"
              width="15%"
              title="角色状态"
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
              title="用户数量"
              data-index="userCount"/>
          <a-table-column
              align="center"
              width="20%"
              title="创建时间"
              data-index="gmtCreate">
            <template slot-scope="gmtCreate">
              <a-icon type="bell"/>
              {{ gmtCreate }}
            </template>
          </a-table-column>
          <a-table-column
              width="280px"
              align="center"
              fixed="right"
              title="操作">
            <template slot-scope="scope"
                      v-if="scope.roleLabel !== 'super_admin'">
              <a-button
                  style="margin-right: 10px"
                  type="primary"
                  icon="control"
                  size="small"
                  @click="toPermsUpdate(scope)">
                权限分配
              </a-button>
              <a-button
                  style="margin-right: 10px"
                  size="small"
                  icon="edit"
                  @click="toSaveOrUpdate(scope)">
                编辑
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
          :show-total="(total) => `共有 ${total} 个角色`"
          @change="currentChange"
          @showSizeChange="sizeChange">
        <template slot="buildOptionText" slot-scope="props">
          <span v-if="props.value !== '50'">{{ props.value }}条/页</span>
        </template>
      </a-pagination>

      <!--弹出层-->
      <a-drawer
          :title="roleDrawerIsSave ? '新增角色':'修改角色'"
          placement="right"
          width="480"
          :visible="roleDrawerIsShow"
          @close="cancelSaveOrUpdate">
        <a-form-model
            ref="ruleForm"
            :label-col="{ span: 5 }"
            :wrapper-col="{ span: 19 }"
            :model="formData"
            :rules="rules">
          <a-form-model-item
              label="角色名称"
              prop="roleName">
            <a-input
                v-model="formData.roleName"
                autocomplete="off"/>
          </a-form-model-item>
          <a-form-model-item
              label="角色标识"
              prop="roleLabel">
            <a-input
                v-model="formData.roleLabel"
                autocomplete="off"/>
          </a-form-model-item>
          <a-form-model-item label="角色排序">
            <a-input-number
                id="inputNumber"
                v-model="formData.orderNum"
                :min="1"/>
          </a-form-model-item>
          <a-form-model-item label="角色标识">
            <a-radio-group
                v-model="formData.isDisabled">
              <a-radio :value="false">正常</a-radio>
              <a-radio :value="true">禁用</a-radio>
            </a-radio-group>
          </a-form-model-item>
        </a-form-model>
        <div class="drawer-footer-btn">
          <a-button
              style="margin-right: 10px"
              @click="cancelSaveOrUpdate">取消
          </a-button>
          <a-button
              type="primary"
              @click="doSaveOrUpdate">确定
          </a-button>
        </div>
      </a-drawer>

      <!--权限分配-->
      <a-drawer
          title="权限分配"
          :width="480"
          :visible="permsDrawerIsShow"
          @close="cancelPermUpdate">
        <a-descriptions :column="1">
          <a-descriptions-item label="角色名称">
            {{ currentRole.roleName }}
          </a-descriptions-item>
          <a-descriptions-item label="角色编码">
            {{ currentRole.roleLabel }}
          </a-descriptions-item>
        </a-descriptions>
        <a-tree checkable
                :checked-keys="defaultMenu"
                :replace-fields="replaceFields"
                :tree-data="menuList"
                @check="onCheck"/>
        <div class="drawer-footer-btn">
          <a-button style="margin-right: 10px" @click="cancelPermUpdate">取消</a-button>
          <a-button type="primary" @click="doPermsUpdate">确定</a-button>
        </div>
      </a-drawer>
    </div>
  </a-card>
</template>

<script>

import {batchDeleteRole, deleteRole, getRole, listRole, saveRole, updatePerm, updateRole} from "@/api/roleApi";
import {listMenuPerm} from "@/api/menuApi";
import {checkResult} from "@/utils/result/resultUtil";
import locale from 'ant-design-vue/es/date-picker/locale/zh_CN';
import PageTitle from "@/components/layout/page/PageTitle";
import {clearFormData, deleteManyData, deleteOneData} from "@/utils/common/common";

export default {
  name: "Role",
  data() {
    return {
      //=======================  初始化数据  ===========================
      locale,
      hideSearch: false,
      loading: false,
      deleteList: [],
      roleList: [],
      menuList: [],
      total: 0,
      permsDrawerIsShow: false,
      roleDrawerIsShow: false,
      roleDrawerIsSave: null,
      roleTitle: "",
      timeValue: [],
      currentRole: {
        id: "",
        roleName: "",
        roleLabel: ""
      },
      replaceFields: {
        children: "children",
        title: "name",
        key: "id",
      },
      //=======================  条件  ===========================
      condition: {
        pageNow: 1,
        pageSize: 8,
        keywords: null,
        isDisabled: undefined,
        roleLabel: null,
        beginTime: null,
        endTime: null
      },
      rules: {
        roleName: [{required: true, message: "角色名称不能为空！", trigger: "blur"}],
        roleLabel: [{required: true, message: "角色简介不能为空！", trigger: "blur"}],
      },
      //=======================  form  ===========================
      formData: {
        id: undefined,
        roleName: undefined,
        roleLabel: undefined,
        orderNum: 1,
        isDisabled: false,
      },
      defaultMenu: [],
      permForm: {
        roleId: null,
        menuIds: [],
        halfCheckedIds: []
      },
    };
  },
  components: {
    PageTitle,
  },
  computed: {
    rowSelectList() {
      return {
        onChange: (keys, rows) => {
          this.deleteList = keys;
        },
      };
    },
  },
  created() {
    this.initData();
  },
  methods: {
    //=======================  初始化  ===========================
    async initData() {
      this.loading = true;
      const roleRes = await listRole(this.condition);
      if (roleRes.success) {
        this.roleList = roleRes.data.dataList;
        this.total = roleRes.data.totalCount;
      }

      //  菜单权限
      const menuPermRes = await listMenuPerm();
      if (menuPermRes.success) {
        this.menuList = menuPermRes.data;
      }

      this.loading = false;
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
    onCheck(checkedKeys, info) {
      this.defaultMenu = checkedKeys;
      this.permForm.menuIds = checkedKeys;
      this.permForm.halfCheckedIds = info.halfCheckedKeys;
    },
    resetCondition() {
      this.condition.keywords = undefined;
      this.condition.isDisabled = undefined;
      this.condition.roleLabel = undefined;
      this.condition.beginTime = undefined;
      this.condition.endTime = undefined;
      this.timeValue = [];
      this.initData();
    },
    resetFormData() {
      //=========== 角色表单清空 ===============
      this.roleDrawerIsShow = false;
      this.roleDrawerIsSave = null;
      this.formData.id = undefined;
      this.formData.roleName = undefined;
      this.formData.roleLabel = undefined;
      this.formData.orderNum = 1;
      this.formData.isDisabled = false;
      //=========== 权限表单清空 ===============
      this.currentRole = {};
      this.permForm = {};
      this.defaultMenu = [];
      this.permsDrawerIsShow = false;

      this.initData();
    },
    //=======================  新增/更新  ===========================
    toSaveOrUpdate(data) {
      this.roleDrawerIsSave = data === undefined;
      this.formData = this.roleDrawerIsSave ? this.formData : JSON.parse(JSON.stringify(data));
      this.roleDrawerIsShow = true;
    },
    async doSaveOrUpdate() {
      this.$refs.ruleForm.validate(async (valid) => {
            if (valid) {
              this.roleDrawerIsSave ? checkResult(await saveRole(this.formData)) : checkResult(await updateRole(this.formData));
              await this.initData();
              this.resetFormData();
            }
          }
      )
    },
    cancelSaveOrUpdate() {
      clearFormData(this.resetFormData);
    },
    async toPermsUpdate(data) {
      this.currentRole = JSON.parse(JSON.stringify(data));
      this.permForm.roleId = data.id;
      const res = await getRole(data.id);
      if (res.success) {
        this.defaultMenu = res.data.menuIdList;
      }
      this.permsDrawerIsShow = true;
    },
    async doPermsUpdate() {
      checkResult(await updatePerm(this.permForm));
      await this.initData();
      this.resetFormData();
    },
    cancelPermUpdate() {
      clearFormData(this.resetFormData);
    },
    //=======================  删除  ===========================
    deleteOne(id) {
      deleteOneData("角色", batchDeleteRole, [id], this.initData);
    },
    deleteMany() {
      deleteManyData("角色", batchDeleteRole, this.deleteList, this.initData);
    },
    //=======================  分页  ===========================
    sizeChange(current, size) {
      this.condition.pageSize = size;
      this.initData();
    },
    currentChange(current, size) {
      this.condition.pageNow = current;
      this.initData();
    }
  },
};
</script>

<style lang="scss" scoped>

</style>
