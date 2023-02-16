package com.wrs.cems.service;

import com.wrs.cems.bean.Assessment;

import java.util.List;

public interface IAssessmentService {
    Assessment getAssessmentById(Integer id);

    List<Assessment> getAll();

    int deleteAssessmentById(Integer id);

    int addAssessment(Assessment assessment);

    int updateAssessment(Assessment assessment);

    List<Assessment> query(String name);
}
