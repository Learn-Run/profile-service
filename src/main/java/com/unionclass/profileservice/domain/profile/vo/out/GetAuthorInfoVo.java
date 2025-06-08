package com.unionclass.profileservice.domain.profile.vo.out;

import com.unionclass.profileservice.domain.profile.entity.Image;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetAuthorInfoVo {

    private String memberUuid;
    private String nickname;
    private Image profileImage;

    @Builder
    public GetAuthorInfoVo(String memberUuid, String nickname, Image profileImage) {
        this.memberUuid = memberUuid;
        this.nickname = nickname;
        this.profileImage = profileImage;
    }
}
