package com.unionclass.profileservice.domain.profile.vo.in;

import lombok.Getter;

import java.util.List;

@Getter
public class UpdateProfileReqVo {

    private String nickname;
    private String selfIntroduction;
    private String imageUrl;
    private String alt;
    private List<Long> categoryListIds;
}
