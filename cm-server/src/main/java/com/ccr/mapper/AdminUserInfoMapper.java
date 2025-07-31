package com.ccr.mapper;

import com.ccr.entity.User;
import com.ccr.vo.AdminUserInfoVO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 31373
 */
@Mapper
public interface AdminUserInfoMapper {
    /**
     * 获取用户信息
     * @param currentId 当前用户id
     * @return 用户信息
     */
    AdminUserInfoVO getUserInfoById(Long currentId);

    /**
     * 修改用户信息
     * @param user 修改用户信息
     */
    void updateUserInfo(User user);

    /**
     * 根据id查询用户
     * @param currentId 当前用户id
     * @return  用户信息
     */
    User selectById(Long currentId);
}
