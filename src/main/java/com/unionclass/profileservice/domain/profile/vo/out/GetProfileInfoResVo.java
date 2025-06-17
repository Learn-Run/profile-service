package com.unionclass.profileservice.domain.profile.vo.out;

import com.unionclass.profileservice.domain.profile.entity.Category;
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
    private List<Category> categoryList;

    @Builder
    public GetProfileInfoResVo(String gradeName, String nickname, Image profileImage, String selfIntroduction, List<Category> categoryList) {
        this.gradeName = gradeName;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.selfIntroduction = selfIntroduction;
        this.categoryList = categoryList;
    }
}
