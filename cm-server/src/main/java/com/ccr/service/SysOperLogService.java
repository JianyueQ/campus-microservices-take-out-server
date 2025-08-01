package com.ccr.service;

import com.ccr.dto.LogPageDTO;
import com.ccr.entity.OperationLog;
import com.ccr.result.PageResult;
import com.ccr.vo.OperationLogDetailVO;

import java.util.List;

/**
 * @author 31373
 */
public interface SysOperLogService {

    /**
     * 新增操作日志
     *
     * @param operationLog 操作日志
     */
    public void insertOperLog(OperationLog operationLog);

    /**
     * 分页查询操作日志
     *
     * @param logPageDTO 查询参数
     * @return 分页数据
     */
    PageResult logPage(LogPageDTO logPageDTO);

    /**
     * 查询操作日志详情
     *
     * @param id 日志id
     * @return 日志详情
     */
    OperationLogDetailVO logDetail(Long id);

    /**
     * 删除操作日志-批量删除
     * @param ids 日志id
     */
    void deleteLog(List<Long> ids);

    /**
     * 清空操作日志
     */
    void emptyLog();
}
