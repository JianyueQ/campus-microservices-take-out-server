package com.ccr.controller.teacher;

import com.ccr.dto.ListStudentPageDTO;
import com.ccr.result.PageResult;
import com.ccr.result.Result;
import com.ccr.service.StudentManagementService;
import com.ccr.vo.CourseListVO;
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
@RestController("teacherStudentManagementController")
@RequestMapping("/teacher/student")
public class StudentManagementController {

    @Autowired
    private StudentManagementService studentManagementService;

    /**
     * 获取学生列表-分页查询
     */
    @GetMapping("/list")
    public Result<PageResult> listStudent(ListStudentPageDTO listStudentPageDTO) {
        log.info("获取学生列表:{}", listStudentPageDTO);
        return Result.success(studentManagementService.listStudent(listStudentPageDTO));
    }
}
