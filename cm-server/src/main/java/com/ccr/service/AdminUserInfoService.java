package com.ccr.service;

import com.ccr.dto.AdminUserInfoUpdateDTO;
import com.ccr.dto.ResetPasswordDTO;
import com.ccr.vo.AdminUserInfoVO;

/**
 * @author 31373
 */
public interface AdminUserInfoService {
    /**
     * 获取用户信息
     * @return 用户信息
     */
    AdminUserInfoVO getUserInfo();

    /**
     * 修改用户信息
     * @param adminUserInfoUpdateDTO 修改用户信息
     */
    void updateUserInfo(AdminUserInfoUpdateDTO adminUserInfoUpdateDTO);

    /**
     * 修改密码
     * @param resetPasswordDTO 修改密码
     */
    void updatePassword(ResetPasswordDTO resetPasswordDTO);
}
