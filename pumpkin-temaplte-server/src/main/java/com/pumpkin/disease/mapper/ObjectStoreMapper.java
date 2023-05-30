package com.pumpkin.disease.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pumpkin.disease.entity.ObjectStore;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pumpkin.disease.request.system.website.ObjectStoreConditionRequest;
import com.pumpkin.disease.response.system.website.ObjectStoreBasicInfoResponse;
import com.pumpkin.disease.response.system.website.ObjectStoreResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 对象存储表 Mapper 接口
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
public interface ObjectStoreMapper extends BaseMapper<ObjectStore> {

    /**
     * 查询对象存储数据
     * @param objectStorePage 分页对象
     * @param request 请求入参
     * @return {@link ObjectStoreResponse} 响应数据
     */
    IPage<ObjectStoreResponse> listObjectStore(Page<ObjectStore> objectStorePage, @Param("request") ObjectStoreConditionRequest request);

    /**
     * 查询对象存储信息
     *
     * @param objectStoreId 对象存储id
     * @return {@link ObjectStoreResponse} 对象存储对象
     */
    ObjectStoreResponse getObjectStoreInfo(@Param("objectStoreId") Integer objectStoreId);

    /**
     * 检测对象存储本地存储是否存在
     * @param objectStoreLabel 对象存储标识
     * @return {@link ObjectStore} 对象存储信息
     */
    ObjectStore checkIsProjectExisted(@Param("objectStoreId") String objectStoreLabel);

    /**
     * 查询对象存储基础信息
     *
     * @return {@link  ObjectStoreBasicInfoResponse} 对象存储基础信息
     */
    List<ObjectStoreBasicInfoResponse> listObjectStoreBasicInfo();
}
