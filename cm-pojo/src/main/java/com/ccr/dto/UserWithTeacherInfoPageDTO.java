package com.ccr.dto;

import com.ccr.entity.Page;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 31373
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithTeacherInfoPageDTO extends Page implements Serializable {

    /**
     * 用户名
     */
    private String username;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * 工号
     */
    private String teacherNo;

    /**
     * 状态（0:禁用, 1:启用）
     */
    private Integer status;

}
