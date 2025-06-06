package com.unionclass.profileservice.domain.grade.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetGradeNameResVo {

    private Long gradeId;
    private String gradeName;

    @Builder
    public GetGradeNameResVo(Long gradeId, String gradeName) {
        this.gradeId = gradeId;
        this.gradeName = gradeName;
    }
}
