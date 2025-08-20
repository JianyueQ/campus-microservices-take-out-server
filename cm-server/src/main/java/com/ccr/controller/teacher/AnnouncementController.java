package com.ccr.controller.teacher;

import com.ccr.annotations.Log;
import com.ccr.dto.AnnouncementDTO;
import com.ccr.dto.AnnouncementPageDTO;
import com.ccr.enumeration.BusinessType;
import com.ccr.result.PageResult;
import com.ccr.result.Result;
import com.ccr.service.AnnouncementService;
import com.ccr.vo.AnnouncementDetailsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 31373
 */
@Slf4j
@RestController("teacherAnnouncementController")
@RequestMapping("/teacher/announcement")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    /**
     * 查询公告详情
     */
    @GetMapping("/{id}")
    public Result<AnnouncementDetailsVO> get(@PathVariable Long id) {
        log.info("查询公告详情:{}", id);
        AnnouncementDetailsVO announcementDetailsVO = announcementService.getAnnouncementDetails(id);
        return Result.success(announcementDetailsVO);
    }

    /**
     * 获取公告列表
     */
    @GetMapping("/list")
    public Result<PageResult> list(AnnouncementPageDTO announcementPageDTO) {
        log.info("查询公告列表");
        PageResult pageResult = announcementService.list(announcementPageDTO);
        return Result.success(pageResult);
    }

    /**
     * 获取公告浏览数量
     */
    @GetMapping("/browseCount/{id}")
    public Result<Integer> browseCount(@PathVariable Long id) {
        log.info("获取公告浏览数量");
        return Result.success(announcementService.browseCount(id));
    }
}
