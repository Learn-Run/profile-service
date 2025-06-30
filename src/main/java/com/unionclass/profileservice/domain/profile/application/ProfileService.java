package com.unionclass.profileservice.domain.profile.application;

import com.unionclass.profileservice.common.kafka.event.MemberCreatedEvent;
import com.unionclass.profileservice.domain.profile.dto.in.*;
import com.unionclass.profileservice.domain.profile.dto.out.GetAuthorInfoDto;
import com.unionclass.profileservice.domain.profile.dto.out.GetProfileInfoResDto;

public interface ProfileService {

    void registerNickname(RegisterNicknameReqDto registerNicknameReqDto);

    void initializeProfile(MemberCreatedEvent event);

    void checkNicknameDuplicate(GetNicknameReqDto getNicknameReqDto);

    void changeNickname(ChangeNicknameReqDto changeNicknameReqDto);

    GetAuthorInfoDto getAuthorInfo(String memberUuid);

    void createProfile(CreateProfileReqDto createProfileReqDto);

    void updateProfileInfo(UpdateProfileInfoReqDto updateProfileInfoReqDto);

    void updateProfileImage(UpdateProfileImageReqDto updateProfileImageReqDto);

    GetProfileInfoResDto getProfileInfo(String memberUuid);
}
