package com.ccr.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{8,16}$", message = "密码格式不正确")
    @NotNull(message = "旧密码不能为空")
    private String oldPassword;
    /**
     * 新密码
     */
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{8,16}$", message = "密码格式不正确")
    @NotNull(message = "新密码不能为空")
    private String newPassword;
    /**
     * 重复新密码
     */
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{8,16}$", message = "密码格式不正确")
    @NotNull(message = "确认新密码不能为空")
    private String rePassword;

}
