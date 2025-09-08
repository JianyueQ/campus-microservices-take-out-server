package com.ccr.controller.student;

import com.ccr.dto.GradeAddDTO;
import com.ccr.result.Result;
import com.ccr.service.ScoreManagementService;
import com.ccr.vo.CourseStudentTreeVO;
import com.ccr.vo.GradeVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 31373
 */
@Slf4j
@RestController("studentScoreManagementController")
@RequestMapping("/student/score")
public class ScoreManagementController {

    @Autowired
    private ScoreManagementService scoreManagementService;

    /**
     * 根据课程ID和学生ID获取成绩
     */
    @GetMapping("/getGrade/{courseId}")
    public Result<GradeVO> getGradeByCourseAndStudent(@PathVariable Long courseId) {
        log.info("根据课程ID获取成绩, courseId={}", courseId);
        return Result.success(scoreManagementService.getGradeByCourseAndStudentByCourseId(courseId));
    }

}
