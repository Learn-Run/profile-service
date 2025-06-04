package com.unionclass.profileservice.domain.profile.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetAuthorInfoVo {

    private String memberUuid;
    private String nickname;
    private String profileImageUrl;
    private String alt;

    @Builder
    public GetAuthorInfoVo(String memberUuid, String nickname, String profileImageUrl, String alt) {
        this.memberUuid = memberUuid;
        this.nickname = nickname;
        this.profileImageUrl = profileImageUrl;
        this.alt = alt;
    }
}
