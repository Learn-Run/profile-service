package com.unionclass.profileservice.profile.dto.in;

import com.unionclass.profileservice.profile.vo.in.GetNicknameReqVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetNicknameReqDto {

    private String nickname;

    @Builder
    public GetNicknameReqDto(String nickname) { this.nickname = nickname; }

    public static GetNicknameReqDto from(GetNicknameReqVo getNicknameReqVo) {
        return GetNicknameReqDto.builder()
                .nickname(getNicknameReqVo.getNickname())
                .build();
    }
}