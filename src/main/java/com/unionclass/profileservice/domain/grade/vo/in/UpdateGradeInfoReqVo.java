package com.unionclass.profileservice.domain.grade.vo.in;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class UpdateGradeInfoReqVo {

    private String gradeName;
    private Integer gradeLevel;
    private String gradeColor;
    private String gradeRequirement;
    private boolean defaultStatus;
    private BigDecimal commissionDiscountRate;
}
