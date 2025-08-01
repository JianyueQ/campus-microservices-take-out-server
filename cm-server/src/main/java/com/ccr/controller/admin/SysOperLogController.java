package com.ccr.controller.admin;

import com.ccr.annotations.Log;
import com.ccr.dto.LogPageDTO;
import com.ccr.enumeration.BusinessType;
import com.ccr.result.PageResult;
import com.ccr.result.Result;
import com.ccr.service.SysOperLogService;
import com.ccr.vo.OperationLogDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 31373
 */
@Slf4j
@RestController
@RequestMapping("/admin/operLog")
public class SysOperLogController {

    @Autowired
    private SysOperLogService sysOperLogService;

    /**
     * 获取操作日志-分页查询
     */
    @GetMapping("/logPage")
    public Result<PageResult> logPage(LogPageDTO logPageDTO){
        log.info("获取操作日志-分页查询:{}", logPageDTO);
        return Result.success(sysOperLogService.logPage(logPageDTO));
    }

    /**
     * 获取操作日志-详情
     */
    @GetMapping("/{id}")
    public Result<OperationLogDetailVO> logDetail(@PathVariable Long id){
        log.info("获取操作日志-详情:{}", id);
        return Result.success(sysOperLogService.logDetail(id));
    }

    /**
     * 删除操作日志
     */
    @Log(title = "操作日志-删除", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete")
    public Result<String> deleteLog(@RequestParam List<Long> ids){
        log.info("删除操作日志:{}", ids);
        sysOperLogService.deleteLog(ids);
        return Result.success();
    }

    /**
     * 清空操作日志
     */
    @Log(title = "操作日志-清空", businessType = BusinessType.DELETE)
    @DeleteMapping("/empty")
    public Result<String> emptyLog(){
        log.info("清空操作日志");
        sysOperLogService.emptyLog();
        return Result.success();
    }
}
