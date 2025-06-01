package com.unionclass.profileservice.profile.entity;

import com.unionclass.profileservice.common.entity.BaseDocument;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "profile")
@Getter
@NoArgsConstructor
public class Profile extends BaseDocument {

    @Id
    private String id;

    private String memberUuid;
    private String selfIntroduction;
    private String image;

    private Long categoryListId;
    private Long activeHistoryId;

    @Builder
    public Profile(
            String id, String memberUuid, String selfIntroduction, String image, Long categoryListId, Long activeHistoryId
    ) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.selfIntroduction = selfIntroduction;
        this.image = image;
        this.categoryListId = categoryListId;
        this.activeHistoryId = activeHistoryId;
    }
}
