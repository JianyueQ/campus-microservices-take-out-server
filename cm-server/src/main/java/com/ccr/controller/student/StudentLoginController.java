package com.ccr.controller.student;

import com.ccr.constant.SuccessConstant;
import com.ccr.context.BaseContext;
import com.ccr.dto.StudentLoginDTO;
import com.ccr.result.Result;
import com.ccr.service.StudentLoginService;
import com.ccr.vo.StudentLoginVO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 31373
 */
@Slf4j
@RestController
@RequestMapping("/student")
public class StudentLoginController {

    @Autowired
    private StudentLoginService studentLoginService;

    /**
     * 学生登录
     */
    @PostMapping("/login")
    public Result<StudentLoginVO> login(@Valid @RequestBody StudentLoginDTO studentLoginDTO) {
        log.info("管理员登录数据:{}", studentLoginDTO);
        return Result.success(studentLoginService.login(studentLoginDTO));
    }

    /**
     * 学生登出
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        Long currentId = BaseContext.getCurrentId();
        log.info("学生:{}登出", currentId);
        studentLoginService.logout(currentId);
        return Result.success(SuccessConstant.LOGOUT_SUCCESS);
    }
}
