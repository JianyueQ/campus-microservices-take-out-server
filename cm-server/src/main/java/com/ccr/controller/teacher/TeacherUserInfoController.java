package com.ccr.controller.teacher;

import com.ccr.annotations.Log;
import com.ccr.constant.SuccessConstant;
import com.ccr.context.BaseContext;
import com.ccr.dto.ResetPasswordDTO;
import com.ccr.dto.StudentUserInfoUpdateDTO;
import com.ccr.dto.TeacherUserInfoUpdateDTO;
import com.ccr.enumeration.BusinessType;
import com.ccr.result.Result;
import com.ccr.service.StudentUserInfoService;
import com.ccr.service.TeacherUserInfoService;
import com.ccr.vo.StudentUserInfoVO;
import com.ccr.vo.TeacherUserInfoVO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 31373
 */
@Slf4j
@RestController
@RequestMapping("/teacher")
public class TeacherUserInfoController {

    @Autowired
    private TeacherUserInfoService teacherUserInfoService;

    /**
     * 获取用户信息
     */
    @GetMapping("/userInfo")
    public Result<TeacherUserInfoVO> getUserInfo() {
        return Result.success(teacherUserInfoService.getUserInfo());
    }

    /**
     * 更新用户信息
     */
    @Log(title = "教师-用户信息-基本资料", businessType = BusinessType.UPDATE)
    @PutMapping("/update")
    public Result<String> updateUserInfo(@Valid @RequestBody TeacherUserInfoUpdateDTO teacherUserInfoUpdateDTO) {
        log.info("更新用户信息:{}",teacherUserInfoUpdateDTO);
        teacherUserInfoService.updateUserInfo(teacherUserInfoUpdateDTO);
        return Result.success(SuccessConstant.UPDATE_SUCCESS);
    }

    /**
     * 修改密码
     */
    @Log(title = "教师-用户信息-修改密码", businessType = BusinessType.UPDATE)
    @PutMapping("/updatePwd")
    public Result<String> updatePassword(@Valid @RequestBody ResetPasswordDTO resetPasswordDTO) {
        log.info("用户:{}:修改密码:{}", BaseContext.getCurrentId(), resetPasswordDTO);
        teacherUserInfoService.updatePassword(resetPasswordDTO);
        return Result.success(SuccessConstant.UPDATE_SUCCESS);
    }


}
