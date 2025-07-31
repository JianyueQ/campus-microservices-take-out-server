package com.ccr.mapper;

import com.ccr.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 31373
 */
@Mapper
public interface AdminLoginMapper {
    /**
     * 获取用户信息
     * @param user 用户信息
     * @return 用户信息
     */
    User getAdminByUser(User user);
}
