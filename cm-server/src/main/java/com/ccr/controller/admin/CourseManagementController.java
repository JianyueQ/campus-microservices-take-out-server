package com.ccr.controller.admin;

import com.ccr.annotations.Log;
import com.ccr.dto.*;
import com.ccr.enumeration.BusinessType;
import com.ccr.result.PageResult;
import com.ccr.result.Result;
import com.ccr.service.CourseService;
import com.ccr.vo.CollegeMajorTreeVO;
import com.ccr.vo.CourseDetailVO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 31373
 */
@Slf4j
@RestController("adminCourseManagementController")
@RequestMapping("/admin/course")
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
        return Result.success(courseService.pageQuery(coursePageDTO));
    }

    /**
     * 获取教师账号信息-分页查询
     */
    @GetMapping("/listTeacherAccount")
    public Result<PageResult> listTeacherAccount(UserWithTeacherInfoPageDTO userWithTeacherInfoPageDTO) {
        log.info("获取教师账号信息-分页查询:{}", userWithTeacherInfoPageDTO);
        return Result.success(courseService.listTeacherAccount(userWithTeacherInfoPageDTO));
    }

    /**
     * 添加课程
     */
    @Log(title = "管理端-课程管理-添加课程", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public Result<String> addCourse(@Valid @RequestBody CourseDTO courseDTO){
        log.info("添加课程:{}",courseDTO);
        courseService.addCourse(courseDTO);
        return Result.success();
    }

    /**
     * 为课程分配教师
     */
    @Log(title = "管理端-课程管理-课程分配教师", businessType = BusinessType.UPDATE)
    @PostMapping("/assignTeacher")
    public Result<String> assignTeacher(@Valid @RequestBody CourseAssignTeacherDTO courseAssignTeacherDTO){
        log.info("为课程分配教师:{}",courseAssignTeacherDTO);
        courseService.assignTeacher(courseAssignTeacherDTO);
        return Result.success();
    }

    /**
     * 获取学院/专业的树形列表
     */
    @GetMapping("/collegeMajorTree")
    public Result<List<CollegeMajorTreeVO>> collegeMajorTree(){
        log.info("获取学院/专业树形列表");
        return Result.success(courseService.collegeMajorTree());
    }

    /**
     * 获取班级列表-分页查询
     */
    @GetMapping("/classList")
    public Result<PageResult> listClasses(ClassListPageDTO classListPageDTO){
        log.info("获取班级列表-分页查询:{}", classListPageDTO);
        return Result.success(courseService.listClasses(classListPageDTO));
    }

    /**
     * 添加授课班级
     */
    @Log(title = "管理端-课程管理-添加授课班级", businessType = BusinessType.UPDATE)
    @PostMapping("/assignClass")
    public Result<String> addClass(@Valid @RequestBody AssignClassToCourseDTO assignClassToCourse){
        log.info("添加授课班级:{}", assignClassToCourse);
        courseService.addClass(assignClassToCourse);
        return Result.success();
    }

    /**
     * 获取课程详情
     */
    @GetMapping("/detail/{id}")
    public Result<CourseDetailVO> detail(@PathVariable String id){
        log.info("获取课程详情:{}", id);
        return Result.success(courseService.detail(id));
    }

    /**
     * 修改课程
     */
    @Log(title = "管理端-课程管理-修改课程", businessType = BusinessType.UPDATE)
    @PutMapping("/update")
    public Result<String> updateCourse(@Valid @RequestBody CourseDTO courseDTO){
        log.info("修改课程:{}", courseDTO);
        courseService.updateCourse(courseDTO);
        return Result.success();
    }

    /**
     * 启用/禁用课程
     */
    @Log(title = "管理端-课程管理-启用/禁用课程", businessType = BusinessType.UPDATE)
    @PutMapping("/updateStatus")
    public Result<String> changeStatus(@Valid @RequestBody CourseChangeStatusDTO courseChangeStatusDTO){
        log.info("启用/禁用课程:{}", courseChangeStatusDTO);
        courseService.changeStatus(courseChangeStatusDTO);
        return Result.success();
    }

    /**
     * 删除课程
     */
    @Log(title = "管理端-课程管理-删除课程", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete")
    public Result<String> deleteCourse(@RequestParam List<Long> ids){
        log.info("删除课程:{}", ids);
        courseService.deleteCourse(ids);
        return Result.success();
    }
}
