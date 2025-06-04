package com.unionclass.profileservice.domain.profile.presentation;

import com.unionclass.profileservice.common.response.BaseResponseEntity;
import com.unionclass.profileservice.common.response.ResponseMessage;
import com.unionclass.profileservice.domain.profile.application.ProfileService;
import com.unionclass.profileservice.domain.profile.dto.in.ChangeNicknameReqDto;
import com.unionclass.profileservice.domain.profile.dto.in.CreateProfileReqDto;
import com.unionclass.profileservice.domain.profile.dto.in.GetNicknameReqDto;
import com.unionclass.profileservice.domain.profile.dto.in.RegisterNicknameReqDto;
import com.unionclass.profileservice.domain.profile.vo.in.ChangeNicknameReqVo;
import com.unionclass.profileservice.domain.profile.vo.in.CreateProfileReqVo;
import com.unionclass.profileservice.domain.profile.vo.in.GetNicknameReqVo;
import com.unionclass.profileservice.domain.profile.vo.in.RegisterNicknameReqVo;
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
                    - imageUrl : (String) 프로필 이미지 URL
                    - alt : (String) 이미지 대체 텍스트
                    - categoryListIds : (List<Long>) 관심 카테고리 ID 리스트

                    [처리 로직]
                    - 상세 정보를 추가하여 프로필을 생성합니다.
                    - MongoDB `profile` 컬렉션에 저장됩니다.

                    [예외 상황]
                    - NO_EXIST_MEMBER : 해당 UUID 를 가진 회원 정보가 존재하지 않음
                    - FAILED_TO_CREATE_PROFILE : 프로필 저장 중 내부 서버 오류
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
}
