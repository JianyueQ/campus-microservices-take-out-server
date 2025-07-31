package com.ccr.service;

import com.ccr.dto.AdminLoginDTO;
import com.ccr.vo.AdminLoginVO;

/**
 * @author 31373
 */
public interface AdminLoginService {
    /**
     * 管理员登录
     * @param adminLoginDTO 管理员登录参数
     * @return 管理员登录返回的token
     */
    AdminLoginVO login(AdminLoginDTO adminLoginDTO);

    /**
     * 管理员登出
     * @param currentId 当前登录管理员id
     */
    void logout(Long currentId);
}
