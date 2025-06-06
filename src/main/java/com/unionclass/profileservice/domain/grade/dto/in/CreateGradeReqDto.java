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
    private String gradeRequirement;
    private boolean defaultStatus;
    private BigDecimal commissionDiscountRate;

    @Builder
    public CreateGradeReqDto(
            String gradeName, String gradeRequirement, boolean defaultStatus, BigDecimal commissionDiscountRate
    ) {
        this.gradeName = gradeName;
        this.gradeRequirement = gradeRequirement;
        this.defaultStatus = defaultStatus;
        this.commissionDiscountRate = commissionDiscountRate;
    }

    public static CreateGradeReqDto from(CreateGradeReqVo createGradeReqVo) {
        return CreateGradeReqDto.builder()
                .gradeName(createGradeReqVo.getGradeName())
                .gradeRequirement(createGradeReqVo.getGradeRequirement())
                .defaultStatus(createGradeReqVo.isDefaultStatus())
                .commissionDiscountRate(createGradeReqVo.getCommissionDiscountRate())
                .build();
    }

    public Grade toEntity() {
        return Grade.builder()
                .name(gradeName)
                .requirement(gradeRequirement)
                .defaultStatus(defaultStatus)
                .commissionDiscountRate(commissionDiscountRate)
                .build();
    }
}
