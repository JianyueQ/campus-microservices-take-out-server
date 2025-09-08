package com.ccr.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 31373
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GradeVO implements Serializable {

    /**
     * 成绩ID
     */
    private Long id;

    /**
     * 学生ID
     */
    private Long studentId;

    /**
     * 课程ID
     */
    private Long courseId;

    /**
     * 教师名称
     */
    private String teacherName;

    /**
     * 课程名称（冗余）
     */
    private String courseName;

    /**
     * 学号（冗余）
     */
    private String studentNo;

    /**
     * 学生姓名（冗余）
     */
    private String studentName;

    /**
     * 成绩分数
     */
    private BigDecimal score;

    /**
     * 成绩等级（A+、A、B+等）
     */
    private String gradeLevel;

    /**
     * 考核方式（1:考试, 2:考查）
     */
    private Integer assessmentType;

    /**
     * 学期
     */
    private String semester;

    /**
     * 状态（1:正常, 2:补考, 3:重修）
     */
    private Integer status;

}
