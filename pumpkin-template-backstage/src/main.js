import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'


//==============   Css   ===============
import "@/assets/css/global.css";
import "animate.css";

//==============   Js   ===============
import {initMenuRouter} from "@/permission";
import {getUserInfo} from "@/api/userInfoApi";

//==============  UI引入    ===============
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css';

//==============  Plugin    ===============
import SliderVerificationCode from 'slider-verification-code';
import 'slider-verification-code/lib/slider-verification-code.css';

import NProgress from 'nprogress';
import 'nprogress/nprogress.css';

import Viewer from 'v-viewer'
import 'viewerjs/dist/viewer.css'


//============== Vue组件注册 ===============
Vue.use(Antd);
Vue.use(SliderVerificationCode);
Vue.use(Viewer);


//=======================  v-viewer配置  ===========================
Viewer.setDefaults({
    Options: {
        "inline": true,
        "button": true,
        "navbar": true,
        "title": true,
        "toolbar": true,
        "tooltip": true,
        "movable": true,
        "zoomable": true,
        "rotatable": true,
        "scalable": true,
        "transition": true,
        "fullscreen": true,
        "keyboard": true,
        "url": "data-source"
    }
});
//=======================  NProgress配置  ===========================
NProgress.configure({
    easing: "ease", // 动画方式
    speed: 400, // 递增进度条的速度
    showSpinner: false, // 是否显示加载ico
    trickleSpeed: 200, // 自动递增间隔
    minimum: 0.3 // 初始化时的最小百分比
});


//=======================  路由守卫  ===========================
router.beforeEach((async (to, from, next) => {
    NProgress.start();
    if (window.sessionStorage.getItem('Authorization')) {
        initMenuRouter(store, router);

        if (store.state.userInfo === null) {
            const res = await getUserInfo();
            store.commit('UPDATE_USERINFO', res.data.principal);
        }

    } else {
        if (to.path === '/login') {
            next();
        } else {
            next('/login');
        }
    }
    next();
}));

router.afterEach(((to, from) => {
    NProgress.done();
}));


Vue.config.productionTip = false

new Vue({
    router,
    store,
    render: function (h) {
        return h(App)
    }
}).$mount('#app')
