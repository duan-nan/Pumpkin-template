package com.pumpkin.disease.strategy.impl.upload;

import com.pumpkin.disease.base.exception.BaseException;
import com.pumpkin.disease.entity.ObjectStore;
import com.pumpkin.disease.enums.http.HttpCodeEnum;
import com.pumpkin.disease.enums.file.UploadModeEnum;
import com.pumpkin.disease.service.ObjectStoreService;
import com.pumpkin.disease.utils.vaild.AssertUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;


import static com.pumpkin.disease.constant.BusinessErrorConstant.FileError.FILE_NOT_EXISTED;
import static com.pumpkin.disease.constant.StrategyServiceConstant.Upload.PROJECT_UPLOAD_STRATEGY_SERVICE_NAME;
import static com.pumpkin.disease.constant.SystemConstant.FileUpload.*;

/**
 * @author: Pumpkin
 * @description: 本地项目上传策略实现类
 * @since : 2023/3/1
 */
@Slf4j
@Getter
@Setter
@RequiredArgsConstructor
@Service(PROJECT_UPLOAD_STRATEGY_SERVICE_NAME)
public class ProjectUploadStrategyImpl extends AbstractUploadStrategyImpl {

    private final ObjectStoreService objectStoreService;

    /**
     * 本地项目端口
     */
    @Value("${server.port}")
    private Integer port;

    /**
     * 前置路径 ip/域名
     */
    private String prefixUrl;

    /**
     * 资源路径Url
     */
    private String resourceUrl;

    private ObjectStore objectStore;

    @Override
    public void initClient() {
        objectStore = objectStoreService.getObjectStoreByLabel(UploadModeEnum.PROJECT_UPLOAD.getMode());

        try {
            String os = System.getProperty("os.name");
            if (os.toLowerCase().startsWith("win")) {
                //region windows系统
                String path = System.getProperty("user.dir");
                prefixUrl = path + FILE_PROJECT_UPLOAD_PATH;
                resourceUrl = path + FILE_RESOURCE_PATH;
                //endregion
            } else {
                //region linux/mac 系统
                prefixUrl = new File(ResourceUtils.getURL("classpath:").getPath()).getAbsolutePath() + FILE_PROJECT_UPLOAD_PATH;
                resourceUrl = System.getProperty("os.name") + FILE_RESOURCE_PATH;
                //endregion
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new BaseException(FILE_NOT_EXISTED);
        }
        log.info("Local Project Init Success...");
    }

    @Override
    public boolean checkFileIsExisted(String fileRelativePath, String fileName) {
        return new File(prefixUrl + fileRelativePath + fileName).exists();
    }

    @Override
    public void executeUpload(MultipartFile file, String fileRelativePath, String fileName) throws IOException {
        File dest = checkFolderIsExisted(false,fileRelativePath);
        File resourcesDest = checkFolderIsExisted(true,fileRelativePath);
        try {
            file.transferTo(new File(resourcesDest + "/" + fileName));
            file.transferTo(new File(dest + "/" + fileName));
        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseException("文件上传失败");
        }
    }

    @Override
    public String getPublicNetworkAccessUrl(String fileRelativePath, String fileName) {
        try {
            String host = InetAddress.getLocalHost().getHostAddress();
            if (AssertUtil.isNull(objectStore.getDomainUrl())) {
                return String.format("http://%s:%d%s/%s", host, port, SYSTEM_STORE_PATH, fileRelativePath + fileName);
            }
            return objectStore.getDomainUrl() + SYSTEM_STORE_PATH + fileRelativePath + fileName;
        } catch (UnknownHostException e) {
            throw new BaseException(HttpCodeEnum.UNKNOWN_ERROR);
        }
    }

    /**
     * 检查文件夹是否存在，若不存在则创建文件夹，最终返回上传文件
     * @param isResource 是否为资源url
     * @param fileRelativePath 文件相对路径
     * @return {@link  File} 文件
     */
    private File checkFolderIsExisted(boolean isResource, String fileRelativePath) {
        File rootPath = new File((isResource ? resourceUrl : prefixUrl) + fileRelativePath);
        if (!rootPath.exists()) {
            if (!rootPath.mkdirs()) {
                throw new BaseException("文件夹创建失败");
            }
        }
        return rootPath;
    }

}
