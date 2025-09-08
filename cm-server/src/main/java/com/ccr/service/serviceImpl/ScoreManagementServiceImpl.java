package com.ccr.service.serviceImpl;

import com.ccr.constant.ParametersQuestionConstant;
import com.ccr.context.BaseContext;
import com.ccr.dto.GradeAddDTO;
import com.ccr.entity.CourseSelection;
import com.ccr.entity.Grade;
import com.ccr.exception.ParametersQuestionException;
import com.ccr.mapper.ScoreManagementMapper;
import com.ccr.service.ScoreManagementService;
import com.ccr.vo.CourseStudentTreeVO;
import com.ccr.vo.GradeVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 31373
 */
@Service
public class ScoreManagementServiceImpl implements ScoreManagementService {

    @Autowired
    private ScoreManagementMapper scoreManagementMapper;

    /**
     * 课程学生树
     * @return 课程学生树
     */
    @Override
    public List<CourseStudentTreeVO> courseStudentTree() {
        Long currentId = BaseContext.getCurrentId();
        return scoreManagementMapper.courseStudentTree(currentId);
    }

    @Override
    public GradeVO getGradeByCourseAndStudent(Long courseId, Long studentId) {
        return scoreManagementMapper.getGradeByCourseAndStudent(courseId, studentId);
    }

    @Override
    public void addGrade(GradeAddDTO gradeAddDTO) {
        GradeVO gradeVO = scoreManagementMapper.getGradeByCourseAndStudent(gradeAddDTO.getCourseId(), gradeAddDTO.getStudentId());
        if (gradeVO != null){
            //"该学生已存在该课程的记录"
            throw new ParametersQuestionException(ParametersQuestionConstant.STUDENT_EXIST_COURSE_RECORD);
        }

        //根据课程id和学生id获取选课表信息
        CourseSelection courseSelection = scoreManagementMapper.getCourseSelectionByCourseAndStudent(gradeAddDTO.getCourseId(), gradeAddDTO.getStudentId());
        Grade grade = new Grade();
        BeanUtils.copyProperties(gradeAddDTO, grade);
        grade.setCourseTeachingId(courseSelection.getCourseTeachingId());
        grade.setTeacherId(courseSelection.getTeacherId());
        grade.setTeacherName(courseSelection.getTeacherName());
        scoreManagementMapper.addGrade(grade);
    }

    @Override
    public GradeVO getGradeByCourseAndStudentByCourseId(Long courseId) {
        Long studentId = scoreManagementMapper.getStudentIdByCourseId(BaseContext.getCurrentId());
        return scoreManagementMapper.getGradeByCourseAndStudent(courseId, studentId);
    }

    @Override
    public List<CourseStudentTreeVO> adminCourseStudentTree() {
        return scoreManagementMapper.adminCourseStudentTree();
    }

    @Override
    public void updateGrade(GradeAddDTO gradeAddDTO) {
        Grade grade = new Grade();
        BeanUtils.copyProperties(gradeAddDTO, grade);
        scoreManagementMapper.updateGrade(grade);
    }


}
