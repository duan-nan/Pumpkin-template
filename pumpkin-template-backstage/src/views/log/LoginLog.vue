<template>
  <div class="main-container animate__animated animate__fadeIn">
    <a-card style="border-radius: 5px">
      <!-- 页面标题 -->
      <page-title/>
      <!-- 页面内容 -->
      <div class="page-content">
        <!-- 搜索部分内容 -->
        <div class="search-container animate__animated animate__flipInX" v-if="!hideSearch">
          <!-- 输入框内容 -->
          <div class="search-box">
            <!-- 用户名 -->
            <div class="search-item">
              <span class="label-title">用户名</span>
              <a-input class="label-component"
                       v-model="condition.keywords"
                       allowClear
                       placeholder="请输入用户名..."/>
            </div>
            <!-- ip来源 -->
            <div class="search-item">
              <span class="label-title">ip来源</span>
              <a-input class="label-component"
                       v-model="condition.ipSource"
                       allowClear
                       placeholder="请输入ip来源..."/>
            </div>
            <!-- 登陆状态 -->
            <div class="search-item">
              <span class="label-title">登陆状态</span>
              <a-select
                  allowClear
                  class="label-component"
                  placeholder="请选择登录状态..."
                  v-model="condition.loginStatus">
                <a-select-option :value="true">
                  <a-icon type="check-circle" theme="twoTone" class="icon-item"/>
                  登录成功
                </a-select-option>
                <a-select-option :value="false">
                  <a-icon type="close-circle" theme="twoTone" two-tone-color="red" class="icon-item"/>
                  登录失败
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

        <!-- 分割线 -->
        <a-divider style="margin: 15px 0"></a-divider>

        <!-- 操作部分按钮 -->
        <div class="operate-btn-container">
          <div class="operate-left-box">
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
              :scroll="scroll"
              :rowSelection="rowSelectList"
              :data-source="loginLogList"
              size="small"
              :rowKey="(loginLog) => loginLog.id"
              :pagination="false">
            <a-table-column
                align="center"
                width="5%"
                title="日志编号"
                data-index="id"/>
            <a-table-column
                align="center"
                width="10%"
                title="用户名"
                data-index="username"/>
            <a-table-column
                align="center"
                width="10%"
                title="ip地址"
                data-index="ipAddress"/>
            <a-table-column
                align="center"
                width="10%"
                title="ip来源"
                data-index="ipSource"/>
            <a-table-column
                align="center"
                min-width="10%"
                title="浏览器"
                data-index="browser"/>
            <a-table-column
                align="center"
                min-width="10%"
                title="操作系统"
                data-index="os"/>
            <a-table-column
                align="center"
                width="10%"
                title="登陆状态"
                data-index="loginStatus">
              <template slot-scope="loginStatus">
                <a-tag style="user-select: none" :color="loginStatus?'#87d068':'#f50'">
                  {{ loginStatus ? "登陆成功" : "登陆失败" }}
                </a-tag>
              </template>
            </a-table-column>
            <a-table-column
                align="center"
                min-width="15%"
                title="响应信息"
                data-index="responseMessage"/>
            <a-table-column
                align="center"
                width="200px"
                fixed="right"
                title="操作时间"
                data-index="gmtCreate">
              <template slot-scope="gmtCreate">
                <a-icon type="bell"/>
                {{ gmtCreate }}
              </template>
            </a-table-column>
          </a-table>
        </a-spin>

        <!-- 分页 -->
        <a-pagination
            class="page-center"
            :total="total" show-size-changer :defaultPageSize="10"
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
import {batchDeleteLoginLog, listLoginLog} from "@/api/loginLogApi";
import PageTitle from "@/components/layout/page/PageTitle";
import locale from 'ant-design-vue/es/date-picker/locale/zh_CN';
import {deleteManyData} from "@/utils/common/common";

export default {
  name: "LoginLog",
  data() {
    return {
      scroll: {
        x: 1450,
      },
      //=======================  初始化数据  ===========================
      locale,
      loading: false,
      deleteList: [],
      loginLogList: [],
      selectList: [],
      total: 0,
      timeValue: [],
      hideSearch: false,
      //=======================  条件  ===========================
      condition: {
        pageNow: 1,
        pageSize: 10,
        keywords: null,
        ipSource: null,
        loginStatus: undefined,
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
    }
  },
  created() {
    this.initData();
  },
  methods: {
    //=======================  初始化  ===========================
    async initData() {
      this.loading = true;
      const res = await listLoginLog(this.condition);
      this.loginLogList = res.data.dataList;
      this.total = res.data.totalCount;
      this.loading = false;
    },
    //=======================  交互方法  ===========================
    resetCondition() {
      this.condition.keywords = null;
      this.condition.ipSource = null;
      this.condition.loginStatus = undefined;
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
      deleteManyData("登录日志",batchDeleteLoginLog,this.deleteList,this.initData);
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
}
</script>

<style lang="scss" scoped>

</style>
