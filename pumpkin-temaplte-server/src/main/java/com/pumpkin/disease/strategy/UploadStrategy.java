package com.pumpkin.disease.strategy;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author: Pumpkin
 * @description: 文件上传策略
 * @since : 2023/2/21
 */
public interface UploadStrategy {

    /**
     * 上传图片
     * @param file 图片文件
     * @param uploadPath 上传路径
     * @return 资源请求路径
     */
    String uploadFile(MultipartFile file,String uploadPath);


}
