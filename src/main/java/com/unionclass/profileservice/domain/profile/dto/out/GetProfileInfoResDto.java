package com.unionclass.profileservice.domain.profile.dto.out;

import com.unionclass.profileservice.domain.profile.entity.Category;
import com.unionclass.profileservice.domain.profile.entity.Image;
import com.unionclass.profileservice.domain.profile.entity.Profile;
import com.unionclass.profileservice.domain.profile.vo.out.GetProfileInfoResVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GetProfileInfoResDto {

    private String gradeName;
    private String nickname;
    private Image profileImage;
    private String selfIntroduction;
    private List<Category> categoryListIds;

    @Builder
    public GetProfileInfoResDto(
            String gradeName, String nickname, Image profileImage, String selfIntroduction, List<Category> categoryListIds
    ) {
        this.gradeName = gradeName;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.selfIntroduction = selfIntroduction;
        this.categoryListIds = categoryListIds;
    }

    public static GetProfileInfoResDto from(Profile profile) {
        return GetProfileInfoResDto.builder()
                .gradeName(profile.getGrade().getName())
                .nickname(profile.getNickname())
                .profileImage(profile.getImage())
                .selfIntroduction(profile.getSelfIntroduction())
                .categoryListIds(profile.getCategoryList())
                .build();
    }

    public GetProfileInfoResVo toVo() {
        return GetProfileInfoResVo.builder()
                .gradeName(gradeName)
                .nickname(nickname)
                .profileImage(profileImage)
                .selfIntroduction(selfIntroduction)
                .categoryList(categoryListIds)
                .build();
    }
}
