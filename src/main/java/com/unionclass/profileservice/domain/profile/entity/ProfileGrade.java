package com.unionclass.profileservice.domain.profile.entity;

import com.unionclass.profileservice.domain.grade.entity.Grade;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProfileGrade {

    private Long id;
    private String name;
    private String color;

    @Builder
    public ProfileGrade(Long id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    public static ProfileGrade from(Grade grade) {
        return new ProfileGrade(grade.getId(), grade.getName(), grade.getColor());
    }
}
