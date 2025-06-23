package com.unionclass.profileservice.domain.profile.dto.out;

import com.unionclass.profileservice.domain.profile.entity.Category;
import com.unionclass.profileservice.domain.profile.entity.Image;
import com.unionclass.profileservice.domain.profile.entity.Profile;
import com.unionclass.profileservice.domain.profile.entity.ProfileGrade;
import com.unionclass.profileservice.domain.profile.vo.out.GetProfileInfoResVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class GetProfileInfoResDto {

    private String memberUuid;
    private ProfileGrade grade;
    private String nickname;
    private Image profileImage;
    private String selfIntroduction;
    private List<Category> categoryListIds;

    @Builder
    public GetProfileInfoResDto(
            String memberUuid, ProfileGrade grade, String nickname,
            Image profileImage, String selfIntroduction, List<Category> categoryListIds
    ) {
        this.memberUuid = memberUuid;
        this.grade = grade;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.selfIntroduction = selfIntroduction;
        this.categoryListIds = categoryListIds;
    }

    public static GetProfileInfoResDto from(Profile profile) {
        return GetProfileInfoResDto.builder()
                .memberUuid(profile.getMemberUuid())
                .grade(profile.getGrade())
                .nickname(profile.getNickname())
                .profileImage(profile.getImage())
                .selfIntroduction(profile.getSelfIntroduction())
                .categoryListIds(profile.getCategoryList())
                .build();
    }

    public GetProfileInfoResVo toVo() {
        return GetProfileInfoResVo.builder()
                .memberUuid(memberUuid)
                .grade(grade)
                .nickname(nickname)
                .profileImage(profileImage)
                .selfIntroduction(selfIntroduction)
                .categoryList(categoryListIds)
                .build();
    }
}
