package com.unionclass.profileservice.profile.dto.in;

import com.unionclass.profileservice.profile.vo.in.ChangeNicknameReqVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChangeNicknameReqDto {

    private String memberUuid;
    private String nickname;

    @Builder
    public ChangeNicknameReqDto(String memberUuid, String nickname) {
        this.memberUuid = memberUuid;
        this.nickname = nickname;
    }

    public static ChangeNicknameReqDto of(String memberUuid, ChangeNicknameReqVo changeNicknameReqVo) {
        return ChangeNicknameReqDto.builder()
                .memberUuid(memberUuid)
                .nickname(changeNicknameReqVo.getNickname())
                .build();
    }
}