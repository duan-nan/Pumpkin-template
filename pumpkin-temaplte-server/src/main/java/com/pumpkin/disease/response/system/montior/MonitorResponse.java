package com.pumpkin.disease.response.system.montior;

import com.pumpkin.disease.entity.monitor.Cpu;
import com.pumpkin.disease.entity.monitor.Jvm;
import com.pumpkin.disease.entity.monitor.Mem;
import lombok.Data;

/**
 * @author Pumpkin
 * @version 1.0
 * @description 内存信息Response类
 * @date 2022/7/20
 */
@Data
public class MonitorResponse {

    /**
     * JVM 信息
     */
    private Jvm jvm;

    /**
     * CPU 信息
     */
    private Cpu cpu;

    /**
     * MEM 信息
     */
    private Mem mem;

}
