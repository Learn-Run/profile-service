package com.unionclass.profileservice.domain.grade.dto.in;

import com.unionclass.profileservice.domain.grade.entity.Grade;
import com.unionclass.profileservice.domain.grade.vo.in.CreateGradeReqVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class CreateGradeReqDto {

    private String gradeName;
    private Integer gradeLevel;
    private String gradeColor;
    private String gradeRequirement;
    private boolean defaultStatus;
    private BigDecimal commissionDiscountRate;

    @Builder
    public CreateGradeReqDto(
            String gradeName, Integer gradeLevel, String gradeColor,
            String gradeRequirement, boolean defaultStatus, BigDecimal commissionDiscountRate) {
        this.gradeName = gradeName;
        this.gradeLevel = gradeLevel;
        this.gradeColor = gradeColor;
        this.gradeRequirement = gradeRequirement;
        this.defaultStatus = defaultStatus;
        this.commissionDiscountRate = commissionDiscountRate;
    }

    public static CreateGradeReqDto from(CreateGradeReqVo createGradeReqVo) {
        return CreateGradeReqDto.builder()
                .gradeName(createGradeReqVo.getGradeName())
                .gradeLevel(createGradeReqVo.getGradeLevel())
                .gradeColor(createGradeReqVo.getGradeColor())
                .gradeRequirement(createGradeReqVo.getGradeRequirement())
                .defaultStatus(createGradeReqVo.isDefaultStatus())
                .commissionDiscountRate(createGradeReqVo.getCommissionDiscountRate())
                .build();
    }

    public Grade toEntity() {
        return Grade.builder()
                .name(gradeName)
                .level(gradeLevel)
                .color(gradeColor)
                .requirement(gradeRequirement)
                .defaultStatus(defaultStatus)
                .commissionDiscountRate(commissionDiscountRate)
                .build();
    }
}
