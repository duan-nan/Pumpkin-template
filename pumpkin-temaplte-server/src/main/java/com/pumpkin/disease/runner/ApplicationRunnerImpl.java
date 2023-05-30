package com.pumpkin.disease.runner;

import com.pumpkin.disease.entity.ObjectStore;
import com.pumpkin.disease.enums.file.UploadModeEnum;
import com.pumpkin.disease.mapper.ObjectStoreMapper;
import com.pumpkin.disease.utils.vaild.AssertUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.net.Inet4Address;
import java.net.UnknownHostException;

import static com.pumpkin.disease.constant.BusinessErrorConstant.SimpleError.SAVE_DATA_FAIL;
import static com.pumpkin.disease.constant.SystemConstant.FileUpload.FILE_PROJECT_UPLOAD_PATH;

/**
 * @author: Pumpkin
 * @description: 项目启动器
 * @since : 2023/3/6
 */
@Service
@RequiredArgsConstructor
public class ApplicationRunnerImpl implements ApplicationRunner {

    private final ObjectStoreMapper objectStoreMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        checkObjectStoreData();
    }

    /**
     * 对象存储数据检测
     */
    private void checkObjectStoreData() throws UnknownHostException {
        if (AssertUtil.isNull(objectStoreMapper.checkIsProjectExisted(UploadModeEnum.PROJECT_UPLOAD.getMode()))) {
            AssertUtil.assertIsNotPass(AssertUtil.isSaveFail(objectStoreMapper.insert(genderObjectStore())), SAVE_DATA_FAIL);
        }
    }

    /**
     * 构建对象数据
     *
     * @return {@link ObjectStore} 对象存储
     */
    private ObjectStore genderObjectStore() throws UnknownHostException {
        return new ObjectStore() {{
            setObjectStoreName("本地上传");
            setObjectStoreLabel("Project");
            setDomainUrl(Inet4Address.getLocalHost().getHostAddress() + "/");
            setEndpoint("本地");
            setBucket(FILE_PROJECT_UPLOAD_PATH);
            setIsPerfect(1);
            setIsPassTest(1);
            setIsDeleted(0);
        }};
    }

}
