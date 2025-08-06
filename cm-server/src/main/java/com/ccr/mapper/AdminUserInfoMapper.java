package com.ccr.mapper;

import com.ccr.annotations.AutoFile;
import com.ccr.dto.AdminUserInfoPageDTO;
import com.ccr.dto.AdminUserInfoUpdateStatusDTO;
import com.ccr.entity.Admin;
import com.ccr.entity.User;
import com.ccr.enumeration.OperationType;
import com.ccr.vo.AdminUserInfoVO;
import com.ccr.vo.AdminWithUserInfoPageVO;
import com.ccr.vo.AdminWithUserInfoVO;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    /**
     * 添加管理员用户
     * @param user 添加用户
     */
    @AutoFile(OperationType.INSERT)
    void insertUser(User user);

    /**
     * 添加管理员用户
     * @param admin 添加用户
     */
    @AutoFile(OperationType.INSERT)
    void insertAdmin(Admin admin);

    /**
     * 修改管理员用户
     * @param admin 修改用户
     */
    @AutoFile(OperationType.UPDATE)
    void updateAdmin(Admin admin);

    /**
     * 删除管理员用户
     * @param ids 删除用户id
     */
    void deleteUser(List<Long> ids);

    /**
     * 删除管理员用户
     * @param ids 删除用户id
     */
    void deleteAdmin(List<Long> ids);

    /**
     * 获取管理员用户列表
     * @param adminUserInfoPageDTO 查询参数
     * @return 管理员用户列表
     */
    Page<AdminWithUserInfoPageVO> list(AdminUserInfoPageDTO adminUserInfoPageDTO);

    /**
     * 根据id查询管理员用户详情
     * @param id 管理员用户id
     * @return 管理员用户详情
     */
    AdminWithUserInfoVO selectAdminWithUserInfoById(Long id);

    /**
     * 修改管理员用户状态
     * @param adminUserInfoUpdateStatusDTO 修改用户状态
     */
    void updateUserInfoStatus(AdminUserInfoUpdateStatusDTO adminUserInfoUpdateStatusDTO);
}
