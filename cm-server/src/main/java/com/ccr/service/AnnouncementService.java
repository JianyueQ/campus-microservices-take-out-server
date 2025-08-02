package com.ccr.service;

import com.ccr.dto.AnnouncementDTO;
import com.ccr.dto.AnnouncementPageDTO;
import com.ccr.result.PageResult;
import com.ccr.vo.AnnouncementDetailsVO;

import java.util.List;

/**
 * @author 31373
 */
public interface AnnouncementService {
    /**
     * 添加公告
     * @param announcementDTO 添加的数据
     */
    void addAnnouncement(AnnouncementDTO announcementDTO);

    /**
     * 修改公告
     * @param announcementDTO 修改的数据
     */
    void updateAnnouncement(AnnouncementDTO announcementDTO);

    /**
     * 删除公告
     * @param ids 删除的id
     */
    void deleteAnnouncement(List<Long> ids);

    /**
     * 获取公告详情
     * @param id 详情的id
     * @return 详情
     */
    AnnouncementDetailsVO getAnnouncementDetails(Long id);

    /**
     * 获取公告列表
     * @param announcementPageDTO 查询条件
     * @return 列表
     */
    PageResult list(AnnouncementPageDTO announcementPageDTO);

    /**
     * 获取公告浏览量
     * @return 浏览量
     */
    Integer browseCount(Long id);

}
