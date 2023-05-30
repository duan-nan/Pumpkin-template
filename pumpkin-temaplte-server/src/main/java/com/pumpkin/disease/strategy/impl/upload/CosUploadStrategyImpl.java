package com.pumpkin.disease.strategy.impl.upload;

import com.pumpkin.disease.base.exception.BaseException;
import com.pumpkin.disease.entity.ObjectStore;
import com.pumpkin.disease.enums.file.UploadModeEnum;
import com.pumpkin.disease.service.ObjectStoreService;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.region.Region;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.pumpkin.disease.constant.BusinessErrorConstant.ObjectStoreError.NO_SUCH_BUCKET_ERROR;
import static com.pumpkin.disease.constant.BusinessErrorConstant.ObjectStoreError.OBJECT_STORE_CONNECT_ERROR;
import static com.pumpkin.disease.constant.StrategyServiceConstant.Upload.COS_UPLOAD_STRATEGY_SERVICE_NAME;

/**
 * @author: Pumpkin
 * @description: 腾讯云Cos上传策略实现类
 * @since : 2023/3/1
 */
@Slf4j
@Getter
@Setter
@RequiredArgsConstructor
@Service(COS_UPLOAD_STRATEGY_SERVICE_NAME)
public class CosUploadStrategyImpl extends AbstractUploadStrategyImpl {

    private final ObjectStoreService objectStoreService;

    /**
     * 属性
     */
    private COSClient cosClient;

    private ObjectStore objectStore;

    @Override
    public void initClient() {
        objectStore = objectStoreService.getObjectStoreByLabel(UploadModeEnum.COS_UPLOAD.getMode());

        COSCredentials cred = new BasicCOSCredentials(objectStore.getAccessKey(), objectStore.getAccessKeySecret());
        //region ClientConfig 中包含了后续请求 COS 的客户端设置：
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setRegion(new Region(objectStore.getEndpoint()));
        clientConfig.setHttpProtocol(HttpProtocol.http);
        clientConfig.setSocketTimeout(30 * 1000);
        clientConfig.setConnectionTimeout(30 * 1000);
        //endregion

        // 生成 cos 客户端
        cosClient = new COSClient(cred, clientConfig);
        log.info("CosClient Init Success...");

    }

    @Override
    public boolean checkFileIsExisted(String fileRelativePath, String fileName) {
        boolean isExisted = false;
        try {
            isExisted = cosClient.doesObjectExist(objectStore.getBucket(), fileRelativePath + fileName);
        }catch (Exception e){
            throw new BaseException(OBJECT_STORE_CONNECT_ERROR);
        }
        return isExisted;
    }

    @Override
    public void executeUpload(MultipartFile file, String fileRelativePath, String fileName) throws IOException {
        log.info("File Upload Starts...");
        try {
            cosClient.putObject(objectStore.getBucket(), fileRelativePath + fileName, file.getInputStream(), null);
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
