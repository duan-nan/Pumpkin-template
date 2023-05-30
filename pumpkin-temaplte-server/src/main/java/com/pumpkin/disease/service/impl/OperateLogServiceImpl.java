package com.pumpkin.disease.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pumpkin.disease.base.request.IDRequest;
import com.pumpkin.disease.base.result.PageResult;
import com.pumpkin.disease.entity.OperateLog;
import com.pumpkin.disease.mapper.OperateLogMapper;
import com.pumpkin.disease.request.system.log.OperateLogRequest;
import com.pumpkin.disease.service.OperateLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pumpkin.disease.utils.vaild.AssertUtil;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 操作日志表 服务实现类
 * </p>
 *
 * @author Pumpkin
 * @since 2022-11-30 10:18:24
 */
@Service
public class OperateLogServiceImpl extends ServiceImpl<OperateLogMapper, OperateLog> implements OperateLogService {

    @Override
    public PageResult<OperateLog> listOperateLog(OperateLogRequest request) {
        IPage<OperateLog> iPage = baseMapper.listOperateLog(new Page<OperateLog>(request.getPageNow(), request.getPageSize()), request);
        return new PageResult<>(iPage.getTotal(), iPage.getRecords());
    }

    @Override
    public void batchDeleteOperateLog(IDRequest<Long> idRequest) {
        if (AssertUtil.isNotNull(idRequest) && idRequest.getIds().size() > 0) {
            idRequest.getIds().forEach(baseMapper::deleteById);
        }
    }
}
