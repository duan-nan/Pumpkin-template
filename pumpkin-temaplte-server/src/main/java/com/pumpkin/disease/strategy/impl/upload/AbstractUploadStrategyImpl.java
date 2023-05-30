package com.pumpkin.disease.strategy.impl.upload;

import com.pumpkin.disease.base.exception.BaseException;
import com.pumpkin.disease.strategy.UploadStrategy;
import com.pumpkin.disease.utils.file.FileUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.pumpkin.disease.constant.BusinessErrorConstant.FileError.FILE_UPLOAD_ERROR;

/**
 * @author: Pumpkin
 * @description: 上传策略抽象模板实现类
 * @since : 2023/3/1
 */
public abstract class AbstractUploadStrategyImpl implements UploadStrategy {

    /**
     * 初始化客户端
     */
    public abstract void initClient();

    /**
     * 检查文件是否已经存在（文件MD5值唯一）
     *
     * @param fileRelativePath 文件相对路径
     * @param fileName         文件名称
     * @return true 已经存在  false 不存在
     */
    public abstract boolean checkFileIsExisted(String fileRelativePath, String fileName);

    /**
     * 执行上传操作
     *
     * @param file             文件
     * @param fileRelativePath 文件相对路径
     * @param fileName         文件名称
     * @throws IOException io异常信息
     */
    public abstract void executeUpload(MultipartFile file, String fileRelativePath, String fileName) throws IOException;

    /**
     * 获取公网访问路径
     *
     * @param fileRelativePath 文件相对路径
     * @param fileName         文件名称
     * @return 公网访问绝对路径
     */
    public abstract String getPublicNetworkAccessUrl(String fileRelativePath, String fileName);

    @Override
    public String uploadFile(MultipartFile file, String fileRelativePath) {
        try {
            //region 获取文件md5值 -> 获取文件后缀名 -> 生成相对路径
            String fileMd5 = FileUtil.getMd5(file.getInputStream());
            String extName = FileUtil.getExtName(file.getOriginalFilename());
            String fileName = fileMd5 + extName;
            //endregion
            //region 初始化
            initClient();
            //endregion
            //region 检测文件是否已经存在，不存在则进行上传操作
            if (!checkFileIsExisted(fileRelativePath, fileName)) {
                executeUpload(file, fileRelativePath, fileName);
            }
            //endregion
            return getPublicNetworkAccessUrl(fileRelativePath, fileName);
        } catch (IOException e) {
            throw new BaseException(FILE_UPLOAD_ERROR);
        }

    }

}
