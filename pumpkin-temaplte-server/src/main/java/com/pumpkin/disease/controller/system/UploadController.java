package com.pumpkin.disease.controller.system;

import com.pumpkin.disease.annotation.NormalOperateLog;
import com.pumpkin.disease.base.result.ResponseResult;
import com.pumpkin.disease.enums.file.FileTypeEnum;
import com.pumpkin.disease.strategy.context.UploadStrategyContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import static com.pumpkin.disease.constant.SystemConstant.LogType.UPLOAD_OPERATE;

/**
 * @author: Pumpkin
 * @description: 上传控制器
 * @since : 2023/3/3
 */
@RestController
@Api(tags = {"上传模块"})
@RequiredArgsConstructor
@RequestMapping("/upload")
public class UploadController {

    private final UploadStrategyContext uploadStrategyContext;

    /**
     * 上传文件测试
     *
     * @param file         文件
     * @param uploadModule 上传模块
     * @param uploadPath   上传路径
     * @return 图片请求路径
     */
    @ApiOperation(value = "[通用]上传文件测试")
    @NormalOperateLog(operateType = UPLOAD_OPERATE)
    @PostMapping("/test")
    public ResponseResult<String> uploadTest(@RequestPart("file") MultipartFile file, @RequestParam String mode, @RequestParam String uploadModule, @RequestParam String uploadPath) {
        return ResponseResult.success("上传图片成功！", uploadStrategyContext.executeUploadFile(file, mode, FileTypeEnum.PICTURE.getFileType() + uploadModule + uploadPath));
    }

    /**
     * 上传图片文件
     *
     * @param file         文件
     * @param uploadModule 上传模块
     * @param uploadPath   上传路径
     * @return 图片请求路径
     */
    @ApiOperation(value = "[通用]上传图片文件")
    @NormalOperateLog(operateType = UPLOAD_OPERATE)
    @PostMapping("/picture")
    public ResponseResult<String> uploadPic(@RequestPart("file") MultipartFile file, @RequestParam String uploadModule, @RequestParam String uploadPath) {
        return ResponseResult.success("上传图片成功！", uploadStrategyContext.executeUploadFile(file, FileTypeEnum.PICTURE.getFileType() + uploadModule + uploadPath));
    }

    /**
     * 上传视频文件
     *
     * @param file         文件
     * @param uploadModule 上传模块
     * @param uploadPath   上传路径
     * @return 视频请求路径
     */
    @ApiOperation(value = "[通用]上传视频文件")
    @NormalOperateLog(operateType = UPLOAD_OPERATE)
    @PostMapping("/video")
    public ResponseResult<String> uploadVideo(@RequestPart("file") MultipartFile file, @RequestParam String uploadModule, @RequestParam String uploadPath) {
        return ResponseResult.success("上传视频成功！", uploadStrategyContext.executeUploadFile(file, FileTypeEnum.VIDEO.getFileType() + uploadModule + uploadPath));
    }

    /**
     * 上传音频文件
     *
     * @param file         文件
     * @param uploadModule 上传模块
     * @param uploadPath   上传路径
     * @return 音频请求路径
     */
    @ApiOperation(value = "[通用]上传音频文件")
    @NormalOperateLog(operateType = UPLOAD_OPERATE)
    @PostMapping("/music")
    public ResponseResult<String> uploadMusic(@RequestPart("file") MultipartFile file, @RequestParam String uploadModule, @RequestParam String uploadPath) {
        return ResponseResult.success("上传音频成功！", uploadStrategyContext.executeUploadFile(file, FileTypeEnum.MUSIC.getFileType() + uploadModule + uploadPath));
    }

    /**
     * 上传富文本文件
     *
     * @param file         文件
     * @param uploadModule 上传模块
     * @param uploadPath   上传路径
     * @return 富文本请求路径
     */
    @ApiOperation(value = "[通用]上传富文本文件")
    @NormalOperateLog(operateType = UPLOAD_OPERATE)
    @PostMapping("/richText")
    public ResponseResult<String> uploadRichText(@RequestPart("file") MultipartFile file, @RequestParam String uploadModule, @RequestParam String uploadPath) {
        return ResponseResult.success("上传富文本成功！", uploadStrategyContext.executeUploadFile(file, FileTypeEnum.RICH_TEXT.getFileType() + uploadModule + uploadPath));
    }

}
