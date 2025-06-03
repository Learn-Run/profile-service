package com.unionclass.profileservice.profile.application;

import com.unionclass.profileservice.profile.dto.in.ChangeNicknameReqDto;
import com.unionclass.profileservice.profile.dto.in.GetNicknameReqDto;
import com.unionclass.profileservice.profile.dto.in.RegisterNicknameReqDto;

public interface ProfileService {

    void registerNickname(RegisterNicknameReqDto registerNicknameReqDto);
    void checkNicknameDuplicate(GetNicknameReqDto getNicknameReqDto);
    void changeNickname(ChangeNicknameReqDto changeNicknameReqDto);
}
