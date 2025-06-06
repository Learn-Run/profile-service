package com.unionclass.profileservice.domain.profile.presentation;

import com.unionclass.profileservice.common.response.BaseResponseEntity;
import com.unionclass.profileservice.common.response.ResponseMessage;
import com.unionclass.profileservice.domain.profile.application.ProfileService;
import com.unionclass.profileservice.domain.profile.dto.in.*;
import com.unionclass.profileservice.domain.profile.vo.in.*;
import com.unionclass.profileservice.domain.profile.vo.out.GetAuthorInfoVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profile")
@Tag(name = "profile")
public class ProfileController {

    private final ProfileService profileService;

    /**
     * /api/v1/profile
     *
     * 1. (회원가입 시) 닉네임 등록
     * 2. 닉네임 중복 검사
     * 3. 닉네임 변경
     * 4. 작성자 프로필 조회
     * 5. 프로필 생성
     * 6. 프로필 정보 변경
     * 7. 프로필 이미지 변경
     */

    /**
     * 1. (회원가입 시) 닉네임 등록
     *
     * @param registerNicknameReqVo
     * @return
     */
    @Operation(
            summary = "닉네임 등록",
            description = """
                    사용자가 회원가입할 때 닉네임은 프로필에 저장 시 내부 호출용으로 사용되는 API 입니다.
                
                    [요청 바디]
                    - memberUuid : (String) 회원 고유 식별자
                    - nickname : (String) 등록할 닉네임
                
                    [처리 로직]
                    - 회원의 memberUuid 를 기준으로 닉네임을 Profile 컬렉션에 저장
                
                    [예외 상황]
                    - FAILED_TO_REGISTER_NICKNAME : 닉네임 등록 중 서버 오류 발생 시
                    """
    )
    @PostMapping("/register-nickname")
    public BaseResponseEntity<Void> registerNickname(
            @RequestBody RegisterNicknameReqVo registerNicknameReqVo
    ) {
        profileService.registerNickname(RegisterNicknameReqDto.from(registerNicknameReqVo));
        return new BaseResponseEntity<>(ResponseMessage.SUCCESS_REGISTER_NICKNAME.getMessage());
    }

    /**
     * 2. 닉네임 중복 검사
     *
     * @param nicknameReqVo
     * @return
     */
    @Operation(
            summary = "닉네임 중복 검사",
            description = """
                    사용자가 입력한 닉네임(nickname)이 이미 사용 중인지 확인합니다.

                    [요청 조건]
                    - nickname: 필수 입력, 공백 불가

                    [처리 방식]
                    - 닉네임으로 회원을 조회하여 존재 여부를 확인합니다.
                    - 이미 존재하는 경우 예외를 발생시키고, 존재하지 않으면 성공 응답을 반환합니다.

                    [예외 코드]
                    - NICKNAME_ALREADY_EXISTS: 중복된 닉네임일 경우
                    """
    )
    @PostMapping("/nickname/check-duplicate")
    public BaseResponseEntity<Void> checkNicknameDuplicate(
            @Valid @RequestBody GetNicknameReqVo nicknameReqVo
    ) {
        profileService.checkNicknameDuplicate(GetNicknameReqDto.from(nicknameReqVo));
        return new BaseResponseEntity<>(ResponseMessage.SUCCESS_CHECK_NICKNAME_DUPLICATE.getMessage());
    }

    /**
     * 3. 닉네임 변경
     *
     * @param memberUuid
     * @param changeNicknameReqVo
     * @return
     */
    @Operation(
            summary = "닉네임 변경",
            description = """
                    로그인한 회원 본인의 정보를 조회합니다.

                    [요청 헤더]
                    - X-Member-UUID : (String) 필수 입력, 회원 고유 식별자

                    [응답 필드]
                    - email : (String) 이메일 주소
                    - nickname : (String) 닉네임
                    - gender : (String) 성별 (남성 또는 여성)
                    - birthDate : (LocalDate) 생년월일

                    [처리 로직]
                    - memberUuid 를 기준으로 회원 정보를 조회
                    - 존재하지 않는 회원일 경우 예외 발생

                    [예외 상황]
                    - NO_EXIST_MEMBER: 해당 UUID 로 조회된 회원이 없는 경우
                    """
    )
    @PutMapping("/change-nickname")
    public BaseResponseEntity<Void> changeNickname(
            @RequestHeader("X-Member-UUID") String memberUuid,
            @Valid @RequestBody ChangeNicknameReqVo changeNicknameReqVo
    ) {
        profileService.changeNickname(ChangeNicknameReqDto.of(memberUuid, changeNicknameReqVo));
        return new BaseResponseEntity<>(ResponseMessage.SUCCESS_CHANGE_NICKNAME.getMessage());
    }

    /**
     * 4. 작성자 프로필 조회
     *
     * @param memberUuid
     * @return
     */
    @Operation(
            summary = "작성자 프로필 조회",
            description = """
                    작성자의 닉네임 및 관련 프로필 정보를 조회하는 API 이며, 내부 호출용으로 사용됩니다.
                    
                    [요청 경로]
                    - GET /api/v1/profile/author/{memberUuid}
                    - PathVariable: memberUuid (String) - 조회할 작성자의 UUID
        
                    [응답 필드]
                    - nickname: (String) 작성자의 닉네임
                    - profileImageUrl: (String) 작성자의 프로필 이미지 URL
                    - alt: (String) 이미지 대체 텍스트
        
                    [처리 로직]
                    - memberUuid 를 기준으로 Profile 컬렉션에서 작성자 정보 조회
                    - 존재하지 않을 경우 예외 발생
        
                    [예외 상황]
                    - NO_EXIST_MEMBER: 해당 UUID 에 대한 프로필 정보가 존재하지 않는 경우
                    """
    )
    @GetMapping("/author/{memberUuid}")
    public BaseResponseEntity<GetAuthorInfoVo> getAuthorInfo(
            @PathVariable String memberUuid
    ) {
        return new BaseResponseEntity<>(
                ResponseMessage.SUCCESS_GET_AUTHOR_INFORMATION.getMessage(),
                profileService.getAuthorInfo(memberUuid).toVo());
    }

    /**
     * 5. 프로필 생성
     *
     * @param memberUuid
     * @param createProfileReqVo
     * @return
     */
    @Operation(
            summary = "프로필 생성",
            description = """
                    회원의 프로필 상세 정보를 등록해서 프로필을 생성하는 API 입니다.
        
                    [요청 헤더]
                    - X-Member-UUID : (String) 회원의 고유 식별자
        
                    [요청 바디]
                    - selfIntroduction : (String) 자기소개
                    - imageType : (String) 이미지 타입 (jpg, jpeg, png, gif, webp, svg, heic)
                    - profileImageUrl : (String) 프로필 이미지 URL
                    - categoryListIds : (List<Long>) 관심 카테고리 ID 리스트
        
                    [처리 로직]
                    - 회원의 UUID 로 기존 프로필을 조회합니다.
                    - 닉네임 기반으로 alt 텍스트를 생성합니다. ("{nickname}의 프로필 이미지입니다.")
                    - 기본 등급 (gradeId : 1, gradeName : WHITE) 을 설정합니다.
                    - MongoDB의 profile 컬렉션에 상세 정보를 포함한 새 프로필을 저장합니다.
        
                    [예외 상황]
                    - FAILED_TO_CREATE_PROFILE : 프로필 생성 실패
                    """
    )
    @PostMapping
    public BaseResponseEntity<Void> createProfile(
            @RequestHeader("X-Member-UUID") String memberUuid,
            @Valid @RequestBody CreateProfileReqVo createProfileReqVo
    ) {
        profileService.createProfile(CreateProfileReqDto.of(memberUuid, createProfileReqVo));
        return new BaseResponseEntity<>(ResponseMessage.SUCCESS_CREATE_PROFILE.getMessage());
    }

    /**
     * 6. 프로필 정보 변경
     *
     * @param memberUuid
     * @param updateProfileInfoReqVo
     */
    @Operation(
            summary = "프로필 정보 변경",
            description = """
                    사용자의 프로필 정보를 변경하는 API 입니다.
            
                    [요청 헤더]
                    - X-Member-UUID : (String) 회원의 고유 식별자
            
                    [요청 바디]
                    - selfIntroduction : (String) 자기소개
                    - categoryListIds : (List<Long>) 관심 카테고리 ID 리스트
            
                    [처리 로직]
                    - memberUuid 로 기존 프로필을 조회
                    - 전달받은 값만 반영하여 기존 값과 병합 후 저장
            
                    [예외 상황]
                    - FAILED_TO_UPDATE_PROFILE_INFORMATION : 프로필 정보 변경 실패
                    """
    )
    @PutMapping("/info")
    public BaseResponseEntity<Void> updateProfileInfo(
            @RequestHeader("X-Member-UUID") String memberUuid,
            @Valid @RequestBody UpdateProfileInfoReqVo updateProfileInfoReqVo
    ) {
        profileService.updateProfileInfo(UpdateProfileInfoReqDto.of(memberUuid, updateProfileInfoReqVo));
        return new BaseResponseEntity<>(ResponseMessage.SUCCESS_UPDATE_PROFILE_INFORMATION.getMessage());
    }

    /**
     * 7. 프로필 이미지 변경
     *
     * @param memberUuid
     * @param updateProfileImageReqVo
     * @return
     */
    @Operation(
            summary = "프로필 이미지 변경",
            description = """
                    사용자의 프로필 이미지를 변경하는 API 입니다.
            
                    [요청 헤더]
                    - X-Member-UUID : (String) 회원의 고유 식별자
            
                    [요청 바디]
                    - imageType : (String) 이미지 타입 (jpg, jpeg, png, gif, webp, svg, heic)
                    - profileImageUrl : (String) 프로필 이미지 URL
                    - alt : 프로필 이미지 대체 텍스트
            
                    [처리 로직]
                    - memberUuid 로 기존 프로필을 조회
                    - 전달받은 값만 반영하여 기존 값과 병합 후 저장
            
                    [예외 상황]
                    - FAILED_TO_UPDATE_PROFILE_IMAGE : 프로필 이미지 변경 실패
                    """
    )
    @PutMapping("/image")
    public BaseResponseEntity<Void> updateProfileImage(
            @RequestHeader("X-Member-UUID") String memberUuid,
            @Valid @RequestBody UpdateProfileImageReqVo updateProfileImageReqVo
    ) {
        profileService.updateProfileImage(UpdateProfileImageReqDto.of(memberUuid, updateProfileImageReqVo));
        return new BaseResponseEntity<>(ResponseMessage.SUCCESS_UPDATE_PROFILE_IMAGE.getMessage());
    }
}
