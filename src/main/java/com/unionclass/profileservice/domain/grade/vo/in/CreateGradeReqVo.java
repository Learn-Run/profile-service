package com.unionclass.profileservice.domain.grade.vo.in;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CreateGradeReqVo {

    private String gradeName;
    private int gradeLevel;
    private String gradeColor;
    private String gradeRequirement;
    private boolean defaultStatus;
    private BigDecimal commissionDiscountRate;
}
