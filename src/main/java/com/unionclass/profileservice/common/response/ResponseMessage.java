package com.unionclass.profileservice.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseMessage {

    SUCCESS_CREATE_PROFILE("프로필 생성에 성공하였습니다."),
    ;

    private final String message;
}