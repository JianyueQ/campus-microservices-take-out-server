package com.ccr.service;

import com.ccr.dto.TeacherLoginDTO;
import com.ccr.vo.TeacherLoginVO;
import jakarta.validation.Valid;

/**
 * @author 31373
 */
public interface TeacherLoginService {
    /**
     * 登录
     * @param teacherLoginDTO 登录数据
     * @return 登录结果
     */
    TeacherLoginVO login(@Valid TeacherLoginDTO teacherLoginDTO);

    /**
     * 登出
     * @param currentId 当前用户id
     */
    void logout(Long currentId);
}
