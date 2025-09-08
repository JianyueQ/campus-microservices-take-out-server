package com.ccr.mapper;

import com.ccr.annotations.AutoFile;
import com.ccr.entity.CourseSelection;
import com.ccr.entity.Grade;
import com.ccr.enumeration.OperationType;
import com.ccr.vo.CourseStudentTreeVO;
import com.ccr.vo.GradeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 31373
 */
@Mapper
public interface ScoreManagementMapper {
    /**
     * 获取课程学生树
     *
     * @param currentId 当前用户id
     * @return 课程学生树
     */
    List<CourseStudentTreeVO> courseStudentTree(Long currentId);


    GradeVO getGradeByCourseAndStudent(Long courseId, Long studentId);

    @AutoFile(OperationType.INSERT)
    void addGrade(Grade grade);

    CourseSelection getCourseSelectionByCourseAndStudent(Long courseId, Long studentId);

    List<CourseStudentTreeVO> adminCourseStudentTree();

    Long getStudentIdByCourseId(Long currentId);

    @AutoFile(OperationType.UPDATE)
    void updateGrade(Grade grade);
}
