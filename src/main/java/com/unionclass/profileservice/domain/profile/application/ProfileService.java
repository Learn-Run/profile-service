package com.unionclass.profileservice.domain.profile.application;

import com.unionclass.profileservice.domain.profile.dto.in.*;
import com.unionclass.profileservice.domain.profile.dto.out.GetAuthorInfoDto;
import com.unionclass.profileservice.domain.profile.vo.out.GetAuthorInfoVo;

public interface ProfileService {

    void registerNickname(RegisterNicknameReqDto registerNicknameReqDto);
    void checkNicknameDuplicate(GetNicknameReqDto getNicknameReqDto);
    void changeNickname(ChangeNicknameReqDto changeNicknameReqDto);
    GetAuthorInfoDto getAuthorInfo(String memberUuid);
    void createProfile(CreateProfileReqDto createProfileReqDto);
    void updateProfile(UpdateProfileReqDto updateProfileReqDto);
}
