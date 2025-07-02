package com.unionclass.profileservice.domain.profile.dto.in;

import com.unionclass.profileservice.domain.grade.entity.Grade;
import com.unionclass.profileservice.domain.profile.entity.Category;
import com.unionclass.profileservice.domain.profile.entity.Image;
import com.unionclass.profileservice.domain.profile.entity.Profile;
import com.unionclass.profileservice.domain.profile.entity.ProfileGrade;
import com.unionclass.profileservice.domain.profile.enums.ImageType;
import com.unionclass.profileservice.domain.profile.vo.in.CreateProfileReqVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CreateProfileReqDto {

    private String memberUuid;
    private String selfIntroduction;
    private ImageType imageType;
    private String profileImageUrl;
    private List<Category> categoryList;

    @Builder
    public CreateProfileReqDto(String memberUuid, String selfIntroduction, ImageType imageType, String profileImageUrl, List<Category> categoryList) {
        this.memberUuid = memberUuid;
        this.selfIntroduction = selfIntroduction;
        this.imageType = imageType;
        this.profileImageUrl = profileImageUrl;
        this.categoryList = categoryList;
    }

    public static CreateProfileReqDto of(String memberUuid, CreateProfileReqVo createProfileReqVo) {
        return CreateProfileReqDto.builder()
                .memberUuid(memberUuid)
                .selfIntroduction(createProfileReqVo.getSelfIntroduction())
                .imageType(createProfileReqVo.getImageType())
                .profileImageUrl(createProfileReqVo.getProfileImageUrl())
                .categoryList(createProfileReqVo.getCategoryList())
                .build();
    }

    public Profile toEntity(Profile profile, Image image) {
        return Profile.builder()
                .id(profile.getId())
                .memberUuid(memberUuid)
                .nickname(profile.getNickname())
                .image(image)
                .selfIntroduction(selfIntroduction)
                .categoryList(categoryList)
                .build();
    }
}
