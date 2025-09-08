package com.ccr.controller.admin;

import com.ccr.result.Result;
import com.ccr.service.DataManagementService;
import com.ccr.vo.GradeStatisticsVO;
import com.ccr.vo.StatisticsScoreStudentsVO;
import com.ccr.vo.StatisticsStudentsVO;
import com.ccr.vo.StatisticsVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 31373
 */
@Slf4j
@RestController("adminDataManagementController")
@RequestMapping("/admin/data")
public class DataManagement {

    @Autowired
    private DataManagementService dataManagementService;

    /**
     * 查询考勤人数的统计数据
     *
     * @param courseId 课程id
     * @return 统计数据
     */
    @GetMapping("/statistics")
    public Result<StatisticsVO> statistics(Long courseId) {
        log.info("查询统计数据:{}", courseId);
        return Result.success(dataManagementService.adminStatistics(courseId));
    }

    /**
     * 获取迟到学生列表（迟到次数超过3次
     */
    @GetMapping("/lateStudents")
    public Result<List<StatisticsStudentsVO>> lateStudents(Long courseId) {
        log.info("获取迟到学生列表:{}", courseId);
        return Result.success(dataManagementService.adminLateStudents(courseId));
    }

    /**
     * 获取未签到学生列表（未签到次数超过3次）
     */
    @GetMapping("/absentStudents")
    public Result<List<StatisticsStudentsVO>> absentStudents(Long courseId) {
        log.info("获取未签到学生列表:{}", courseId);
        return Result.success(dataManagementService.adminAbsentStudents(courseId));
    }

    /**
     * 获取请假学生列表（请假次数超过3次）
     */
    @GetMapping("/leaveStudents")
    public Result<List<StatisticsStudentsVO>> leaveStudents(Long courseId) {
        log.info("获取请假学生列表:{}", courseId);
        return Result.success(dataManagementService.adminLeaveStudents(courseId));
    }


    /**
     * 获取成绩统计信息
     */
    @GetMapping("/gradeStatistics")
    public Result<GradeStatisticsVO> gradeStatistics(Long courseId) {
        log.info("获取成绩统计信息:{}", courseId);
        return Result.success(dataManagementService.adminGradeStatistics(courseId));
    }

    /**
     * 获取成绩排行榜（前10名）
     */
    @GetMapping("/topStudents")
    public Result<List<StatisticsScoreStudentsVO>> topStudents(Long courseId) {
        log.info("获取成绩排行榜:{}", courseId);
        return Result.success(dataManagementService.adminTopStudents(courseId));
    }

    /**
     * 获取需要关注的学生（成绩较低）
     */
    @GetMapping("/atRiskStudents")
    public Result<List<StatisticsScoreStudentsVO>> attentionStudents(Long courseId) {
        log.info("获取需要关注的学生:{}", courseId);
        return Result.success(dataManagementService.adminAttentionStudents(courseId));
    }
}
