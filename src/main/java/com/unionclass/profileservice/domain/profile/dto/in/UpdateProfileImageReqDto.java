package com.unionclass.profileservice.domain.profile.dto.in;

import com.unionclass.profileservice.domain.profile.entity.Image;
import com.unionclass.profileservice.domain.profile.entity.Profile;
import com.unionclass.profileservice.domain.profile.enums.ImageType;
import com.unionclass.profileservice.domain.profile.vo.in.UpdateProfileImageReqVo;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateProfileImageReqDto {

    private String memberUuid;
    private ImageType imageType;
    private String imageUrl;
    private String alt;

    @Builder
    public UpdateProfileImageReqDto(String memberUuid, ImageType imageType, String imageUrl, String alt) {
        this.memberUuid = memberUuid;
        this.imageType = imageType;
        this.imageUrl = imageUrl;
        this.alt = alt;
    }

    public static UpdateProfileImageReqDto of(String memberUuid, UpdateProfileImageReqVo updateProfileImageReqVo) {
        return UpdateProfileImageReqDto.builder()
                .memberUuid(memberUuid)
                .imageType(updateProfileImageReqVo.getProfileImageType())
                .imageUrl(updateProfileImageReqVo.getProfileImageUrl())
                .alt(updateProfileImageReqVo.getAlt())
                .build();
    }

    public Profile toEntity(Profile profile) {
        Image image = Image.builder()
                .type(imageType == null ? profile.getImage().getType() : imageType)
                .imageUrl(imageUrl == null ? profile.getImage().getImageUrl() : imageUrl)
                .alt(alt == null ? profile.getImage().getAlt() : alt)
                .build();

        return Profile.builder()
                .id(profile.getId())
                .memberUuid(memberUuid)
                .nickname(profile.getNickname())
                .image(image)
                .selfIntroduction(profile.getSelfIntroduction())
                .categoryList(profile.getCategoryList())
                .grade(profile.getGrade())
                .build();
    }
}
