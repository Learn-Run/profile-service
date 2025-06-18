package com.unionclass.profileservice.domain.grade.dto.out;

import com.unionclass.profileservice.domain.grade.entity.Grade;
import com.unionclass.profileservice.domain.grade.vo.out.GetAllGradeResVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetAllGradeResDto {

    private Long gradeId;
    private String gradeName;
    private String gradeColor;

    @Builder
    public GetAllGradeResDto(Long gradeId, String gradeName, String gradeColor) {
        this.gradeId = gradeId;
        this.gradeName = gradeName;
        this.gradeColor = gradeColor;
    }

    public static GetAllGradeResDto from(Grade grade) {
        return GetAllGradeResDto.builder()
                .gradeId(grade.getId())
                .gradeName(grade.getName())
                .gradeColor(grade.getColor())
                .build();
    }

    public GetAllGradeResVo toVo() {
        return GetAllGradeResVo.builder()
                .gradeId(gradeId)
                .gradeName(gradeName)
                .gradeColor(gradeColor)
                .build();
    }
}
