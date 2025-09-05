package com.ccr.service;

import com.ccr.dto.AttendanceListPageDTO;
import com.ccr.dto.AttendanceStudentListDTO;
import com.ccr.dto.StartAttendanceDTO;
import com.ccr.dto.UpdateAttendanceStatusDTO;
import com.ccr.entity.AttendanceRecord;
import com.ccr.result.PageResult;
import com.ccr.vo.AttendanceStudentListVO;

import java.util.List;
import java.util.Map;

/**
 * @author 31373
 */
public interface AttendanceManagementService {

    /**
     * 开始签到
     *
     * @param startAttendanceDTO 开始签到参数
     */
    void startAttendance(StartAttendanceDTO startAttendanceDTO);

    /**
     * 获取正在进行中的签到列表-分页查询
     * @param attendanceListPageDTO 查询参数
     * @return 签到列表
     */
    PageResult listInitiate(AttendanceListPageDTO attendanceListPageDTO);

    /**
     * 结束签到
     * @param id 签到id
     */
    void endAttendance(Long id);

    /**
     * 获取考勤的剩余时间
     *
     * @param id 考勤id
     * @return 剩余时间
     */
    Map<Object, Object> getRemainingTime(Long id);

    /**
     * 添加考勤记录
     * @param attendanceRecord 考勤记录信息
     */
    void addAttendanceRecord(AttendanceRecord attendanceRecord);

    /**
     * 获取已签到/未签到的学生信息
     * @param attendanceStudentListDTO 查询参数
     * @return 学生信息
     */
    List<AttendanceStudentListVO> getStudentList(AttendanceStudentListDTO attendanceStudentListDTO);

    /**
     * 获取学生进行中/已结束的签到列表-分页查询
     * @param attendanceListPageDTO 查询参数
     * @return 签到列表
     */
    PageResult studentListInitiate(AttendanceListPageDTO attendanceListPageDTO);

    /**
     * 修改学生考勤状态
     * @param updateAttendanceStatusDTO 修改参数
     */
    void updateStudentStatus(UpdateAttendanceStatusDTO updateAttendanceStatusDTO);
}
