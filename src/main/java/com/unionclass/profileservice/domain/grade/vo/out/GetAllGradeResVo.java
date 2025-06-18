package com.unionclass.profileservice.domain.grade.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetAllGradeResVo {

    private Long gradeId;
    private String gradeName;
    private String gradeColor;

    @Builder
    public GetAllGradeResVo(Long gradeId, String gradeName, String gradeColor) {
        this.gradeId = gradeId;
        this.gradeName = gradeName;
        this.gradeColor = gradeColor;
    }
}
