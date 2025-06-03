package com.unionclass.profileservice.profile.dto.in;

import com.unionclass.profileservice.profile.entity.Profile;
import com.unionclass.profileservice.profile.vo.in.RegisterNicknameReqVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterNicknameReqDto {

    private String memberUuid;
    private String nickname;

    @Builder
    public RegisterNicknameReqDto(String memberUuid, String nickname) {
        this.memberUuid = memberUuid;
        this.nickname = nickname;
    }

    public static RegisterNicknameReqDto from(RegisterNicknameReqVo registerNicknameReqVo) {
        return RegisterNicknameReqDto.builder()
                .memberUuid(registerNicknameReqVo.getMemberUuid())
                .nickname(registerNicknameReqVo.getNickname())
                .build();
    }

    public Profile toEntity() {
        return Profile.builder()
                .memberUuid(memberUuid)
                .nickname(nickname)
                .build();
    }
}
