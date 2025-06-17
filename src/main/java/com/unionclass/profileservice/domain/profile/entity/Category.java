package com.unionclass.profileservice.domain.profile.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Category {

    private Long mainCategoryId;
    private Long subCategoryId;

    @Builder
    public Category(Long mainCategoryId, Long subCategoryId) {
        this.mainCategoryId = mainCategoryId;
        this.subCategoryId = subCategoryId;
    }
}
