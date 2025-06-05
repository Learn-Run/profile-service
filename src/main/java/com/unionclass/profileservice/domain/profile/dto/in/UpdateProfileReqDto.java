package com.unionclass.profileservice.domain.profile.dto.in;

import com.unionclass.profileservice.domain.profile.entity.Profile;
import com.unionclass.profileservice.domain.profile.vo.in.UpdateProfileReqVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UpdateProfileReqDto {

    private String memberUuid;
    private String nickname;
    private String selfIntroduction;
    private String imageUrl;
    private String alt;
    private List<Long> categoryListIds;

    @Builder
    public UpdateProfileReqDto(
            String memberUuid, String nickname, String selfIntroduction,
            String imageUrl, String alt, List<Long> categoryListIds
    ) {
        this.memberUuid = memberUuid;
        this.nickname = nickname;
        this.selfIntroduction = selfIntroduction;
        this.imageUrl = imageUrl;
        this.alt = alt;
        this.categoryListIds = categoryListIds;
    }

    public static UpdateProfileReqDto of(String memberUuid, UpdateProfileReqVo updateProfileReqVo) {
        return UpdateProfileReqDto.builder()
                .memberUuid(memberUuid)
                .nickname(updateProfileReqVo.getNickname())
                .selfIntroduction(updateProfileReqVo.getSelfIntroduction())
                .imageUrl(updateProfileReqVo.getImageUrl())
                .alt(updateProfileReqVo.getAlt())
                .categoryListIds(updateProfileReqVo.getCategoryListIds())
                .build();
    }

    public Profile toEntity(Profile profile) {
        return Profile.builder()
                .id(profile.getId())
                .memberUuid(memberUuid)
                .nickname(nickname == null ? profile.getNickname() : nickname)
                .selfIntroduction(selfIntroduction == null ? profile.getSelfIntroduction() : selfIntroduction)
//                .image()
//                .imageUrl(imageUrl == null ? profile.getImageUrl() : imageUrl)
//                .alt(alt == null ? profile.getAlt() : alt)
                .categoryListIds(categoryListIds == null ? profile.getCategoryListIds() : categoryListIds)
                .build();
    }
}
