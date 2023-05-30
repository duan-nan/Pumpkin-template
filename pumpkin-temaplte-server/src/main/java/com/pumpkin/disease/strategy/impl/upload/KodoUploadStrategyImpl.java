package com.pumpkin.disease.strategy.impl.upload;

import com.pumpkin.disease.base.exception.BaseException;
import com.pumpkin.disease.entity.ObjectStore;
import com.pumpkin.disease.enums.file.UploadModeEnum;
import com.pumpkin.disease.service.ObjectStoreService;
import com.qiniu.common.QiniuException;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;

import static com.pumpkin.disease.constant.BusinessErrorConstant.ObjectStoreError.OBJECT_STORE_CONNECT_ERROR;
import static com.pumpkin.disease.constant.StrategyServiceConstant.Upload.KODO_UPLOAD_STRATEGY_SERVICE_NAME;

/**
 * @author: Pumpkin
 * @description: 七牛云Kodo上传策略实现类
 * @since : 2023/3/1
 */
@Slf4j
@Getter
@Setter
@RequiredArgsConstructor
@Service(KODO_UPLOAD_STRATEGY_SERVICE_NAME)
public class KodoUploadStrategyImpl extends AbstractUploadStrategyImpl {

    private final ObjectStoreService objectStoreService;

    /**
     * upToken
     */
    private String upToken;

    /**
     * 上传Manger
     */
    private UploadManager uploadManager;

    /**
     * 存储桶Manger
     */
    private BucketManager bucketManager;

    private ObjectStore objectStore;


    @Override
    public void initClient() {
        objectStore = objectStoreService.getObjectStoreByLabel(UploadModeEnum.KODO_UPLOAD.getMode());
        Auth auth = Auth.create(objectStore.getAccessKey(), objectStore.getAccessKeySecret());
        upToken = auth.uploadToken(objectStore.getBucket());
        Configuration cfg = new Configuration(Region.region0());
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;
        uploadManager = new UploadManager(cfg);
        bucketManager = new BucketManager(auth, cfg);
        log.info("KodoClient Init Success...");
    }

    @Override
    public boolean checkFileIsExisted(String fileRelativePath, String fileName) {
        try {
            if (null == bucketManager.stat(objectStore.getBucket(), fileRelativePath + fileName)) {
                return false;
            }
        } catch (QiniuException e) {
            return false;
        }
        return true;

    }

    @Override
    public void executeUpload(MultipartFile file, String fileRelativePath, String fileName) throws IOException {
        try {
            uploadManager.put(file.getInputStream(), fileRelativePath + fileName, upToken, null, null);
        } catch (IOException e) {
            throw new BaseException(OBJECT_STORE_CONNECT_ERROR);
        }

    }

    @Override
    public String getPublicNetworkAccessUrl(String fileRelativePath, String fileName) {
        return objectStore.getDomainUrl() + fileRelativePath + fileName;
    }
}
