package com.pumpkin.disease.service;

import com.pumpkin.disease.base.request.IDRequest;
import com.pumpkin.disease.base.result.PageResult;
import com.pumpkin.disease.entity.ObjectStore;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pumpkin.disease.request.system.website.ObjectStoreConditionRequest;
import com.pumpkin.disease.request.system.website.ObjectStoreRequest;
import com.pumpkin.disease.request.system.website.ObjectStoreStatusRequest;
import com.pumpkin.disease.response.system.website.ObjectStoreBasicInfoResponse;
import com.pumpkin.disease.response.system.website.ObjectStoreResponse;
import com.pumpkin.disease.response.system.website.ObjectStoreSelectResponse;

import java.util.List;

/**
 * <p>
 * 对象存储表 服务类
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
public interface ObjectStoreService extends IService<ObjectStore> {

    /**
     * 查询对象存储信息
     *
     * @param objectStoreConditionRequest 请求入参
     * @return {@link ObjectStoreResponse}
     */
    PageResult<ObjectStoreResponse> listObjectStore(ObjectStoreConditionRequest objectStoreConditionRequest);

    /**
     * 查询对象存储基础信息
     *
     * @return {@link  ObjectStoreBasicInfoResponse} 对象存储基础信息
     */
    List<ObjectStoreBasicInfoResponse> listObjectStoreBasicInfo();

    /**
     * 查询对象存储信息
     *
     * @param objectStoreId 对象存储id
     * @return {@link ObjectStoreResponse} 对象存储对象
     */
    ObjectStoreResponse getObjectStoreInfo(Integer objectStoreId);

    /**
     * 添加对象存储信息
     *
     * @param objectStoreRequest 请求入参
     */
    void saveObjectStore(ObjectStoreRequest objectStoreRequest);

    /**
     * 修改对象存储信息
     *
     * @param objectStoreRequest 对象存储请求入参
     * @return 是否修改成功
     */
    void updateObjectStore(ObjectStoreRequest objectStoreRequest);

    /**
     * 更新对象存储状态
     *
     * @param objectStoreStatusRequest 对象存储请求入参
     */
    void updateObjectStoreStatus(ObjectStoreStatusRequest objectStoreStatusRequest);

    /**
     * 批量删除对象存储信息
     *
     * @param idRequest id集合
     */
    void deleteObjectStore(IDRequest<Long> idRequest);

    /**
     * 根据对象存储标签获取对象存储信息
     *
     * @param objectStoreLabel 对象存储标签
     * @return {@link  ObjectStore} 对象存储
     */
    ObjectStore getObjectStoreByLabel(String objectStoreLabel);

    /**
     * 查询对象存储下拉框信息
     *
     * @return {@link  ObjectStoreSelectResponse} 对象存储下拉框信息
     */
    List<ObjectStoreSelectResponse> listObjectStoreSelect();
}
