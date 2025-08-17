package com.ccr.controller.student;

import com.ccr.annotations.Log;
import com.ccr.constant.SuccessConstant;
import com.ccr.context.BaseContext;
import com.ccr.dto.*;
import com.ccr.enumeration.BusinessType;
import com.ccr.result.PageResult;
import com.ccr.result.Result;
import com.ccr.service.AdminUserInfoService;
import com.ccr.service.StudentUserInfoService;
import com.ccr.vo.AdminUserInfoVO;
import com.ccr.vo.AdminWithUserInfoVO;
import com.ccr.vo.StudentUserInfoVO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 31373
 */
@Slf4j
@RestController
@RequestMapping("/student")
public class StudentUserInfoController {

    @Autowired
    private StudentUserInfoService studentUserInfoService;

    /**
     * 获取用户信息
     */
    @GetMapping("/userInfo")
    public Result<StudentUserInfoVO> getUserInfo() {
        return Result.success(studentUserInfoService.getUserInfo());
    }

    /**
     * 更新用户信息
     */
    @Log(title = "用户信息-基本资料", businessType = BusinessType.UPDATE)
    @PutMapping("/update")
    public Result<String> updateUserInfo(@Valid @RequestBody StudentUserInfoUpdateDTO studentUserInfoUpdateDTO) {
        log.info("更新用户信息:{}",studentUserInfoUpdateDTO);
        studentUserInfoService.updateUserInfo(studentUserInfoUpdateDTO);
        return Result.success(SuccessConstant.UPDATE_SUCCESS);
    }

    /**
     * 修改密码
     */
    @Log(title = "用户信息-修改密码", businessType = BusinessType.UPDATE)
    @PutMapping("/updatePwd")
    public Result<String> updatePassword(@Valid @RequestBody ResetPasswordDTO resetPasswordDTO) {
        log.info("用户:{}:修改密码:{}", BaseContext.getCurrentId(), resetPasswordDTO);
        studentUserInfoService.updatePassword(resetPasswordDTO);
        return Result.success(SuccessConstant.UPDATE_SUCCESS);
    }


}
