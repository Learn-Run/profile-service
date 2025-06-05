package com.unionclass.profileservice.domain.profile.dto.in;

import com.unionclass.profileservice.domain.profile.entity.Profile;
import com.unionclass.profileservice.domain.profile.vo.in.CreateProfileReqVo;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CreateProfileReqDto {

    private String memberUuid;
    private String selfIntroduction;
    private String imageUrl;
    private String alt;
    private List<Long> categoryListIds;

    @Builder
    public CreateProfileReqDto(String memberUuid, String selfIntroduction, String imageUrl, String alt, List<Long> categoryListIds) {
        this.memberUuid = memberUuid;
        this.selfIntroduction = selfIntroduction;
        this.imageUrl = imageUrl;
        this.alt = alt;
        this.categoryListIds = categoryListIds;
    }

    public static CreateProfileReqDto of(String memberUuid, CreateProfileReqVo createProfileReqVo) {
        return CreateProfileReqDto.builder()
                .memberUuid(memberUuid)
                .selfIntroduction(createProfileReqVo.getSelfIntroduction())
                .imageUrl(createProfileReqVo.getImageUrl())
                .alt(createProfileReqVo.getAlt())
                .categoryListIds(createProfileReqVo.getCategoryListIds())
                .build();
    }

    public Profile toEntity(Profile profile) {
        return Profile.builder()
                .id(profile.getId())
                .memberUuid(memberUuid)
                .nickname(profile.getNickname())
                .selfIntroduction(selfIntroduction)
                .alt(alt)
                .categoryListIds(categoryListIds)
                .build();
    }
}
