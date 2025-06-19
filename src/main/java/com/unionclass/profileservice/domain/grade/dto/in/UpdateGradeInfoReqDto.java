package com.unionclass.profileservice.domain.grade.dto.in;

import com.unionclass.profileservice.domain.grade.entity.Grade;
import com.unionclass.profileservice.domain.grade.vo.in.UpdateGradeInfoReqVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class UpdateGradeInfoReqDto {

    private Long gradeId;
    private String gradeName;
    private Integer gradeLevel;
    private String gradeColor;
    private String gradeRequirement;
    private boolean defaultStatus;
    private BigDecimal commissionDiscountRate;

    @Builder
    public UpdateGradeInfoReqDto(
            Long gradeId, String gradeName, Integer gradeLevel, String gradeColor,
            String gradeRequirement, boolean defaultStatus, BigDecimal commissionDiscountRate
    ) {
        this.gradeId = gradeId;
        this.gradeName = gradeName;
        this.gradeLevel = gradeLevel;
        this.gradeColor = gradeColor;
        this.gradeRequirement = gradeRequirement;
        this.defaultStatus = defaultStatus;
        this.commissionDiscountRate = commissionDiscountRate;
    }

    public static UpdateGradeInfoReqDto of(Long gradeId, UpdateGradeInfoReqVo updateGradeInfoReqVo) {
        return UpdateGradeInfoReqDto.builder()
                .gradeId(gradeId)
                .gradeName(updateGradeInfoReqVo.getGradeName())
                .gradeLevel(updateGradeInfoReqVo.getGradeLevel())
                .gradeColor(updateGradeInfoReqVo.getGradeColor())
                .gradeRequirement(updateGradeInfoReqVo.getGradeRequirement())
                .defaultStatus(updateGradeInfoReqVo.isDefaultStatus())
                .commissionDiscountRate(updateGradeInfoReqVo.getCommissionDiscountRate())
                .build();
    }

    public Grade toEntity(Grade grade) {
        return Grade.builder()
                .id(gradeId)
                .name(gradeName == null ? grade.getName() : gradeName)
                .level(gradeLevel == null ? grade.getLevel() : gradeLevel)
                .color(gradeColor == null ? grade.getColor() : gradeColor)
                .requirement(gradeRequirement == null ? grade.getRequirement() : gradeRequirement)
                .defaultStatus(grade.isDefaultStatus())
                .commissionDiscountRate(commissionDiscountRate == null ? grade.getCommissionDiscountRate() : commissionDiscountRate)
                .build();
    }
}
