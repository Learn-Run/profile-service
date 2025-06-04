package com.unionclass.profileservice.domain.profile.entity;

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
    private String nickname;

    private String selfIntroduction;
    private String imageUrl;
    private String alt;

    private Long categoryListId;

    @Builder
    public Profile(String id, String memberUuid, String nickname, String selfIntroduction,
                   String imageUrl, String alt, Long categoryListId
    ) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.nickname = nickname;
        this.selfIntroduction = selfIntroduction;
        this.imageUrl = imageUrl;
        this.alt = alt;
        this.categoryListId = categoryListId;
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }
}
