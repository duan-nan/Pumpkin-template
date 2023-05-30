package com.pumpkin.disease.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pumpkin.disease.base.request.IDRequest;
import com.pumpkin.disease.base.result.PageResult;
import com.pumpkin.disease.entity.ObjectStore;
import com.pumpkin.disease.enums.file.UploadModeEnum;
import com.pumpkin.disease.mapper.ObjectStoreMapper;
import com.pumpkin.disease.request.system.website.ObjectStoreConditionRequest;
import com.pumpkin.disease.request.system.website.ObjectStoreRequest;
import com.pumpkin.disease.request.system.website.ObjectStoreStatusRequest;
import com.pumpkin.disease.response.system.website.ObjectStoreBasicInfoResponse;
import com.pumpkin.disease.response.system.website.ObjectStoreResponse;
import com.pumpkin.disease.response.system.website.ObjectStoreSelectResponse;
import com.pumpkin.disease.service.ObjectStoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pumpkin.disease.utils.bean.BeanCopyUtil;
import com.pumpkin.disease.utils.redis.RedisCache;
import com.pumpkin.disease.utils.vaild.AssertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.pumpkin.disease.constant.BusinessErrorConstant.SimpleError.*;
import static com.pumpkin.disease.constant.RedisPrefixConstant.ObjectStorePrefix.BASIC_INFO_LIST;
import static com.pumpkin.disease.constant.RedisPrefixConstant.ObjectStorePrefix.DETAIL_INFO;
import static com.pumpkin.disease.constant.SystemConstant.Number.NOT_DELETED;

/**
 * <p>
 * 对象存储表 服务实现类
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
@Service
@RequiredArgsConstructor
public class ObjectStoreServiceImpl extends ServiceImpl<ObjectStoreMapper, ObjectStore> implements ObjectStoreService {

    private final RedisCache redisCache;

    @Override
    public PageResult<ObjectStoreResponse> listObjectStore(ObjectStoreConditionRequest request) {
        IPage<ObjectStoreResponse> iPage = baseMapper.listObjectStore(new Page<ObjectStore>(request.getPageNow(), request.getPageSize()), request);
        return new PageResult<>(iPage.getTotal(), iPage.getRecords());
    }

    @Override
    public List<ObjectStoreBasicInfoResponse> listObjectStoreBasicInfo() {
        //region 查询缓存信息
        List<ObjectStoreBasicInfoResponse> objectStoreBasicInfoResponses = redisCache.getCacheList(BASIC_INFO_LIST);
        //endregion
        if (AssertUtil.isListEmpty(objectStoreBasicInfoResponses)) {
            objectStoreBasicInfoResponses = baseMapper.listObjectStoreBasicInfo();
            //region 设置缓存
            redisCache.setCacheList(BASIC_INFO_LIST, objectStoreBasicInfoResponses);
            //endregion
        }
        return objectStoreBasicInfoResponses;
    }

    @Override
    public ObjectStoreResponse getObjectStoreInfo(Integer objectStoreId) {

        //region 查询缓存中是否含有数据
        ObjectStoreResponse response = redisCache.getCacheObject(DETAIL_INFO + objectStoreId);
        //endregion

        if (AssertUtil.isNull(response)) {
            response = baseMapper.getObjectStoreInfo(objectStoreId);
            //region 添加缓存数据
            redisCache.setCacheObject(DETAIL_INFO + objectStoreId, response);
            //endregion
        }

        return response;
    }

    @Override
    public void saveObjectStore(ObjectStoreRequest objectStoreRequest) {
        //region 数据转换校验
        ObjectStore objectStore = BeanCopyUtil.copyObject(objectStoreRequest, ObjectStore.class);
        AssertUtil.assertIsNotPass(AssertUtil.isNull(objectStore), DATA_TRANSFORMATION_ERROR);
        //endregion
        AssertUtil.assertIsNotPass(AssertUtil.isSaveFail(baseMapper.insert(objectStore)), SAVE_DATA_FAIL);
        redisCache.deleteObject(BASIC_INFO_LIST);
    }

    @Override
    public void updateObjectStore(ObjectStoreRequest objectStoreRequest) {
        //region 数据转换校验
        AssertUtil.assertIsNotPass(AssertUtil.isNull(objectStoreRequest.getId()), MISSING_NECESSARY_PARAMETER);
        ObjectStore objectStore = BeanCopyUtil.copyObject(objectStoreRequest, ObjectStore.class);
        AssertUtil.assertIsNotPass(AssertUtil.isNull(objectStore), DATA_TRANSFORMATION_ERROR);
        objectStore.setIsPassTest(0);
        //endregion
        AssertUtil.assertIsNotPass(AssertUtil.isSaveFail(baseMapper.updateById(objectStore)), UPDATE_DATA_FAIL);
        redisCache.deleteObject(BASIC_INFO_LIST);
        redisCache.deleteObject(DETAIL_INFO + objectStoreRequest.getId());
    }

    @Override
    public void updateObjectStoreStatus(ObjectStoreStatusRequest request) {
        //region 数据转换校验
        AssertUtil.assertIsNotPass(AssertUtil.isNull(request.getId()), MISSING_NECESSARY_PARAMETER);
        AssertUtil.assertIsNotPass(AssertUtil.isNull(request.getIsPassTest()), MISSING_NECESSARY_PARAMETER);
        //endregion

        //region 查询记录是否存在
        ObjectStore objectStore = baseMapper.selectOne(new LambdaQueryWrapper<ObjectStore>()
                .eq(ObjectStore::getIsDeleted, NOT_DELETED)
                .eq(ObjectStore::getId, request.getId()));
        AssertUtil.assertIsNotPass(AssertUtil.isNull(objectStore), QUERY_RECORD_NOT_EXISTED);
        //endregion

        //region 更新数据
        objectStore.setIsPassTest(request.getIsPassTest());
        AssertUtil.assertIsNotPass(AssertUtil.isUpdateOneFail(baseMapper.updateById(objectStore)), UPDATE_DATA_FAIL);
        //endregion
        redisCache.deleteObject(BASIC_INFO_LIST);
    }

    @Override
    public void deleteObjectStore(IDRequest<Long> idRequest) {
        if (idRequest.getIds().size() > 0) {
            idRequest.getIds().forEach(baseMapper::deleteById);
        }
        redisCache.deleteObject(BASIC_INFO_LIST);
    }

    @Override
    public ObjectStore getObjectStoreByLabel(String objectStoreLabel) {
        return baseMapper.selectOne(new LambdaQueryWrapper<ObjectStore>()
                .eq(ObjectStore::getIsDeleted, NOT_DELETED)
                .eq(ObjectStore::getObjectStoreLabel, objectStoreLabel));
    }

    @Override
    public List<ObjectStoreSelectResponse> listObjectStoreSelect() {
        List<ObjectStoreSelectResponse> objectStoreList = UploadModeEnum.OBJECT_STORE_SELECT_RESPONSE_LIST;
        objectStoreList.forEach(objectStore -> objectStore.setDisabled(getObjectStoreByLabel(objectStore.getLabel()) != null));
        return objectStoreList;
    }


}
