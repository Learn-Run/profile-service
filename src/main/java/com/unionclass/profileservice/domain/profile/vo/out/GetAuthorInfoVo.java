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
    private String gradeName;
    private Image profileImage;

    @Builder
    public GetAuthorInfoVo(String memberUuid, String nickname, String gradeName, Image profileImage) {
        this.memberUuid = memberUuid;
        this.nickname = nickname;
        this.gradeName = gradeName;
        this.profileImage = profileImage;
    }
}
