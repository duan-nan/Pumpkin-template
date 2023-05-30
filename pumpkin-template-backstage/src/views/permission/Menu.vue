<template>
  <a-card class="main-container animate__animated animate__fadeIn" style="border-radius: 5px">
    <!-- 搜索部分 -->
    <div class="btn-container">
      <a-button
          type="primary"
          icon="plus"
          @click="toSaveOrUpdate()">
        新增菜单
      </a-button>
    </div>

    <!-- 表格部分 -->
    <a-spin :spinning="loading" tip="正在努力拉取数据...">
      <!--表格内容-->
      <a-table
          v-if="menuList && menuList.length > 0"
          :data-source="menuList"
          size="small"
          :scroll="scroll"
          defaultExpandAllRows
          :rowKey="(menu) => menu.id"
          :pagination="false">
        <a-table-column
            width="300px"
            title="菜单名称"
            data-index="name">
          <template slot-scope="name">
            <span style="overflow-x: hidden">{{ name }}</span>
          </template>
        </a-table-column>
        <a-table-column
            align="center"
            width="60px"
            title="图标"
            data-index="icon">
          <template slot-scope="icon">
            <a-icon :type="icon"/>
          </template>
        </a-table-column>
        <a-table-column
            align="center"
            width="80px"
            title="菜单类型"
            data-index="menuType">
          <template slot-scope="menuType">
            <a-tag color="orange" v-if="menuType === 'C'">目录</a-tag>
            <a-tag color="cyan" v-if="menuType === 'M'">菜单</a-tag>
            <a-tag color="green" v-if="menuType === 'B'">按钮</a-tag>
          </template>
        </a-table-column>
        <a-table-column
            align="center"
            width="60px"
            title="排序"
            data-index="orderNum"/>
        <a-table-column
            align="center"
            width="200px"
            title="权限信息"
            data-index="perm">
          <template slot-scope="perm">
            {{ perm }}
          </template>
        </a-table-column>
        <a-table-column
            align="center"
            width="210px"
            title="组件路径"
            data-index="component">
          <template slot-scope="component">
            {{ component }}
          </template>
        </a-table-column>
        <a-table-column
            align="center"
            width="90px"
            title="状态"
            data-index="isHidden">
          <template slot-scope="isHidden">
            <a-switch disabled
                      :default-checked="!isHidden"
                      checked-children="正常"
                      un-checked-children="禁用"/>
          </template>
        </a-table-column>
        <a-table-column
            align="center"
            width="190px"
            title="创建时间"
            data-index="gmtCreate">
          <template slot-scope="gmtCreate">
            <a-icon type="bell"/>
            {{ formatDate(gmtCreate) }}
          </template>
        </a-table-column>
        <a-table-column
            fixed="right"
            width="220px"
            align="center"
            title="操作">
          <template slot-scope="scope">
            <a-button
                class="button"
                v-if="scope.menuType !== 'B'"
                type="link"
                size="small"
                icon="plus-square"
                @click="toSaveOrUpdate(scope.id)">
              新增
            </a-button>
            <a-button
                class="button"
                type="link"
                size="small"
                icon="edit"
                @click="toSaveOrUpdate(scope)">
              编辑
            </a-button>
            <a-button
                class="button"
                size="small"
                type="link"
                icon="delete"
                @click="deleteInfo(scope.id)">
              删除
            </a-button>
          </template>
        </a-table-column>
      </a-table>
    </a-spin>

    <!--抽屉弹出层-->
    <a-drawer
        :title="drawerTitle"
        :width="600"
        :visible="drawerShow"
        @close="closeDrawer">
      <div class="menu-container">
        <a-form>
          <!--上级菜单-->
          <div class="simple-container parent-menu">
            <div class="title-span">上级菜单:</div>
            <a-input
                :disabled="true"
                v-model="parentName"
                placeholder="请输入菜单信息..."/>
          </div>
          <!--菜单类型-->
          <div class="simple-container">
            <div class="title-span">菜单类型:</div>
            <a-radio-group :disabled="drawerTitle === '修改菜单'"
                           @change="menuChange"
                           v-model="menuForm.menuType">
              <a-radio value="C">目录</a-radio>
              <a-radio value="M">菜单</a-radio>
              <a-radio value="B">按钮</a-radio>
            </a-radio-group>
          </div>
          <!--菜单图标-->
          <div class="simple-container"
               v-if="menuForm.menuType !== 'B'">
            <div class="title-span">菜单图标:</div>
            <a-popover trigger="click">
              <template slot="content">
                <a-row style="width: 550px;height: 400px;overflow-y: scroll;">
                  <a-col v-for="icon in iconList"
                         :key="icon"
                         :span="8"
                         :gutter="10">
                    <div @click="menuForm.icon = icon"
                         class="icon-item">
                      <a-icon :type="icon" class="icon"/>
                      <span class="icon-text">{{ icon }}</span>
                    </div>
                  </a-col>
                </a-row>
              </template>
              <a-input v-model="menuForm.icon" placeholder="请选择菜单图标...">
                <a-icon slot="prefix" :type="menuForm.icon"/>
                <a-tooltip slot="suffix" title="菜单图标">
                  <a-icon type="info-circle" style="color: rgba(0, 0, 0, 0.45)"/>
                </a-tooltip>
              </a-input>
            </a-popover>
          </div>
          <!--菜单名称/排序-->
          <div class="two-container">
            <div class="one-box">
              <div class="title-span">菜单名称:</div>
              <a-input v-model="menuForm.name" placeholder="请输入菜单名称..."/>
            </div>
            <div class="two-box">
              <div class="title-span">
                <a-tooltip>
                  <template slot="title">
                    菜单在侧边栏的展示顺序，由小到大依次展示
                  </template>
                  <a-icon
                      theme="filled"
                      class="info-icon"
                      type="question-circle"/>
                </a-tooltip>
                菜单排序:
              </div>
              <a-input-number
                  id="inputNumber"
                  v-model="menuForm.orderNum"
                  :min="1"/>
            </div>
          </div>
          <!--菜单是否显示/路由-->
          <div class="two-container" v-if="menuForm.menuType !== 'B'">
            <div class="one-box">
              <div class="title-span">
                <a-tooltip>
                  <template slot="title">
                    选择隐藏菜单，
                  </template>
                  <a-icon
                      theme="filled"
                      class="info-icon"
                      type="question-circle"/>
                </a-tooltip>
                菜单禁用:
              </div>
              <a-radio-group
                  v-model="menuForm.isHidden"
                  style="width: 70%">
                <a-radio :value="false">显示</a-radio>
                <a-radio :value="true">隐藏</a-radio>
              </a-radio-group>
            </div>
            <div class="two-box" v-if="menuForm.menuType !== 'M'">
              <div class="title-span">
                <a-tooltip>
                  <template slot="title">
                    访问的路由地址，如：`/article`
                  </template>
                  <a-icon
                      theme="filled"
                      class="info-icon"
                      type="question-circle"/>
                </a-tooltip>
                菜单路由:
              </div>
              <a-input
                  v-model="menuForm.path"
                  placeholder="请输入菜单路由..."/>
            </div>
          </div>
          <!--菜单组件-->
          <div class="simple-container" v-if="menuForm.menuType === 'C'">
            <div class="title-span">
              <a-tooltip>
                <template slot="title">
                  菜单组件的位置，位于`views`目录下,例如`/article/ArticleList`
                </template>
                <a-icon
                    theme="filled"
                    class="info-icon"
                    type="question-circle"/>
              </a-tooltip>
              菜单组件:
            </div>
            <a-input
                :disabled="menuForm.menuType === 'M'"
                v-model="menuForm.component"
                placeholder="请输入菜单组件..."/>
          </div>
          <!--权限字符-->
          <div class="simple-container"
               v-if="menuForm.menuType !== 'M' && menuForm.menuType !== 'C'">
            <div class="title-span">
              <a-tooltip>
                <template slot="title">
                  控制器中定义的权限字符，例如`security:menu:save`
                </template>
                <a-icon
                    theme="filled"
                    class="info-icon"
                    type="question-circle"/>
              </a-tooltip>
              权限字符:
            </div>
            <a-input
                v-model="menuForm.perm"
                placeholder="请输入菜单字符..."/>
          </div>
          <!--备注-->
          <div class="simple-container">
            <div class="title-span">
              <a-tooltip>
                <template slot="title"> 指定菜单备注信息</template>
                <a-icon theme="filled"
                        class="info-icon"
                        type="question-circle"/>
              </a-tooltip>
              备注信息:
            </div>
            <a-textarea
                :auto-size="{ minRows: 5 }"
                v-model="menuForm.remark"
                placeholder="请输入备注信息..."/>
          </div>
        </a-form>
      </div>
      <!--操作按钮-->
      <div class="drawer-btn">
        <a-button
            style="margin-right: 10px"
            @click="closeDrawer">
          取消
        </a-button>
        <a-button
            type="primary"
            @click="saveOrUpdate">
          确认
        </a-button>
      </div>
    </a-drawer>
  </a-card>
</template>

<script>

import IconList from "@/assets/json/IconList.json";
import {deleteMenuById, getMenuById, listMenu, saveMenu, updateMenu} from "@/api/menuApi";
import {formatMenu} from "@/utils/menu/Menu";


export default {
  name: "Menu",
  data() {
    return {
      //=======================  初始化数据  ===========================
      loading: false,
      defaultMenuIdList: [],
      menuList: [],
      iconList: IconList,
      scroll: {
        x: 1050,
      },
      drawerShow: false,
      drawerTitle: "",
      parentName: "",
      //=======================  条件  ===========================
      name: "",
      //=======================  form  ===========================
      menuForm: {
        id: null,
        name: null,
        icon: null,
        orderNum: 1,
        perm: null,
        component: null,
        path: null,
        isHidden: false,
        menuType: "C",
        parentId: -1,
        remark: "",
      },
    };
  },
  created() {
    this.initData();
  },
  computed: {
    formatDate() {
      return function (timestamp) {
        if (undefined !== timestamp) {
          const date = new Date(timestamp);
          const year = date.getFullYear();
          const month = date.getMonth() + 1;
          const day = date.getDate();
          const hour = date.getHours();
          const minute = date.getMinutes();
          const second = date.getSeconds();
          return `${year}-${month}-${day} ${hour}:${minute}:${second}`;
        }
        return null;
      }
    }
  },
  methods: {
    //=======================  初始化  ===========================
    async initData() {
      this.loading = true;
      const res = await listMenu(true);
      if (res.success) {
        this.menuList = formatMenu(res.data);
      }
      this.loading = false;
    },
    //=======================  新增/更新  ===========================
    async toSaveOrUpdate(data) {

      if (data === undefined) {
        this.drawerTitle = "新增菜单";
        this.menuForm.parentId = 0;
      } else if (data instanceof Number || data.id === undefined) {
        this.drawerTitle = "新增菜单";
        this.menuForm.parentId = data;
      } else {
        this.drawerTitle = "修改菜单";
        let res = await getMenuById(data.id);
        this.menuForm = res.data;
      }

      if (this.menuForm.parentId === 0) {
        this.parentName = "无上级菜单";
      } else {
        this.parentName = this.menuForm.parentName;
      }
      this.drawerShow = true;
    },
    saveOrUpdate() {
      if (this.drawerTitle === "修改菜单") {
        this.updateMenu();
      } else {
        this.saveMenu();
      }
      this.drawerShow = false;
      this.drawerShow = false;
      this.menuForm = {
        id: null,
        name: null,
        icon: null,
        orderNum: 1,
        perm: null,
        component: null,
        path: null,
        isHidden: false,
        menuType: "C",
        parentId: 0,
        remark: "",
      };
    },
    async saveMenu() {
      this.loading = true;
      this.menuForm.perm = this.menuForm.perm === null ? '#' : this.menuForm.perm;
      const res = await saveMenu(this.menuForm);
      if (res.success) {
        this.$notification.success({message: res.message});
        await this.initData();
      }
      this.loading = false;
    },
    async updateMenu() {
      this.loading = true;
      const res = await updateMenu(this.menuForm);
      if (res.success) {
        this.$notification.success({message: res.message});
        await this.initData();
      }
      this.loading = false;
    },
    //=======================  删除  ===========================
    deleteInfo(id) {
      var that = this;
      this.$confirm({
        title: "警告",
        content: "确定要删除菜单信息吗？",
        centered: true,
        okText: "确定",
        cancelText: "取消",
        async onOk() {
          that.loading = true;
          const res = await deleteMenuById(id);
          if (res.success) {
            that.$notification.success({message: '提示', description: res.message});
            await that.initData();
          }
          that.loading = false;
        },
      });
    },
    //=======================  弹出层操作  ===========================
    closeDrawer() {
      var that = this;
      this.$confirm({
        title: "提示",
        content: "离开将不会保存编辑数据？",
        centered: true,
        okText: "确定",
        cancelText: "取消",
        onOk() {
          that.drawerTitle = "";
          that.drawerShow = false;
          that.menuForm = {
            id: null,
            name: null,
            icon: null,
            orderNum: 1,
            perm: null,
            component: null,
            path: null,
            isHidden: false,
            menuType: "C",
            parentId: 0,
            remark: "",
          };
        },
      });
    },
    menuChange() {
      if (this.menuForm.menuType === "M") {
        this.menuForm.component = "#";
        this.menuForm.path = "#";
        this.menuForm.perm = "#";
      } else {
        this.menuForm.component = "";
        this.menuForm.path = "";
        this.menuForm.perm = "";
      }
    },
  },
}
</script>

<style lang="scss" scoped>
.btn-container {
  display: flex;
  justify-content: flex-start;
  align-items: center;
  margin-top: 10px;
  margin-bottom: 20px;
}

.button {
  font-size: 11px;
  margin-right: 5px;
}

.drawer-btn {
  position: absolute;
  right: 0;
  bottom: 0;
  width: 100%;
  border-top: 1px solid #e9e9e9;
  padding: 10px 16px;
  background: #fff;
  text-align: right;
  z-index: 1;
}

.simple-container {
  display: flex;
  justify-content: left;
  align-items: center;
  margin: 30px 0 0;

  .title-span {
    width: 20%;
  }


}

.icon-item {
  padding: 5px 0 !important;

  .icon {
    font-size: 18px;
    padding-right: 5px;
    cursor: pointer;
  }

  .icon-text {
    font-size: 15px;
    margin-right: 5px;
    cursor: pointer;
  }

}

.icon-item:hover {
  border-radius: 5px;
  background-color: gainsboro;
}


.parent-menu {
  margin-top: 10px;
}


.ant-popover-inner {
  width: 550px !important;
  height: 400px !important;
  overflow: hidden;
  overflow-y: auto;
}

.two-container {
  margin-top: 30px;
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;

  .title-span {
    width: 45%;
  }

  .one-box {
    display: flex;
    justify-content: left;
    align-items: center;
    width: 45%;
  }

  .two-box {
    display: flex;
    justify-content: left;
    align-items: center;
    width: 50%;
  }
}


.info-icon {
  cursor: pointer;

  &:hover {
    background-color: #aaa6a6;
    border-radius: 15px;
  }
}

</style>
