import axios from "axios";
import router from "@/router";
import {notification} from "ant-design-vue";

//=======================  请求拦截器  ===========================
axios.interceptors.request.use(
    config => {
        if (window.sessionStorage.getItem('Authorization')) {
            config.headers['Authorization'] = window.sessionStorage.getItem('Authorization');
        }
        return config;
    }, error => {
        console.log("Something Wrong With The Request ==>  ", error);
    }
)

//=======================  响应(Response)拦截器  ===========================
axios.interceptors.response.use(
    config => {
        if (config.data.message === "用户未登陆,请登陆！") {
            router.replace('/login');
            return;
        }
        if (!config.data.success) {
            notification.error({message: config.data.message});
        }
        return config.data;
    }, error => {
        if (error.response.code === 404 || error.response.code === 504) {
            message.error({message: '服务器被怪兽吃掉了 gg'})
        } else if (error.response.code === 401) {
            message.error({message: '尚未登陆，请登陆！'})
            router.replace('/');
        } else if (error.response.code === 403) {
            message.error({message: '没有权限，请联系管理员！'})
        }
    }
);

//=======================  请求封装  ===========================

var baseUrl = 'http://127.0.0.1:9999';

// Get
export const getRequest = (url, params) => {
    return axios({
        method: 'get',
        url: `${baseUrl}${url}`,
        params: params
    });
};

// Post  Data（方法体传递数据） 方式
export const postDataRequest = (url, data) => {
    return axios({
        method: 'post',
        url: `${baseUrl}${url}`,
        data: data
    });
};

// 上传图片
export const uploadRequest = (url, data) => {
    return axios({
        method: 'post',
        url: `${baseUrl}${url}`,
        data: data,
        headers: {
            'Content-Type': 'multipart/form-data'
        },
    });
};

// Post  Params（路径拼接参数） 方式
export const postParamsRequest = (url, params) => {
    return axios({
        method: 'post',
        url: `${baseUrl}${url}`,
        params: params
    });
};

// Put  Data（方法体传递数据） 方式
export const putDataRequest = (url, data) => {
    return axios({
        method: 'put',
        url: `${baseUrl}${url}`,
        data: data
    });
};

// Put  Params（路径拼接参数） 方式
export const putParamsRequest = (url, params) => {
    return axios({
        method: 'put',
        url: `${baseUrl}${url}`,
        params: params
    });
};

// Delete
export const deleteRequest = (url, params) => {
    return axios({
        method: 'delete',
        url: `${baseUrl}${url}`,
        params: params
    });
};

export const deleteDataRequest = (url, params) => {
    return axios({
        method: 'delete',
        url: `${baseUrl}${url}`,
        data: params
    });
};

