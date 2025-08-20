package com.ccr.service.serviceImpl;

import com.ccr.constant.ParametersQuestionConstant;
import com.ccr.context.BaseContext;
import com.ccr.dto.ResetPasswordDTO;
import com.ccr.dto.TeacherUserInfoUpdateDTO;
import com.ccr.entity.User;
import com.ccr.exception.ParametersQuestionException;
import com.ccr.mapper.TeacherUserInfoMapper;
import com.ccr.service.TeacherUserInfoService;
import com.ccr.vo.TeacherUserInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @author 31373
 */
@Service
@Slf4j
public class TeacherUserInfoServiceImpl implements TeacherUserInfoService {

    @Autowired
    private TeacherUserInfoMapper teacherUserInfoMapper;

    /**
     * 修改密码
     * @param resetPasswordDTO  修改密码
     */
    @Override
    public void updatePassword(ResetPasswordDTO resetPasswordDTO) {
        Long currentId = BaseContext.getCurrentId();
        checkUserPassword(resetPasswordDTO);
        //查询旧密码
        User userInfo = teacherUserInfoMapper.selectById(currentId);
        //验证密码,使用MD5加密比对
        String encodedPassword = DigestUtils.md5DigestAsHex(resetPasswordDTO.getOldPassword().getBytes());
        if (!userInfo.getPassword().equals(encodedPassword)) {
            throw new ParametersQuestionException(ParametersQuestionConstant.PASSWORD_ERROR);
        }
        //更新密码
        userInfo.setPassword(DigestUtils.md5DigestAsHex(resetPasswordDTO.getNewPassword().getBytes()));
        teacherUserInfoMapper.updateUserInfo(userInfo);
    }

    /**
     * 获取用户信息
     * @return 用户信息
     */
    @Override
    public TeacherUserInfoVO getUserInfo() {
        Long currentId = BaseContext.getCurrentId();
        log.info("当前用户id为：{}", currentId);
        return teacherUserInfoMapper.getUserInfoById(currentId);
    }

    /**
     * 修改用户信息
     * @param teacherUserInfoUpdateDTO 修改用户信息
     */
    @Override
    public void updateUserInfo(TeacherUserInfoUpdateDTO teacherUserInfoUpdateDTO) {
        Long currentId = BaseContext.getCurrentId();
        User user = new User();
        BeanUtils.copyProperties(teacherUserInfoUpdateDTO, user);
        user.setId(currentId);
        teacherUserInfoMapper.updateUserInfo(user);
    }

    /**
     * 验证用户密码
     * @param resetPasswordDTO 修改密码
     */
    private void checkUserPassword(ResetPasswordDTO resetPasswordDTO) {
        if (!resetPasswordDTO.getNewPassword().equals(resetPasswordDTO.getRePassword())){
            //新密码与确认密码不一致
            throw new ParametersQuestionException(ParametersQuestionConstant.NEW_PASSWORD_SAME_AS_RE_PASSWORD);
        }
        if (resetPasswordDTO.getOldPassword().equals(resetPasswordDTO.getNewPassword())){
            //新密码与旧密码一致
            throw new ParametersQuestionException(ParametersQuestionConstant.NEW_PASSWORD_SAME_AS_OLD_PASSWORD);
        }
    }
}
