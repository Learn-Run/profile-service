package com.unionclass.profileservice.domain.profile.entity;

import com.unionclass.profileservice.common.entity.BaseDocument;
import com.unionclass.profileservice.domain.grade.entity.Grade;
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

    private List<Category> categoryList;

    private Grade grade;

    @Builder
    public Profile(
            String id, String memberUuid, String nickname, Image image,
            String selfIntroduction, List<Category> categoryList, Grade grade
    ) {
        this.id = id;
        this.memberUuid = memberUuid;
        this.nickname = nickname;
        this.image = image;
        this.selfIntroduction = selfIntroduction;
        this.categoryList = categoryList;
        this.grade = grade;
    }

    public void changeNickname(String nickname) {
        this.nickname = nickname;
    }
}
