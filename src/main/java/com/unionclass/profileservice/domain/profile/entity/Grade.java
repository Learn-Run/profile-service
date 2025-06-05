package com.unionclass.profileservice.domain.profile.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Grade {

    private Long id;
    private String name;

    @Builder
    public Grade(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
