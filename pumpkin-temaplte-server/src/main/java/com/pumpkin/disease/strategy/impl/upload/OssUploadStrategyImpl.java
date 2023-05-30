package com.pumpkin.disease.strategy.impl.upload;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.pumpkin.disease.base.exception.BaseException;
import com.pumpkin.disease.entity.ObjectStore;
import com.pumpkin.disease.enums.file.UploadModeEnum;
import com.pumpkin.disease.service.ObjectStoreService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.pumpkin.disease.constant.BusinessErrorConstant.ObjectStoreError.*;
import static com.pumpkin.disease.constant.StrategyServiceConstant.Upload.OSS_UPLOAD_STRATEGY_SERVICE_NAME;

/**
 * @author: Pumpkin
 * @description: 阿里云Oss上传策略实现类
 * @since : 2023/3/1
 */
@Slf4j
@Getter
@Setter
@RequiredArgsConstructor
@Service(OSS_UPLOAD_STRATEGY_SERVICE_NAME)
public class OssUploadStrategyImpl extends AbstractUploadStrategyImpl {

    private final ObjectStoreService objectStoreService;

    private OSS ossClient;

    private ObjectStore objectStore;


    @Override
    public void initClient() {
        objectStore = objectStoreService.getObjectStoreByLabel(UploadModeEnum.OSS_UPLOAD.getMode());
        ossClient = new OSSClientBuilder().build(objectStore.getEndpoint(), objectStore.getAccessKey(), objectStore.getAccessKeySecret());
        log.info("OssClient Init Success...");
    }

    @Override
    public boolean checkFileIsExisted(String fileRelativePath, String fileName) {
        boolean isExisted = false;
        try {
            isExisted = ossClient.doesObjectExist(objectStore.getBucket(), fileRelativePath + fileName);
        }catch (Exception e){
            throw new BaseException(OBJECT_STORE_CONNECT_ERROR);
        }
        return isExisted;
    }

    @Override
    public void executeUpload(MultipartFile file, String fileRelativePath, String fileName) throws IOException {
        log.info("File Upload Starts...");
        try {
            ossClient.putObject(objectStore.getBucket(), fileRelativePath + fileName, file.getInputStream());
        }catch (Exception e){
            throw new BaseException(NO_SUCH_BUCKET_ERROR);
        }
        log.info("File Upload Finish...");

    }

    @Override
    public String getPublicNetworkAccessUrl(String fileRelativePath, String fileName) {
        return objectStore.getDomainUrl() + fileRelativePath + fileName;
    }
}
