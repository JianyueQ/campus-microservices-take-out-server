package com.ccr.service;

import com.ccr.dto.GradeAddDTO;
import com.ccr.vo.CourseStudentTreeVO;
import com.ccr.vo.GradeVO;

import java.util.List;

/**
 * @author 31373
 */
public interface ScoreManagementService {

    /**
     * 获取课程学生树
     * @return 树形数据模型
     */
    List<CourseStudentTreeVO> courseStudentTree();

    GradeVO getGradeByCourseAndStudent(Long courseId, Long studentId);

    void addGrade(GradeAddDTO gradeAddDTO);

    GradeVO getGradeByCourseAndStudentByCourseId(Long courseId);

    List<CourseStudentTreeVO> adminCourseStudentTree();

    void updateGrade(GradeAddDTO gradeAddDTO);

}
