package com.pumpkin.disease.base.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;


/**
 * @author Pumpkin
 * @version 1.0
 * @description 基础分页Request类
 * @date 2022/12/8
 */
@Getter
@Setter
public class BaseRequest {

    /**
     * 当前页码
     */
    @ApiModelProperty(value = "当前页码", name = "pageNow", example = "1")
    @Min(value = 0, message = "页码必须大于等于0")
    private Integer pageNow = 1;

    /**
     * 当前页面大小
     */
    @ApiModelProperty(value = "当前页面大小", name = "pageSize", example = "10")
    @Min(value = 1, message = "页码大小必须大于等于1")
    @Max(value = 100, message = "页码大小不能超过100")
    private Integer pageSize = 10;

    /**
     * 关键字
     */
    @ApiModelProperty(value = "关键字", name = "keywords")
    private String keywords;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间", name = "beginTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime beginTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间", name = "endTime")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
}
