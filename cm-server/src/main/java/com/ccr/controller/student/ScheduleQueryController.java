package com.ccr.controller.student;

import com.ccr.dto.ScheduleQueryDTO;
import com.ccr.result.PageResult;
import com.ccr.result.Result;
import com.ccr.service.ScheduleQueryService;
import com.ccr.vo.ScheduleQueryVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 31373
 */
@Slf4j
@RestController
@RequestMapping("/student/schedule")
public class ScheduleQueryController {

    @Autowired
    private ScheduleQueryService scheduleQueryService;

    /**
     * 获取课表
     */
    @GetMapping("/list")
    public Result<PageResult> list(ScheduleQueryDTO scheduleQueryDTO) {
        log.info("查询课表:{}", scheduleQueryDTO);
        return Result.success(scheduleQueryService.listPage(scheduleQueryDTO));
    }

    /**
     * 查看详情
     */
    @GetMapping("/detail/{id}")
    public Result<ScheduleQueryVO> detail(@PathVariable Long id) {
        log.info("查看详情:{}", id);
        return Result.success(scheduleQueryService.detail(id));
    }
}
