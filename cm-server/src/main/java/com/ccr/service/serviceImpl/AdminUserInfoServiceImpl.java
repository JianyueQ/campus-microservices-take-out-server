package com.ccr.service.serviceImpl;

import cn.hutool.core.util.PhoneUtil;
import com.ccr.constant.MessageConstant;
import com.ccr.constant.ParametersQuestionConstant;
import com.ccr.constant.RedisConstant;
import com.ccr.context.BaseContext;
import com.ccr.dto.AdminUserInfoUpdateDTO;
import com.ccr.dto.ResetPasswordDTO;
import com.ccr.entity.User;
import com.ccr.exception.ParametersQuestionException;
import com.ccr.mapper.AdminUserInfoMapper;
import com.ccr.service.AdminUserInfoService;
import com.ccr.utils.ParametersUtil;
import com.ccr.vo.AdminUserInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Map;

/**
 * @author 31373
 */
@Slf4j
@Service
public class AdminUserInfoServiceImpl implements AdminUserInfoService {

    @Autowired
    private AdminUserInfoMapper adminUserInfoMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 获取用户信息
     * @return 用户信息
     */
    @Override
    public AdminUserInfoVO getUserInfo() {
        Long currentId = BaseContext.getCurrentId();
        log.info("当前用户id为：{}", currentId);
        AdminUserInfoVO adminUserInfoVO = adminUserInfoMapper.getUserInfoById(currentId);
        Map<Object, Object> map = redisTemplate.opsForHash().entries(RedisConstant.JWT_ID_KEY + currentId);
        adminUserInfoVO.setSuperAdmin(map.get(RedisConstant.HASH_KEY_SUPER_ADMIN).toString());
        return adminUserInfoVO;
    }

    /**
     * 修改用户信息
     * @param adminUserInfoUpdateDTO 修改用户信息
     */
    @Override
    public void updateUserInfo(AdminUserInfoUpdateDTO adminUserInfoUpdateDTO) {
        Long currentId = BaseContext.getCurrentId();
        checkParam(adminUserInfoUpdateDTO);
        User user = new User();
        BeanUtils.copyProperties(adminUserInfoUpdateDTO, user);
        user.setId(currentId);
        adminUserInfoMapper.updateUserInfo(user);
    }

    /**
     * 修改密码
     * @param resetPasswordDTO 修改密码
     */
    @Override
    public void updatePassword(ResetPasswordDTO resetPasswordDTO) {
        Long currentId = BaseContext.getCurrentId();
        checkUserPassword(resetPasswordDTO);
        //查询旧密码
        User userInfo = adminUserInfoMapper.selectById(currentId);
        //验证密码,使用MD5加密比对
        String encodedPassword = DigestUtils.md5DigestAsHex(resetPasswordDTO.getOldPassword().getBytes());
        if (!userInfo.getPassword().equals(encodedPassword)) {
            throw new ParametersQuestionException(ParametersQuestionConstant.PASSWORD_ERROR);
        }
        //更新密码
        userInfo.setPassword(DigestUtils.md5DigestAsHex(resetPasswordDTO.getNewPassword().getBytes()));
        adminUserInfoMapper.updateUserInfo(userInfo);

    }

    /**
     * 密码校验
     */
    private void checkUserPassword(ResetPasswordDTO resetPasswordDTO) {
        if (resetPasswordDTO == null){
            throw new ParametersQuestionException(ParametersQuestionConstant.PARAMETERS_NOT_NULL);
        }
        if (resetPasswordDTO.getOldPassword() == null) {
            //旧密码为空
            throw new ParametersQuestionException(ParametersQuestionConstant.OLD_PASSWORD_NOT_NULL);
        }
        if (resetPasswordDTO.getNewPassword() == null){
            //新密码为空
            throw new ParametersQuestionException(ParametersQuestionConstant.NEW_PASSWORD_NOT_NULL);
        }
        if (resetPasswordDTO.getRePassword() == null){
            //确认密码为空
            throw new ParametersQuestionException(ParametersQuestionConstant.RE_PASSWORD_NOT_NULL);
        }
        if (!resetPasswordDTO.getNewPassword().equals(resetPasswordDTO.getRePassword())){
            //新密码与确认密码不一致
            throw new ParametersQuestionException(ParametersQuestionConstant.NEW_PASSWORD_SAME_AS_RE_PASSWORD);
        }
        if (resetPasswordDTO.getOldPassword().equals(resetPasswordDTO.getNewPassword())){
            //新密码与旧密码一致
            throw new ParametersQuestionException(ParametersQuestionConstant.NEW_PASSWORD_SAME_AS_OLD_PASSWORD);
        }
    }


    /**
     * 非空校验,和参数格式校验
     */
    private void checkParam(AdminUserInfoUpdateDTO adminUserInfoUpdateDTO) {
        if (adminUserInfoUpdateDTO.getAvatar() != null){
            return;
        }
        if (adminUserInfoUpdateDTO.getEmail() == null){
            // 邮箱不能为空
            throw new ParametersQuestionException(ParametersQuestionConstant.EMAIL_NOT_NULL);
        }
        //邮箱格式正则校验
        if (!ParametersUtil.isEmail(adminUserInfoUpdateDTO.getEmail())){
            //邮箱格式不正确
            throw new ParametersQuestionException(ParametersQuestionConstant.EMAIL_ERROR);
        }
        if (adminUserInfoUpdateDTO.getPhone()== null){
            // 手机号不能为空
            throw new ParametersQuestionException(ParametersQuestionConstant.PHONE_NOT_NULL);
        }
        if (!PhoneUtil.isPhone(adminUserInfoUpdateDTO.getPhone())){
            //手机号格式不正确
            throw new ParametersQuestionException(ParametersQuestionConstant.PHONE_ERROR);
        }
        if (adminUserInfoUpdateDTO.getGender() == null){
            //性别不能为空
            throw new ParametersQuestionException(ParametersQuestionConstant.GENDER_NOT_NULL);
        }
        if (adminUserInfoUpdateDTO.getRealName()== null){
            //真实姓名不能为空
            throw new ParametersQuestionException(ParametersQuestionConstant.REAL_NAME_NOT_NULL);
        }
        //真实姓名不能包含数字或者字母以及其他标点符号
        if (!ParametersUtil.isRealName(adminUserInfoUpdateDTO.getRealName())){
            throw new ParametersQuestionException(ParametersQuestionConstant.REAL_NAME_NOT_RIGHT);
        }
    }
}
