package com.unionclass.profileservice.domain.profile.vo.out;

import com.unionclass.profileservice.domain.grade.entity.Grade;
import com.unionclass.profileservice.domain.profile.entity.Image;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GetProfileInfoResVo {

    private String gradeName;
    private String nickname;
    private Image profileImage;
    private String selfIntroduction;
    private List<Long> categoryListIds;

    @Builder
    public GetProfileInfoResVo(String gradeName, String nickname, Image profileImage, String selfIntroduction, List<Long> categoryListIds) {
        this.gradeName = gradeName;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.selfIntroduction = selfIntroduction;
        this.categoryListIds = categoryListIds;
    }
}
