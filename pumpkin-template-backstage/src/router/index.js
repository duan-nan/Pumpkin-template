import Vue from 'vue'
import VueRouter from 'vue-router'

//===================== 组件引入 ========================
import Login from "@/views/login/Login";
import Home from "@/views/Home";
import Person from "@/views/person/Person";

//================== 组件引入 ===================
Vue.use(VueRouter)

const routes = [
    {path: '/', name: 'home', redirect: '/login'},
    {path: '/login', name: 'login', component: Login},
    {
        path: '/home',
        name: 'home',
        component: Home,
        redirect: '/welcome',
        children: [
            {path: '/person', name: '个人中心', component: Person},
        ]
    },
]

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

const originalPush = VueRouter.prototype.push;
const originalReplace = VueRouter.prototype.replace;

// push
VueRouter.prototype.push = function push(location, onResolve, onReject) {
    if (onResolve || onReject)
        return originalPush.call(this, location, onResolve, onReject);
    return originalPush.call(this, location).catch(err => err);
};

//replace
VueRouter.prototype.replace = function push(location, onResolve, onReject) {
    if (onResolve || onReject)
        return originalReplace.call(this, location, onResolve, onReject);
    return originalReplace.call(this, location).catch(err => err);
};


export default router
