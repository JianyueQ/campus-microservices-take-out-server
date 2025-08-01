package com.ccr.manager.factory;


import com.ccr.entity.OperationLog;
import com.ccr.service.SysOperLogService;
import com.ccr.utils.SpringUtils;

import java.util.TimerTask;

/**
 * 异步工厂（产生任务用）
 * @author 31373
 */
public class AsyncFactory {

    /**
     * 操作日志记录
     * @param operationLog 日志
     * @return 任务
     */
    public static TimerTask recordOper(OperationLog operationLog) {
        return new TimerTask() {
            @Override
            public void run() {
                //添加操作日志
                SpringUtils.getBean(SysOperLogService.class).insertOperLog(operationLog);
            }
        };
    }
}
