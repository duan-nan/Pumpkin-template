package com.pumpkin.disease.enums.file;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author: Pumpkin
 * @description: 文件类型枚举
 * @since : 2023/3/3
 */
@Getter
@AllArgsConstructor
public enum FileTypeEnum {

    /**
     * 图片类型
     */
    PICTURE("picture"),
    /**
     * 视频类型
     */
    VIDEO("video"),
    /**
     * 音频类型
     */
    MUSIC("music"),
    /**
     * 富文本
     */
    RICH_TEXT("rich_text")
    ;

    private final String fileType;
}
