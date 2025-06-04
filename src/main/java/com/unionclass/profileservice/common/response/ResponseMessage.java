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
    ;

    private final String message;
}