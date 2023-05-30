import Vue from 'vue'
import Vuex from 'vuex'
import persistedstate from 'vuex-persistedstate';

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        //==================  全局配制  ====================
        collapsed: false,
        userInfo: null,
        //==================  菜单历史  ====================
        routerHistory: [],
        //================== 菜单路由信息 ===================
        menuRouterList: [],

    },
    mutations: {
        //==================  全局配制  ====================
        CHANGE_COLLAPSE_STATUS(state) {
            state.collapsed = !state.collapsed;
        },
        UPDATE_USERINFO(state,data){
            state.userInfo = data;
        },
        //==================  菜单历史  ====================
        SAVE_ROUTER_HISTORY(state, tab) {
            if (state.routerHistory.findIndex(item => item.name === tab.name) === -1 &&
                state.routerHistory.findIndex(item => item.path === tab.path) === -1 &&
                tab.name !== '控制台') {
                state.routerHistory.push({name: tab.name, path: tab.path});
            }
        },
        REMOVE_ROUTE_HISTORY(state, tab) {
            state.routerHistory.splice(state.routerHistory.findIndex(item => item.name === tab.name), 1);
        },
        RESET_ROUTE_HISTORY(state) {
            state.routerHistory = [];
        },
        //================== 菜单路由信息 ===================
        UPDATE_MENU_ROUTERS_LIST(state, data) {
            state.menuRouterList = data;
        },
    },
    actions: {},
    // 持久化存储
    plugins: [persistedstate({storage: window.sessionStorage})],
})
