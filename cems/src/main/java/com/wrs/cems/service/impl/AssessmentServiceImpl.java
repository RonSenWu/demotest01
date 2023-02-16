package com.wrs.cems.service.impl;

import com.wrs.cems.bean.Assessment;
import com.wrs.cems.mapper.AssessmentMapper;
import com.wrs.cems.service.IAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssessmentServiceImpl implements IAssessmentService {
    @Autowired
    private AssessmentMapper assessmentMapper;


    @Override
    public Assessment getAssessmentById(Integer id) {
        return assessmentMapper.getAssessmentById(id);
    }

    @Override
    public List<Assessment> getAll() {
        return assessmentMapper.getAll();
    }

    @Override
    public int deleteAssessmentById(Integer id) {
        return assessmentMapper.deleteAssessmentById(id);
    }

    @Override
    public int addAssessment(Assessment assessment) {
        return assessmentMapper.addAssessment(assessment);
    }

    @Override
    public int updateAssessment(Assessment assessment) {
        return assessmentMapper.updateAssessment(assessment);
    }

    @Override
    public List<Assessment> query(String name) {
        return assessmentMapper.query(name);
    }
}
