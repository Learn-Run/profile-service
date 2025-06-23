package com.unionclass.profileservice.domain.profile.vo.out;

import com.unionclass.profileservice.domain.profile.entity.Category;
import com.unionclass.profileservice.domain.profile.entity.Image;
import com.unionclass.profileservice.domain.profile.entity.ProfileGrade;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GetProfileInfoResVo {

    private String memberUuid;
    private ProfileGrade grade;
    private String nickname;
    private Image profileImage;
    private String selfIntroduction;
    private List<Category> categoryList;

    @Builder
    public GetProfileInfoResVo(
            String memberUuid, ProfileGrade grade, String nickname,
            Image profileImage, String selfIntroduction, List<Category> categoryList
    ) {
        this.memberUuid = memberUuid;
        this.grade = grade;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.selfIntroduction = selfIntroduction;
        this.categoryList = categoryList;
    }
}
