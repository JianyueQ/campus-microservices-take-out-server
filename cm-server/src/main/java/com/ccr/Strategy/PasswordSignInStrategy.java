package com.ccr.Strategy;

import com.ccr.WebSocketServer.StudentWebSocketServer;
import com.ccr.constant.ParametersQuestionConstant;
import com.ccr.constant.RedisConstant;
import com.ccr.constant.StatusConstant;
import com.ccr.context.BaseContext;
import com.ccr.dto.AttendanceSignInDTO;
import com.ccr.dto.StartAttendanceDTO;
import com.ccr.entity.AttendanceInitiate;
import com.ccr.entity.AttendanceRecord;
import com.ccr.exception.ParametersQuestionException;
import com.ccr.mapper.AttendanceManagementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 31373
 */
@Service
public class PasswordSignInStrategy implements SignInStrategy {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private AttendanceManagementMapper attendanceManagementMapper;

    /**
     * 密码签到
     * @param attendanceSignInDTO 签到信息
     */
    @Override
    public void executeSignIn(AttendanceSignInDTO attendanceSignInDTO) {
        Long currentId = BaseContext.getCurrentId();
        // 检查学生是否已经签到，防止重复签到
        AttendanceRecord existingRecord = null;
        if (attendanceSignInDTO.getSignInStatus() == null || attendanceSignInDTO.getSignInStatus() == 3) {
            // 根据学生id,考勤id,课程id获取当前签到状态
            existingRecord = attendanceManagementMapper.getAttendanceRecord(attendanceSignInDTO, currentId);
        }
        // 如果已经签到（状态不为"未签到"），则拒绝重复签到
        if (existingRecord != null && !existingRecord.getStatus().equals(StatusConstant.ATTENDANCE_STATUS_NOT_SIGN_IN)) {
            throw new ParametersQuestionException(ParametersQuestionConstant.ATTENDANCE_ALREADY_SIGN_IN);
        }
        String password = redisTemplate.opsForValue().get(RedisConstant.ATTENDANCE_VERIFICATION_KEY + attendanceSignInDTO.getCourseId());
        if (password == null) {
            //签到已经过期
            throw new ParametersQuestionException(ParametersQuestionConstant.ATTENDANCE_EXPIRED);
        }
        if (!password.equals(attendanceSignInDTO.getSignInPassword())) {
            //密码错误
            throw new ParametersQuestionException(ParametersQuestionConstant.ATTENDANCE_PASSWORD_ERROR);
        }

        AttendanceRecord attendanceRecord = new AttendanceRecord();
        attendanceRecord.setAttendanceInitiateId(attendanceSignInDTO.getAttendanceInitiateId());
        attendanceRecord.setCourseId(attendanceSignInDTO.getCourseId());
        attendanceRecord.setSignInPassword(attendanceSignInDTO.getSignInPassword());
        attendanceRecord.setStatus(StatusConstant.ATTENDANCE_STATUS_SIGN_IN);
        attendanceRecord.setUserId(currentId);
        attendanceRecord.setSignInTime(LocalDateTime.now());
        attendanceManagementMapper.updateAttendanceRecordWithStatus(attendanceRecord);
        AttendanceInitiate attendanceInitiateById = attendanceManagementMapper.getAttendanceInitiateById(attendanceSignInDTO.getAttendanceInitiateId());
        //通知教师端
        Map<String, Object> data = new HashMap<>();
        data.put("type", "attendance_sign_in");
        StudentWebSocketServer.sendAttendanceNotification(attendanceInitiateById.getTeacherId(), data);
    }
}
