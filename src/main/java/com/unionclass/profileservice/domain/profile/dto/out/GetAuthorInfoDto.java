package com.unionclass.profileservice.domain.profile.dto.out;

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
    private String profileImageUrl;
    private String alt;

    @Builder
    public GetAuthorInfoDto(String memberUuid, String nickname, String profileImageUrl, String alt) {
        this.memberUuid = memberUuid;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.alt = alt;
    }

    public static GetAuthorInfoDto from(Profile profile) {
        return GetAuthorInfoDto.builder()
                .memberUuid(profile.getMemberUuid())
                .nickname(profile.getNickname())
                .profileImageUrl(profile.getImage().getImageUrl())
                .alt(profile.getImage().getAlt())
                .build();
    }

    public GetAuthorInfoVo toVo() {
        return GetAuthorInfoVo.builder()
                .memberUuid(memberUuid)
                .nickname(nickname)
                .profileImageUrl(profileImageUrl)
                .alt(alt)
                .build();
    }
}
