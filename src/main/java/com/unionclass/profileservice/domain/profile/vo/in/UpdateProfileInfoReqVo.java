package com.unionclass.profileservice.domain.profile.vo.in;

import lombok.Getter;

import java.util.List;

@Getter
public class UpdateProfileInfoReqVo {

    private String selfIntroduction;
    private List<Long> categoryListIds;
}