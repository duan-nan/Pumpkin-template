import {uploadRequest} from "@/utils/axios/request";


/**
 * 上传图片文件测试
 * @param file 文件
 * @param mode 模块
 * @param uploadModule 上传模块
 * @param uploadPath 上传路径
 * @returns {*}
 */
export const uploadPictureTest = (file, mode, uploadModule, uploadPath) => {

    let formData = {
        "file": file,
        "mode": mode,
        "uploadModule": uploadModule,
        "uploadPath": uploadPath
    }
    return uploadRequest('/upload/test', formData);
}

/**
 * 上传图片文件
 * @param file 文件
 * @param uploadModule 上传模块
 * @param uploadPath 上传路径
 * @returns {*}
 */
export const uploadPicture = (file, uploadModule, uploadPath) => {

    let formData = {
        "file": file,
        "uploadModule": uploadModule,
        "uploadPath": uploadPath
    }
    return uploadRequest('/upload/picture', formData);
}

/**
 * 上传视频文件
 * @param params 请求参数
 * @returns {*}
 */
export const uploadVideo = (params) => {
    return uploadRequest('/upload/video', params);
}

/**
 * 上传音频文件
 * @param params 请求参数
 * @returns {*}
 */
export const uploadMusic = (params) => {
    return uploadRequest('/upload/music', params);
}
