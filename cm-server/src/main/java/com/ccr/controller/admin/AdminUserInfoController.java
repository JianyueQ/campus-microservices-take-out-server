package com.ccr.controller.admin;

import com.ccr.constant.SuccessConstant;
import com.ccr.context.BaseContext;
import com.ccr.dto.AdminUserInfoUpdateDTO;
import com.ccr.dto.ResetPasswordDTO;
import com.ccr.result.Result;
import com.ccr.service.AdminUserInfoService;
import com.ccr.vo.AdminUserInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 31373
 */
@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminUserInfoController {

    @Autowired
    private AdminUserInfoService adminUserInfoService;

    /**
     * 获取用户信息
     */
    @GetMapping("/userInfo")
    public Result<AdminUserInfoVO> getUserInfo() {
        return Result.success(adminUserInfoService.getUserInfo());
    }

    /**
     * 更新用户信息
     */
    @PutMapping("/update")
    public Result<String> updateUserInfo(@RequestBody AdminUserInfoUpdateDTO adminUserInfoUpdateDTO) {
        log.info("更新用户信息:{}",adminUserInfoUpdateDTO);
        adminUserInfoService.updateUserInfo(adminUserInfoUpdateDTO);
        return Result.success(SuccessConstant.UPDATE_SUCCESS);
    }

    /**
     * 修改密码
     */
    @PutMapping("/updatePwd")
    public Result<String> updatePassword(@RequestBody ResetPasswordDTO resetPasswordDTO) {
        log.info("用户:{}:修改密码:{}", BaseContext.getCurrentId(), resetPasswordDTO);
        adminUserInfoService.updatePassword(resetPasswordDTO);
        return Result.success(SuccessConstant.UPDATE_SUCCESS);
    }
}
