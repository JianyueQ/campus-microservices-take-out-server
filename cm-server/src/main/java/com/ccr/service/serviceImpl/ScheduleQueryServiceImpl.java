package com.ccr.service.serviceImpl;

import com.ccr.context.BaseContext;
import com.ccr.dto.ScheduleQueryDTO;
import com.ccr.mapper.ScheduleQueryMapper;
import com.ccr.result.PageResult;
import com.ccr.service.ScheduleQueryService;
import com.ccr.vo.ScheduleQueryPageVO;
import com.ccr.vo.ScheduleQueryVO;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 31373
 */
@Service
public class ScheduleQueryServiceImpl implements ScheduleQueryService {

    @Autowired
    private ScheduleQueryMapper scheduleQueryMapper;

    /**
     * 查询课表
     *
     * @param scheduleQueryDTO 查询参数
     * @return 查询结果
     */
    @Override
    public PageResult listPage(ScheduleQueryDTO scheduleQueryDTO) {
        scheduleQueryDTO.setUserId(BaseContext.getCurrentId());
        PageHelper.startPage(scheduleQueryDTO.getPageNum(), scheduleQueryDTO.getPageSize());
        Page<ScheduleQueryPageVO> page = scheduleQueryMapper.listPage(scheduleQueryDTO);
        return PageResult.builder()
                .total(page.getTotal())
                .records(page.getResult())
                .build();
    }

    @Override
    public ScheduleQueryVO detail(Long id) {
        Long currentId = BaseContext.getCurrentId();
        return scheduleQueryMapper.detail(id,currentId);
    }
}
