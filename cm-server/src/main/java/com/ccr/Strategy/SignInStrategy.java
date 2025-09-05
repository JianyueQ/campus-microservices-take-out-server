package com.ccr.Strategy;

import com.ccr.dto.AttendanceSignInDTO;

/**
 * @author 31373
 */
public interface SignInStrategy {

    void executeSignIn(AttendanceSignInDTO attendanceSignInDTO);

}
