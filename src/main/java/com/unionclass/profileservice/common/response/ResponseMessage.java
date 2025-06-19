package com.unionclass.profileservice.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseMessage {

    SUCCESS_REGISTER_NICKNAME("닉네임 등록에 성공하였습니다."),
    SUCCESS_CHECK_NICKNAME_DUPLICATE("닉네임 중복 검사에 성공하였습니다."),
    SUCCESS_CHANGE_NICKNAME("닉네임 변경에 성공하였습니다."),
    SUCCESS_GET_AUTHOR_INFORMATION("작성자 프로필 조회에 성공하였습니다."),
    SUCCESS_CREATE_PROFILE("프로필 생성에 성공하였습니다."),
    SUCCESS_UPDATE_PROFILE_INFORMATION("프로필 정보 변경에 성공하였습니다."),
    SUCCESS_CREATE_GRADE("등급 생성에 성공하였습니다."),
    SUCCESS_GET_ALL_GRADES("등급 전체 조회에 성공하였습니다."),
    SUCCESS_GET_GRADE_NAME("등급명 조회에 성공하였습니다."),
    SUCCESS_UPDATE_PROFILE_IMAGE("프로필 이미지 변경에 성공하였습니다."),
    SUCCESS_GET_MY_PROFILE_INFORMATION("내 프로필 정보 조회에 성공하였습니다."),
    SUCCESS_GET_MEMBER_PROFILE_INFORMATION("회원 프로필 정보 조회에 성공하였습니다."),
    SUCCESS_UPDATE_GRADE_INFORMATION("등급 정보 변경에 성공하였습니다."),
    ;

    private final String message;
}