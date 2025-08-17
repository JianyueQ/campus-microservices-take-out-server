package com.ccr.controller.student;

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
@RestController("studentAnnouncementController")
@RequestMapping("/student/announcement")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    /**
     * 新增公告信息
     */
    @Log(title = "公告信息-添加", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public Result<String> addAnnouncement(@RequestBody AnnouncementDTO announcementDTO) {
        log.info("新增公告数据:{}", announcementDTO);
        announcementService.addAnnouncement(announcementDTO);
        return Result.success();
    }

    /**
     * 修改公告信息
     */
    @Log(title = "公告信息-修改", businessType = BusinessType.UPDATE)
    @PutMapping("/update")
    public Result<String> updateAnnouncement(@RequestBody AnnouncementDTO announcementDTO) {
        log.info("修改公告数据:{}", announcementDTO);
        announcementService.updateAnnouncement(announcementDTO);
        return Result.success();
    }

    /**
     * 删除公告信息
     */
    @Log(title = "公告信息-删除", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete")
    public Result<String> deleteAnnouncement(@RequestParam List<Long> ids) {
        log.info("删除公告数据:{}", ids);
        announcementService.deleteAnnouncement(ids);
        return Result.success();
    }

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
