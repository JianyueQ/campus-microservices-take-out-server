package com.ccr.service;

import com.ccr.dto.ScheduleQueryDTO;
import com.ccr.result.PageResult;
import com.ccr.vo.ScheduleQueryVO;

/**
 * @author 31373
 */

public interface ScheduleQueryService {
    /**
     * 课程查询
     * @param scheduleQueryDTO 查询参数
     * @return 分页结果
     */
    PageResult listPage(ScheduleQueryDTO scheduleQueryDTO);

    /**
     * 课程详情
     * @param id 课程id
     * @return 课程详情
     */
    ScheduleQueryVO detail(Long id);
}
