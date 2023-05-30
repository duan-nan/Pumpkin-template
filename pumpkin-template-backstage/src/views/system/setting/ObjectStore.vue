<template>
  <a-card class="animate__animated animate__fadeIn" style="border-radius: 5px">
    <!-- 页面标题 -->
    <page-title/>
    <!-- 页面内容 -->
    <div class="page-content">
      <!-- 搜索部分内容 -->
      <div class="search-container animate__animated animate__flipInX" v-if="!hideSearch">
        <!-- 输入框内容 -->
        <div class="search-box">
          <!-- 对象存储名称 -->
          <div class="search-item">
            <span class="label-title" style="width: 50%;">对象存储名称</span>
            <a-input class="label-component"
                     v-model="condition.keywords"
                     allowClear
                     placeholder="请输入对象存储名称..."/>
          </div>
          <!-- 标识 -->
          <div class="search-item">
            <span class="label-title" style="width: 50%;">对象存储标识</span>
            <a-input class="label-component"
                     v-model="condition.objectStoreLabel"
                     allowClear
                     placeholder="请输入对象存储标识..."/>
          </div>
          <!-- 信息是否完善 -->
          <div class="search-item">
            <span class="label-title" style="width: 50%;">信息是否完善</span>
            <a-select
                allowClear
                class="label-component"
                placeholder="请选择是否完善信息..."
                v-model="condition.isPrefect">
              <a-select-option :value="1">
                <a-icon type="check-circle" theme="twoTone" class="icon-item"/>
                已完善
              </a-select-option>
              <a-select-option :value="0">
                <a-icon type="close-circle" theme="twoTone" two-tone-color="red" class="icon-item"/>
                未完善
              </a-select-option>
            </a-select>
          </div>
          <!-- 是否通过上传测试 -->
          <div class="search-item">
            <span class="label-title" style="width: 80%;">是否通过上传测试</span>
            <a-select
                allowClear
                class="label-component"
                placeholder="请选择是否通过测试..."
                v-model="condition.isPassTest">
              <a-select-option :value="1">
                <a-icon type="check-circle" theme="twoTone" class="icon-item"/>
                已通过
              </a-select-option>
              <a-select-option :value="0">
                <a-icon type="close-circle" theme="twoTone" two-tone-color="red" class="icon-item"/>
                未通过
              </a-select-option>
            </a-select>
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

      <!-- 分割线 -->
      <a-divider style="margin: 15px 0"></a-divider>

      <!-- 操作部分按钮 -->
      <div class="operate-btn-container">
        <div class="operate-left-box">
          <a-button
              class="operate-left-item"
              type="primary"
              icon="plus-square"
              @click="toSaveOrUpdate()">
            新增
          </a-button>
          <a-button
              class="operate-left-item"
              :disabled="deleteList.length === 0"
              type="danger"
              icon="delete"
              @click="deleteMany">
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

      <!-- 数据表格 -->
      <a-spin :spinning="loading" tip="正在努力拉取数据...">
        <!--表格内容-->
        <a-table
            :scroll="{x:1650}"
            :rowSelection="rowSelectList"
            :data-source="dataList"
            size="small"
            bordered
            :rowKey="(item) => item.id"
            :pagination="false">
          <a-table-column
              align="center"
              width="4%"
              title="编号"
              data-index="id"/>
          <a-table-column
              align="center"
              width="16%"
              title="对象存储名称"
              data-index="objectStoreName"/>
          <a-table-column
              align="center"
              width="10%"
              title="对象存储标识"
              data-index="objectStoreLabel"/>
          <a-table-column
              align="center"
              width="10%"
              title="上传测试"
              data-index="isPassTest">
            <template slot-scope="isPassTest">
              <a-tag style="user-select: none" :color="isPassTest?'#87d068':'#f50'">
                {{ isPassTest ? "已通过" : "未通过" }}
              </a-tag>
            </template>
          </a-table-column>
          <a-table-column
              align="center"
              width="15%"
              title="存储桶名称/服务器路径"
              data-index="bucket"/>
          <a-table-column
              align="center"
              width="30%"
              title="域名"
              data-index="domainUrl"/>
          <a-table-column
              align="center"
              width="20%"
              title="地域节点/服务ip"
              data-index="endpoint"/>
          <a-table-column
              align="center"
              width="10%"
              title="是否完善"
              data-index="isPerfect">
            <template slot-scope="isPerfect">
              <a-tag style="user-select: none" :color="isPerfect?'#87d068':'#f50'">
                {{ isPerfect ? "已完善" : "未完善" }}
              </a-tag>
            </template>
          </a-table-column>
          <a-table-column
              align="center"
              width="300px"
              fixed="right"
              title="操作">
            <template slot-scope="scope">
              <div v-if="scope.objectStoreLabel !== 'Project'">
                <a-upload
                    :disabled="!scope.isPerfect || scope.isPassTest"
                    style="display: inline"
                    name="file"
                    :showUploadList="false"
                    :customRequest="uploadTest">
                  <a-button
                      :disabled="!scope.isPerfect || scope.isPassTest"
                      @click="getSelectRow(scope)"
                      style="margin-right: 10px"
                      size="small"
                      icon="cloud-upload">
                    上传测试
                  </a-button>
                </a-upload>
                <a-button
                    style="margin-right: 10px"
                    size="small"
                    type="primary"
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
              </div>
            </template>
          </a-table-column>
        </a-table>
      </a-spin>

      <!-- 分页 -->
      <a-pagination
          class="page-center"
          :total="total" show-size-changer :defaultPageSize="10"
          :pageSizeOptions="['10', '20', '30']"
          :show-total="(total) => `共有 ${total} 条对象存储记录`"
          @change="currentChange"
          @showSizeChange="sizeChange">
        <template slot="buildOptionText" slot-scope="props">
          <span v-if="props.value !== '50'">{{ props.value }}条/页</span>
        </template>
      </a-pagination>
    </div>

    <!-- 抽屉弹窗 -->
    <a-drawer
        :title="drawerIsSave ? '新增对象存储':'修改对象存储信息'"
        placement="right"
        width="480"
        :closable="false"
        :visible="saveOrUpdateDrawerIsShow"
        @close="cancelSaveOrUpdate">
      <a-form-model
          ref="formData"
          :model="formData"
          :rules="rules"
          layout="vertical">

        <!--对象存储名称-->
        <a-form-model-item prop="objectStoreName">
          <div class="form-item">
            <div class="title-span">
              <a-tooltip>
                <template slot="title">
                  对象存储名称，可以自定义（eg：阿里云Oss）
                </template>
                <a-icon
                    theme="twoTone"
                    class="info-icon"
                    two-tone-color="orange"
                    type="question-circle"/>
              </a-tooltip>
              对象存储名称:
            </div>
            <a-input
                v-model="formData.objectStoreName"
                placeholder="请输入对象存储名称..."/>
          </div>
        </a-form-model-item>

        <!--对象存储标识-->
        <a-form-model-item prop="objectStoreLabel">
          <div class="form-item">
            <div class="title-span">
              <a-tooltip>
                <template slot="title">
                  对象存储标识(目前仅支持：Project、Oss、Cos、Kodo)
                </template>
                <a-icon
                    theme="twoTone"
                    class="info-icon"
                    two-tone-color="orange"
                    type="question-circle"/>
              </a-tooltip>
              对象存储标识:
            </div>
            <a-select
                placeholder="请选择对象存储标识..."
                v-model="formData.objectStoreLabel">
              <a-select-option
                  v-for="item in objectStoreSelectList"
                  :key="item.label"
                  :value="item.label"
                  :disabled="item.disabled"
                  :label="item.name">
                {{ item.name + `(${completeInfo(item.name)})` }}
              </a-select-option>
            </a-select>
          </div>
        </a-form-model-item>

        <!-- accessKey -->
        <a-form-model-item prop="accessKey">
          <div class="form-item">
            <div class="title-span">
              <a-tooltip>
                <template slot="title">
                  请勿泄露accessKey，否则会造成安全隐患
                </template>
                <a-icon
                    theme="twoTone"
                    class="info-icon"
                    two-tone-color="red"
                    type="exclamation-circle"/>
              </a-tooltip>
              accessKey:
            </div>
            <a-input-password
                v-model="formData.accessKey"
                placeholder="请输入accessKey..."/>
          </div>
        </a-form-model-item>

        <!-- accessSecret -->
        <a-form-model-item prop="accessKeySecret">
          <div class="form-item">
            <div class="title-span">
              <a-tooltip>
                <template slot="title">
                  请勿泄露accessSecret，否则会造成安全隐患
                </template>
                <a-icon
                    theme="twoTone"
                    class="info-icon"
                    two-tone-color="red"
                    type="exclamation-circle"/>
              </a-tooltip>
              accessSecret:
            </div>
            <a-input-password
                v-model="formData.accessKeySecret"
                placeholder="请输入accessKeySecret..."/>
          </div>
        </a-form-model-item>

        <!--存储桶-->
        <a-form-model-item prop="bucket">
          <div class="form-item">
            <div class="title-span">
              <a-tooltip>
                <template slot="title">
                  对象存储存储桶名称，根据在云平台的定义进行填写
                </template>
                <a-icon
                    theme="twoTone"
                    class="info-icon"
                    two-tone-color="orange"
                    type="question-circle"/>
              </a-tooltip>
              存储桶:
            </div>
            <a-input
                v-model="formData.bucket"
                placeholder="请输入存储桶..."/>
          </div>
        </a-form-model-item>

        <!--地域节点-->
        <a-form-model-item prop="endpoint">
          <div class="form-item">
            <div class="title-span">
              <a-tooltip>
                <template slot="title">
                  对象存储地域节点，填写存储桶所在的地域节点信息
                </template>
                <a-icon
                    theme="twoTone"
                    class="info-icon"
                    two-tone-color="orange"
                    type="question-circle"/>
              </a-tooltip>
              地域节点:
            </div>
            <a-input
                v-model="formData.endpoint"
                placeholder="请输入地域节点..."/>
          </div>
        </a-form-model-item>

        <!--对象存储域名-->
        <a-form-model-item prop="domainUrl">
          <div class="form-item">
            <div class="title-span">
              <a-tooltip>
                <template slot="title">
                  对象存储域名，可以自定义（eg：disease-store.oss-cn-beijing.aliyuncs.com）
                </template>
                <a-icon
                    theme="twoTone"
                    class="info-icon"
                    two-tone-color="orange"
                    type="question-circle"/>
              </a-tooltip>
              对象存储域名:
            </div>
            <a-input
                v-model="formData.domainUrl"
                placeholder="请输入对象存储域名..."/>
          </div>
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
  </a-card>
</template>

<script>
import PageTitle from "@/components/layout/page/PageTitle";
import Logo from "@/assets/images/logo/logo.png";
import {
  batchDeleteObjectStore,
  getObjectStoreById,
  listObjectStore,
  listObjectStoreSelect,
  saveObjectStore,
  updateObjectStore,
  updateObjectStoreStatus
} from "@/api/objectStoreApi";
import {checkResult} from "@/utils/result/resultUtil";
import {uploadPictureTest} from "@/api/uploadApi";
import {clearFormData, deleteManyData, deleteOneData} from "@/utils/common/common";

export default {
  name: "ObjectStore",
  components: {
    PageTitle,
  },
  data() {
    return {
      //================= 初始化参数 =================
      loading: false,
      hideSearch: false,
      drawerIsSave: null,
      saveOrUpdateDrawerIsShow: null,
      isUpdate: null,
      deleteList: [],
      dataList: [],
      total: 0,
      Logo,
      selectData: {},
      objectStoreSelectList: [],
      rules: {
        objectStoreName: [{required: true, message: "对象存储名称为必填项！", trigger: 'blur'}],
        objectStoreLabel: [{required: true, message: "对象存储名称为必填项！", trigger: 'blur'}],
        accessKey: [{required: true, message: "accessKey为必填项！", trigger: 'blur'}],
        accessKeySecret: [{required: true, message: "accessKeySecret为必填项！", trigger: 'blur'}],
        bucket: [{required: true, message: "存储桶为必填项！", trigger: 'blur'}],
        endpoint: [{required: true, message: "地域节点为必填项！", trigger: 'blur'}],
        domainUrl: [{required: true, message: "对象存储域名为必填项！", trigger: 'blur'}]
      },
      //==================  条件  ===================
      condition: {
        pageNow: 1,
        pageSize: 10,
        keywords: null,
        objectStoreLabel: null,
        isPrefect: undefined,
        isPassTest: undefined
      },
      //==================  表单数据  ===================
      formData: {
        objectStoreName: undefined,
        objectStoreLabel: undefined,
        domainUrl: undefined,
        accessKey: undefined,
        accessKeySecret: undefined,
        endpoint: undefined,
        bucket: undefined,
        isPrefect: 0
      }
    }
  },
  computed: {
    rowSelectList() {
      return {
        onChange: (keys, rows) => {
          this.deleteList = keys;
        },
      };
    },
    completeInfo() {
      return function (name) {
        switch (name) {
          case "Oss":
            return " 阿里云对象存储";
          case "Cos":
            return " 腾讯云对象存储";
          case "Kodo":
            return " 七牛云对象存储";
        }
      };
    },
  },
  created() {
    this.initData();
    this.initSelect();
  },
  methods: {
    //====================== 初始化方法 =========================
    async initData() {
      this.loading = true;
      const res = await listObjectStore(this.condition);
      if (res.success) {
        this.dataList = res.data.dataList;
        this.total = res.data.totalCount;
      }
      if (this.total === 1) {
        this.$notification.warning({
          message: "重要提醒！",
          description: "为了防止用户上传文件内容丢失，建议使用云服务厂商对象存储服务！"
        })
      }
      this.loading = false;
    },
    async initSelect() {
      const res = await listObjectStoreSelect();
      if (res.success) {
        this.objectStoreSelectList = res.data;
      }
    },
    //====================== 交互方法 =========================
    resetCondition() {
      this.condition.keywords = null;
      this.condition.objectStoreLabel = null;
      this.condition.isPrefect = undefined;
      this.condition.isPassTest = undefined;
      this.timeValue = [];
      this.initData();
    },
    resetFormData() {
      this.saveOrUpdateDrawerIsShow = false;
      this.drawerIsSave = null;
      this.formData.objectStoreName = undefined;
      this.formData.objectStoreLabel = undefined;
      this.formData.domainUrl = undefined;
      this.formData.accessKey = undefined;
      this.formData.accessKeySecret = undefined;
      this.formData.endpoint = undefined;
      this.formData.bucket = undefined;
      this.formData.isPrefect = 0;
      this.initData();
    },
    getSelectRow(data) {
      this.selectData = data;
    },
    async uploadTest(info) {
      const res = await uploadPictureTest(info.file, this.selectData.objectStoreLabel, "/test", "/");
      if (res.success) {
        checkResult(res);
        await updateObjectStoreStatus({"id": this.selectData.id, "isPassTest": 1});
        await this.initData();
      }
    },
    //====================== 新增/编辑 =========================
    async toSaveOrUpdate(data) {
      this.drawerIsSave = data === undefined;
      if (!this.drawerIsSave) {
        const res = await getObjectStoreById(data.id);
        this.formData = res.success ? res.data : data;
        this.formData.isPassTest = 0;
        this.formData.isPerfect = this.formData.isPerfect ? 1 : 0;
      }
      this.saveOrUpdateDrawerIsShow = true;
    },
    async doSaveOrUpdate() {
      this.$refs.formData.validate(async valid => {
        if (valid) {
          if (this.formData.objectStoreName
              && this.formData.objectStoreLabel
              && this.formData.domainUrl
              && this.formData.accessKey
              && this.formData.accessKeySecret
              && this.formData.endpoint
              && this.formData.bucket) {
            this.formData.isPerfect = 1;
          } else {
            this.formData.isPerfect = 0;
          }
          this.drawerIsSave ? checkResult(await saveObjectStore(this.formData)) : checkResult(await updateObjectStore(this.formData));
          this.resetFormData();
        }
      });
    },
    cancelSaveOrUpdate() {
      clearFormData(this.resetFormData);
    },
    //====================== 删除 =========================
    deleteOne(id) {
      deleteOneData("对象存储", batchDeleteObjectStore, [id], this.initData);
    },
    deleteMany() {
      deleteManyData("对象存储", batchDeleteObjectStore, this.deleteList, this.initData);
    },
    //====================== 分页 =========================
    sizeChange(current, size) {
      this.condition.pageSize = size;
      this.initData();
    },
    currentChange(current, size) {
      this.condition.pageNow = current;
      this.initData();
    }
  }
}
</script>

<style lang="scss" scoped>
.form-box {

  .form-item {
    display: flex;
    justify-content: flex-start;
    align-items: center;
    margin-bottom: 20px;

    .title-span {
      width: 40%;
    }
  }
}
</style>
