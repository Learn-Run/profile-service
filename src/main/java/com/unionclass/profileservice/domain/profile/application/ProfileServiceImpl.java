package com.unionclass.profileservice.domain.profile.application;

import com.unionclass.profileservice.common.exception.BaseException;
import com.unionclass.profileservice.common.exception.ErrorCode;
import com.unionclass.profileservice.domain.profile.dto.in.*;
import com.unionclass.profileservice.domain.profile.dto.out.GetAuthorInfoDto;
import com.unionclass.profileservice.domain.profile.entity.Grade;
import com.unionclass.profileservice.domain.profile.entity.Image;
import com.unionclass.profileservice.domain.profile.entity.Profile;
import com.unionclass.profileservice.domain.profile.infrastructure.ProfileRepository;
import com.unionclass.profileservice.domain.profile.util.ImageAltTextTemplateProvider;
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
    private final ImageAltTextTemplateProvider imageAltTextTemplateProvider;

    private final Long gradeId = 1L;
    private final String gradeName = "WHITE";

    /**
     * /api/v1/profile
     *
     * 1. (회원가입 시) 닉네임 등록
     * 2. 닉네임 중복 검사
     * 3. 닉네임 변경
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
     * 2. 닉네임 중복 검사
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

    /**
     * 4. 작성자 프로필 조회
     *
     * @param memberUuid
     * @return
     */
    @Override
    public GetAuthorInfoDto getAuthorInfo(String memberUuid) {
        return GetAuthorInfoDto.from(profileRepository.findByMemberUuid(memberUuid)
                .orElseThrow(() -> new BaseException(ErrorCode.NO_EXIST_MEMBER)));
    }

    /**
     * 5. 프로필 생성
     *
     * @param createProfileReqDto
     */
    @Transactional
    @Override
    public void createProfile(CreateProfileReqDto createProfileReqDto) {
        try {
            Profile profile = profileRepository.findByMemberUuid(createProfileReqDto.getMemberUuid()) 
                    .orElseThrow(() -> new BaseException(ErrorCode.NO_EXIST_MEMBER));

            profileRepository.save(createProfileReqDto.toEntity(
                    profile,
                    Image.builder()
                            .type(createProfileReqDto.getImageType())
                            .imageUrl(createProfileReqDto.getProfileImageUrl())
                            .alt(imageAltTextTemplateProvider.getProfileImageAltTextTemplate(profile.getNickname()))
                            .build(),
                    Grade.builder()
                            .id(gradeId)
                            .name(gradeName)
                            .build()));

            log.info("프로필 생성 완료 - Member UUID: {}", createProfileReqDto.getMemberUuid());
        } catch (Exception e) {
            log.warn("프로필 생성 실패 - Member UUID: {}", createProfileReqDto.getMemberUuid());
            throw new BaseException(ErrorCode.FAILED_TO_CREATE_PROFILE);
        }
    }

    /**
     * 6. 프로필 변경
     *
     * @param updateProfileReqDto
     */
    @Transactional
    @Override
    public void updateProfile(UpdateProfileReqDto updateProfileReqDto) {
        try {
            profileRepository.save(
                    updateProfileReqDto.toEntity(profileRepository.findByMemberUuid(updateProfileReqDto.getMemberUuid())
                            .orElseThrow(() -> new BaseException(ErrorCode.NO_EXIST_MEMBER))));
            log.info("프로필 변경 완료 - Member UUID: {}", updateProfileReqDto.getMemberUuid());
        } catch (Exception e) {
            log.warn("프로필 변경 실패 - Member UUID: {}", updateProfileReqDto.getMemberUuid());
            throw new BaseException(ErrorCode.FAILED_TO_UPDATE_PROFILE);
        }
    }
}