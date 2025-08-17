package com.ccr.service;

import com.ccr.dto.ResetPasswordDTO;
import com.ccr.dto.StudentUserInfoUpdateDTO;
import com.ccr.vo.StudentUserInfoVO;

/**
 * @author 31373
 */

public interface StudentUserInfoService {
    /**
     * 获取用户信息
     * @return 用户信息
     */
    StudentUserInfoVO getUserInfo();

    /**
     * 修改用户信息
     * @param studentUserInfoUpdateDTO 修改用户信息
     */
    void updateUserInfo(StudentUserInfoUpdateDTO studentUserInfoUpdateDTO);

    /**
     * 修改密码
     * @param resetPasswordDTO 修改密码
     */
    void updatePassword(ResetPasswordDTO resetPasswordDTO);

}
