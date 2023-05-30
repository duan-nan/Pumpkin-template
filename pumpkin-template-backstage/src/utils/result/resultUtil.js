import {notification} from "ant-design-vue";

/**
 * 检查是否 新增\更新\删除成功
 * @param res
 */
export const checkResult = (res) => {
    if (res.success) {
        notification.success({message: res.message});
    }
};
