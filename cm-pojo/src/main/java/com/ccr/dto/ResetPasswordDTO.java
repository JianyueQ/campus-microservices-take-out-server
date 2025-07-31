package com.ccr.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 31373
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordDTO implements Serializable {

    /**
     * 旧密码
     */
    private String oldPassword;
    /**
     * 新密码
     */
    private String newPassword;
    /**
     * 重复新密码
     */
    private String rePassword;

}
