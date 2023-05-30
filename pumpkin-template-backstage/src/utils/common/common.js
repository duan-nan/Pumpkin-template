import {Modal, notification} from "ant-design-vue";


/**
 * 清空表单信息
 * @param clearForm 清空所调用的方法
 */
export const clearFormData = (clearForm) => {
    Modal.confirm({
        title: "警告",
        content: "请确认数据保存成功，离开将不会保存！",
        centered: true,
        okText: "确定",
        cancelText: "取消",
        onOk() {
            clearForm();
        },
    });
};

/**
 * 删除单条数据
 * @param type 删除类型
 * @param fn 删除所调用的方法
 * @param idList id集合
 * @param initData 初始化方法
 */
export const deleteOneData = (type, fn, idList, initData) => {
    Modal.confirm({
        title: "警告",
        content: `确定要删除这些${type}信息吗？`,
        centered: true,
        okText: "确定",
        cancelText: "取消",
        async onOk() {
            const res = await fn(idList);
            if (res.success) {
                notification.success(res.message);
            }
            initData();
        },
    });
};


/**
 * 批量删除数据
 * @param type 删除类型
 * @param fn 删除所调用的方法
 * @param idList id集合
 * @param initData 初始化方法
 */
export const deleteManyData = (type, fn, idList, initData) => {
    Modal.confirm({
        title: "警告",
        content: `确定要删除这些${type}信息吗？`,
        centered: true,
        okText: "确定",
        cancelText: "取消",
        async onOk() {
            const res = await fn(idList);
            if (res.success) {
                notification.success(res.message);
            }
            initData();
        },
    });
}
