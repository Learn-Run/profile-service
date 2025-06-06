package com.unionclass.profileservice.domain.grade.dto.out;

import com.unionclass.profileservice.domain.grade.entity.Grade;
import com.unionclass.profileservice.domain.grade.vo.out.GetGradeNameResVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetGradeNameResDto {

    private Long gradeId;
    private String gradeName;

    @Builder
    public GetGradeNameResDto(Long gradeId, String gradeName) {
        this.gradeId = gradeId;
        this.gradeName = gradeName;
    }

    public static GetGradeNameResDto from(Grade grade) {
        return GetGradeNameResDto.builder()
                .gradeId(grade.getId())
                .gradeName(grade.getName())
                .build();
    }

    public GetGradeNameResVo toVo() {
        return GetGradeNameResVo.builder()
                .gradeId(gradeId)
                .gradeName(gradeName)
                .build();
    }
}
