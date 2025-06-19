package com.unionclass.profileservice.domain.grade.application;

import com.unionclass.profileservice.domain.grade.dto.in.CreateGradeReqDto;
import com.unionclass.profileservice.domain.grade.dto.in.UpdateGradeInfoReqDto;
import com.unionclass.profileservice.domain.grade.dto.out.GetAllGradeResDto;
import com.unionclass.profileservice.domain.grade.entity.Grade;

import java.util.List;

public interface GradeService {

    void createGrade(CreateGradeReqDto createGradeReqDto);
    List<GetAllGradeResDto> getAllGrades();
    Grade getDefaultGradeInfo();
    void updateGradeInfo(UpdateGradeInfoReqDto updateGradeInfoReqDto);
}
