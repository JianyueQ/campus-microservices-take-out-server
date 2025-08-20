package com.ccr.controller.teacher;

import com.ccr.constant.SuccessConstant;
import com.ccr.context.BaseContext;
import com.ccr.dto.StudentLoginDTO;
import com.ccr.dto.TeacherLoginDTO;
import com.ccr.result.Result;
import com.ccr.service.StudentLoginService;
import com.ccr.service.TeacherLoginService;
import com.ccr.vo.StudentLoginVO;
import com.ccr.vo.TeacherLoginVO;
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
@RequestMapping("/teacher")
public class TeacherLoginController {

    @Autowired
    private TeacherLoginService teacherLoginService;

    /**
     * 教师登录
     */
    @PostMapping("/login")
    public Result<TeacherLoginVO> login(@Valid @RequestBody TeacherLoginDTO teacherLoginDTO) {
        log.info("教师登录数据:{}", teacherLoginDTO);
        return Result.success(teacherLoginService.login(teacherLoginDTO));
    }

    /**
     * 教师登出
     */
    @PostMapping("/logout")
    public Result<String> logout() {
        Long currentId = BaseContext.getCurrentId();
        log.info("教师:{}登出", currentId);
        teacherLoginService.logout(currentId);
        return Result.success(SuccessConstant.LOGOUT_SUCCESS);
    }
}
