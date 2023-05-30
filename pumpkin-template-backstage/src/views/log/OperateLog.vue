<template>
  <div class="main-container animate__animated animate__fadeIn">
    <a-card style="border-radius: 5px">
      <page-title/>
      <div class="page-content">

        <!-- 搜索部分 -->
        <div class="search-container animate__animated animate__flipInX" v-if="!hideSearch">
          <!-- 输入框内容 -->
          <div class="search-box">
            <!-- 操作模块 -->
            <div class="search-item">
              <span class="label-title">操作模块</span>
              <a-input class="label-component"
                       v-model="condition.keywords"
                       allowClear
                       placeholder="请输入操作模块..."/>
            </div>
            <!-- 用户名 -->
            <div class="search-item">
              <span class="label-title">用户名</span>
              <a-input class="label-component"
                       v-model="condition.username"
                       allowClear
                       placeholder="请输入用户名..."/>
            </div>
            <!-- 操作类型 -->
            <div class="search-item">
              <span class="label-title">操作类型</span>
              <a-select
                  allowClear
                  class="label-component"
                  placeholder="请选择操作类型..."
                  v-model="condition.operateType">
                <a-select-option
                    v-for="item in selectList"
                    :value="item.value"
                    :key="item.value">
                  {{ item.label }}
                </a-select-option>
              </a-select>
            </div>
            <!-- 请求类型 -->
            <div class="search-item">
              <span class="label-title">请求类型</span>
              <a-select
                  allowClear
                  class="label-component"
                  placeholder="请选择请求类型..."
                  v-model="condition.requestType">
                <a-select-option value="GET">
                  GET
                </a-select-option>
                <a-select-option value="POST">
                  POST
                </a-select-option>
                <a-select-option value="PUT">
                  PUT
                </a-select-option>
                <a-select-option value="DELETE">
                  DELETE
                </a-select-option>
              </a-select>
            </div>
          </div>
          <!-- 搜索按钮 -->
          <div class="search-box">
            <!-- 创建时间 -->
            <div class="search-item">
              <span class="label-title">创建时间</span>
              <a-range-picker
                  style="width: 100%;"
                  v-model="timeValue"
                  :locale="locale"
                  @change="dateChange"
                  class="label-component"/>
            </div>
            <!-- 操作按钮 -->
            <div class="search-item">
              <a-button style="margin:0 10px 0 30px"
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
            <a-button :disabled="deleteList.length === 0"
                      class="operate-left-item"
                      type="danger"
                      icon="delete"
                      @click="deleteMany">
              批量删除
            </a-button>
          </div>
          <div class="operate-right-box">
            <a-tooltip placement="top">
              <template slot="title">
                <span>{{ showSearch ? '展示搜索' : '隐藏搜索' }}</span>
              </template>
              <a-button
                  style="margin-right: 10px"
                  size="small"
                  @click="hideSearch = !hideSearch"
                  :icon="hideSearch ? 'zoom-out':'zoom-in'"/>
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

        <!-- 表格内容 -->
        <a-spin :spinning="loading" tip="阿星正在努力拉取数据中...">
          <!--表格内容-->
          <a-table
              :scroll="{x: 1200}"
              :rowSelection="rowSelectList"
              :data-source="operateLogList" size="small"
              :rowKey="(operateLog) => operateLog.id"
              :pagination="false">
            <a-table-column
                align="center"
                width="15%"
                title="操作模块"
                data-index="operateModule"/>
            <a-table-column
                align="center"
                width="10%"
                title="操作类型"
                data-index="operateType">
              <template slot-scope="operateType">
                <a-tag style="user-select: none" :color="getColor(operateType).color">
                  {{ getColor(operateType).operateTypeName }}
                </a-tag>
              </template>
            </a-table-column>
            <a-table-column
                align="center"
                width="10%"
                title="请求方式"
                data-index="requestType">
              <template slot-scope="requestType">
                <a-tag style="user-select: none" :color="getColor(requestType).color">{{ requestType }}</a-tag>
              </template>
            </a-table-column>
            <a-table-column
                align="center"
                width="15%"
                title="操作人员"
                data-index="username"/>
            <a-table-column
                align="center"
                min-width="20%"
                title="ip地址"
                data-index="operateIpAddress"/>
            <a-table-column
                align="center"
                width="12%"
                title="操作地点"
                data-index="operateIpSource">
              <template slot-scope="operateIpSource">
                {{ operateIpSource == null ? "未知地址" : operateIpSource }}
              </template>
            </a-table-column>
            <a-table-column
                align="center"
                width="18%"
                title="操作时间"
                data-index="gmtCreate">
              <template slot-scope="gmtCreate">
                <a-icon type="bell"/>
                {{ gmtCreate }}
              </template>
            </a-table-column>
            <div slot="expandedRowRender" class="more-container" slot-scope="scope">
              <div class="one-info">
                <div class="module-box">
                  <span class="span-title">模块方法：</span>
                  <span class="info">{{ scope.operateModule }} / {{ scope.operateDescription }}</span>
                </div>
                <div class="url-box">
                  <span class="span-title">请求地址：</span>
                  <span class="info">{{ scope.operateUrl }}</span>
                </div>
              </div>
              <div class="one-info">
                <div class="login-box">
                  <span class="span-title">登陆信息：</span>
                  <span class="info">
                  {{ scope.username }} / {{
                      scope.operateIpAddress
                    }} /{{ scope.operateIpSource === "" ? "无" : scope.operateIpSource }}
                </span>
                </div>
                <div class="method-box">
                  <span class="span-title">请求方式：</span>
                  <a-tag style="user-select: none" :color="getColor(scope.requestType).color">
                    {{ scope.requestType }}
                  </a-tag>
                </div>
              </div>
              <div class="two-info">
                <span class="span-title">执行方法：</span>
                <span class="info">{{ scope.operateMethod }}</span>
              </div>
              <div class="two-info">
                <span class="span-title">请求参数：</span>
                <a-textarea style="width: 90%" :rows="8" readonly
                            :value="JSON.stringify(JSON.parse(scope.requestParams), null, '\t')"/>
              </div>
              <div class="two-info">
                <span class="span-title">响应数据：</span>
                <a-textarea style="width: 90%" :rows="5" readonly
                            :value="JSON.stringify(JSON.parse(scope.responseData), null, '\t')"/>
              </div>
              <div class="three-info">
                <span class="span-title">操作时间：</span>
                <span class="info">{{ scope.gmtCreate }}</span>
              </div>
            </div>
          </a-table>
        </a-spin>

        <!-- 分页组件 -->
        <a-pagination
            class="page-center"
            :total="total"
            show-size-changer
            :defaultPageSize="10"
            :pageSizeOptions="['10', '20', '30']"
            :show-total="(total) => `共有 ${total} 条日志记录`"
            @change="currentChange"
            @showSizeChange="sizeChange">
          <template slot="buildOptionText" slot-scope="props">
            <span v-if="props.value !== '50'">{{ props.value }}条/页</span>
          </template>
        </a-pagination>
      </div>
    </a-card>
  </div>
</template>

<script>

import locale from 'ant-design-vue/es/date-picker/locale/zh_CN';
import {OperateColor} from "@/views/log/config/Color"
import {seclectList} from "@/views/log/config/Select";
import {batchDeleteOperateLog, listOperateLog} from "@/api/operateLogApi";
import PageTitle from "@/components/layout/page/PageTitle";
import {deleteManyData} from "@/utils/common/common";

export default {
  name: "OperateLog",
  data() {
    return {
      locale,
      //=======================  初始化数据  ===========================
      loading: false,
      showSearch: false,
      timeValue: [],
      deleteList: [],
      operateLogList: [],
      selectList: seclectList,
      hideSearch: false,
      total: 0,
      //=======================  条件  ===========================
      condition: {
        pageNow: 1,
        pageSize: 10,
        keywords: null,
        username: null,
        operateType: undefined,
        requestType: undefined,
        beginTime: null,
        endTime: null,
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
    getColor() {
      return function (type) {
        return OperateColor.find((item) => item.operateType === type);
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
      const res = await listOperateLog(this.condition);
      this.operateLogList = res.data.dataList;
      this.total = res.data.totalCount;
      this.loading = false;
    },
    //=======================  交互方法  ===========================
    resetCondition() {
      this.condition.keywords = null;
      this.condition.username = null;
      this.condition.operateType = undefined;
      this.condition.requestType = undefined;
      this.condition.beginTime = null;
      this.condition.endTime = null;
      this.timeValue = [];
      this.initData();
    },
    dateChange(date, dateString) {
      if (dateString[0] !== '' && dateString[1] !== '') {
        dateString[0] = dateString[0] + " 00:00:01";
        dateString[1] = dateString[1] + " 23:59:59";
      }
      this.condition.beginTime = dateString[0];
      this.condition.endTime = dateString[1];
    },
    //=======================  删除  ===========================
    deleteMany() {
      deleteManyData("操作日志", batchDeleteOperateLog, this.deleteList, this.initData);
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
.more-container {
  width: 100%;

  .one-info {
    display: flex;
    justify-content: left;
    align-items: center;
    margin: 10px 0;

    .module-box {
      width: 50%;
    }

    .login-box {
      width: 50%;
    }
  }

  .two-info {
    display: flex;
    justify-content: left;
    align-items: center;
    margin: 10px 0;

    .info {
      width: 93%;
    }

    .span-title {
      width: 7%;
    }
  }


  .three-info {
    display: flex;
    justify-content: right;
    align-content: center;
  }
}


.span-title {
  font-weight: bolder;
}


</style>
