package com.unionclass.profileservice.domain.profile.entity;

import com.unionclass.profileservice.common.entity.BaseDocument;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "profile")
@Getter
@NoArgsConstructor
public class Profile extends BaseDocument {

    @Id
    private String id;

    private String memberUuid;
    private String nickname;

    private Image image;
    private String selfIntroduction;

    private List<Long> categoryListIds;

    private Grade grade;

    @Builder
    public Profile(
            String id, String memberUuid, String nickname, Image image,
            String selfIntroduction, List<Long> categoryListIds, Grade grade
    ) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.nickname = nickname;
        this.image = image;
        this.selfIntroduction = selfIntroduction;
        this.categoryListIds = categoryListIds;
        this.grade = grade;
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }
}
