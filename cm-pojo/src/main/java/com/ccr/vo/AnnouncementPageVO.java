package com.ccr.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 31373
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnnouncementPageVO implements Serializable {

    /**
     * 公告ID
     */
    private Long id;

    /**
     * 公告标题
     */
    private String title;

    /**
     * 公告类型（1:系统公告, 2:课程公告, 3:学校通知）
     */
    private Integer announcementType;

    /**
     * 优先级（1:一般, 2:重要, 3:紧急）
     */
    private Integer priority;

    /**
     * 发布人姓名（冗余）
     */
    private String publisherName;

    /**
     * 发布时间
     */
    private LocalDateTime publishTime;

    /**
     * 阅读次数
     */
    private Integer readCount;

    /**
     * 状态（0:草稿, 1:已发布, 2:已撤回）
     */
    private Integer status;
}
