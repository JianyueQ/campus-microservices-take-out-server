package com.ccr.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author 31373
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO implements Serializable {

    /**
     * 课程ID
     */
    private Long id;

    /**
     * 课程编码
     */
    @NotNull(message = "课程编码不能为空")
    private String courseCode;

    /**
     * 课程名称
     */
    @NotNull(message = "课程名称不能为空")
    private String name;

    /**
     * 课程描述
     */
    private String description;

    /**
     * 理论学时
     */

    private Integer theoryHours;

    /**
     * 实践学时
     */
    private Integer practiceHours;

    /**
     * 总学时
     */
    private Integer totalHours;

    /**
     * 课程类型（1:必修, 2:选修, 3:公选）
     */
    @NotNull(message = "课程类型不能为空")
    private Integer courseType;

    /**
     * 考核方式（1:考试, 2:考查）
     */
    @NotNull(message = "考核方式不能为空")
    private Integer assessmentType;

    /**
     * 状态（0:禁用, 1:启用）
     */
    private Integer status;

    /**
     * 课程开始选择时间
     */
    private LocalDateTime selectionStartTime;

    /**
     * 课程结束选择时间
     */
    private LocalDateTime selectionEndTime;

    /**
     * 学期（如2023-2024 1）
     */
    @NotNull(message = "学期不能为空")
    private String semester;

    /**
     * 最大学生人数
     */
    private Integer maxStudentCount;

    /**
     * 上课时间地点信息
     */
    private String scheduleInfo;

}
