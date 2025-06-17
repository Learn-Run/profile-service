package com.unionclass.profileservice.domain.profile.dto.out;

import com.unionclass.profileservice.domain.profile.entity.Image;
import com.unionclass.profileservice.domain.profile.entity.Profile;
import com.unionclass.profileservice.domain.profile.vo.out.GetAuthorInfoVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetAuthorInfoDto {

    private String memberUuid;
    private String nickname;
    private String gradeName;
    private Image profileImage;

    @Builder
    public GetAuthorInfoDto(String memberUuid, String nickname, String gradeName, Image profileImage) {
        this.memberUuid = memberUuid;
        this.nickname = nickname;
        this.gradeName = gradeName;
        this.profileImage = profileImage;
    }

    public static GetAuthorInfoDto from(Profile profile) {
        return GetAuthorInfoDto.builder()
                .memberUuid(profile.getMemberUuid())
                .nickname(profile.getNickname())
                .gradeName(profile.getGrade().getName())
                .profileImage(profile.getImage())
                .build();
    }

    public GetAuthorInfoVo toVo() {
        return GetAuthorInfoVo.builder()
                .memberUuid(memberUuid)
                .nickname(nickname)
                .gradeName(gradeName)
                .profileImage(profileImage)
                .build();
    }
}
