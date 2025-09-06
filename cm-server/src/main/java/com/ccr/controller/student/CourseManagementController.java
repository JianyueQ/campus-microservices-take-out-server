package com.ccr.controller.student;

import com.ccr.annotations.Log;
import com.ccr.dto.CancelCourseSelectionDTO;
import com.ccr.dto.CoursePageDTO;
import com.ccr.dto.StudentSelectCourseDTO;
import com.ccr.enumeration.BusinessType;
import com.ccr.result.PageResult;
import com.ccr.result.Result;
import com.ccr.service.CourseService;
import com.ccr.vo.CourseListVO;
import com.ccr.vo.CourseSelectionVO;
import com.ccr.vo.CoursesNumberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 31373
 */
@Slf4j
@RestController("studentCourseManagementController")
@RequestMapping("/student/course")
public class CourseManagementController {

    @Autowired
    private CourseService courseService;

    /**
     * 课程分页查询
     *
     * @param coursePageDTO 分页查询参数
     * @return 分页结果
     */
    @GetMapping("/page")
    public Result<PageResult> page(CoursePageDTO coursePageDTO) {
        log.info("课程分页查询: {}", coursePageDTO);
        return Result.success(courseService.pageQueryByStudent(coursePageDTO));
    }

    /**
     * 获取课程的当前人数和最大人数
     */
    @GetMapping("/getCurrentAndMaxStudentCount")
    public Result<List<CoursesNumberVO>> getCurrentAndMaxStudentCount(@RequestParam(value = "courseIds") List<Long> courseIds) {
        log.info("获取课程的当前人数和最大人数: {}", courseIds);
        return Result.success(courseService.getCurrentAndMaxStudentCount(courseIds));
    }

    /**
     * 学生选课
     */
    @Log(title = "学生端-学生选课", businessType = BusinessType.INSERT)
    @PostMapping("/select")
    public Result<String> select(@RequestBody StudentSelectCourseDTO studentSelectCourseDTO) {
        log.info("学生选课: {}", studentSelectCourseDTO);
        courseService.select(studentSelectCourseDTO);
        return Result.success();
    }

    /**
     * 查看已经选的课程
     */
    @GetMapping("/listSelectedCourse")
    public Result<List<CourseSelectionVO>> listSelectedCourse() {
        log.info("查看已经选的课程" );
        return Result.success(courseService.listSelectedCourse());
    }

    /**
     * 取消课程(单体取消或全部取消)
     */
    @Log(title = "学生端-取消课程", businessType = BusinessType.DELETE)
    @DeleteMapping("/deselect")
    public Result<String> cancel(@RequestBody CancelCourseSelectionDTO cancelCourseSelectionDTO) {
        log.info("取消课程: {}", cancelCourseSelectionDTO);
        courseService.deleteCourseSelection(cancelCourseSelectionDTO);
        return Result.success();
    }

    /**
     * 获取课程树形列表
     */
    @GetMapping("/courseList")
    public Result<List<CourseListVO>> listCourse() {
        log.info("获取课程列表");
        return Result.success(courseService.listCourseByStudent());
    }

}
