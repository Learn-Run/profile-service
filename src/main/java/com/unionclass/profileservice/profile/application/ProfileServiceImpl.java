package com.unionclass.profileservice.profile.application;

import com.unionclass.profileservice.common.exception.BaseException;
import com.unionclass.profileservice.common.exception.ErrorCode;
import com.unionclass.profileservice.profile.dto.in.ChangeNicknameReqDto;
import com.unionclass.profileservice.profile.dto.in.GetNicknameReqDto;
import com.unionclass.profileservice.profile.dto.in.RegisterNicknameReqDto;
import com.unionclass.profileservice.profile.entity.Profile;
import com.unionclass.profileservice.profile.infrastructure.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    /**
     * /api/v1/profile
     *
     * 1. (회원가입 시) 닉네임 등록
     */

    /**
     * 1. (회원가입 시) 닉네임 등록
     *
     * @param registerNicknameReqDto
     */
    @Transactional
    @Override
    public void registerNickname(RegisterNicknameReqDto registerNicknameReqDto) {
        try {
            profileRepository.save(registerNicknameReqDto.toEntity());
            log.info("닉네임 등록 성공 : memberUuid = {}, nickname = {}",
                    registerNicknameReqDto.getMemberUuid(),
                    registerNicknameReqDto.getNickname());
        } catch (Exception e) {
            log.error("닉네임 등록 실패 : memberUuid = {}, nickname = {}",
                    registerNicknameReqDto.getMemberUuid(),
                    registerNicknameReqDto.getNickname());
            throw new BaseException(ErrorCode.FAILED_TO_REGISTER_NICKNAME);
        }
    }

    /**
     * 5. 닉네임 중복 검사
     *
     * @param getNicknameReqDto
     */
    @Override
    public void checkNicknameDuplicate(GetNicknameReqDto getNicknameReqDto) {
        if (profileRepository.findByNickname(getNicknameReqDto.getNickname()).isPresent()) {
            log.warn("닉네임 중복됨 - 입력 닉네임: {}", getNicknameReqDto.getNickname());
            throw new BaseException(ErrorCode.NICKNAME_ALREADY_EXISTS);
        }
        log.info("닉네임 중복 없음 - 입력 닉네임: {}", getNicknameReqDto.getNickname());
    }

    /**
     * 3. 닉네임 변경
     *
     * @param changeNicknameReqDto
     */
    @Transactional
    @Override
    public void changeNickname(ChangeNicknameReqDto changeNicknameReqDto) {
        Profile profile = profileRepository.findByMemberUuid(changeNicknameReqDto.getMemberUuid())
                .orElseThrow(() -> new BaseException(ErrorCode.NO_EXIST_MEMBER));

        profile.changeNickname(changeNicknameReqDto.getNickname());
        profileRepository.save(profile);
        log.info("닉네임 변경 완료 - Member UUID: {}, 새로운 닉네임: {}",
                changeNicknameReqDto.getMemberUuid(),
                profile.getNickname());
    }
}