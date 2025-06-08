package com.unionclass.profileservice.domain.profile.vo.in;

import com.unionclass.profileservice.domain.profile.enums.ImageType;
import lombok.Getter;

@Getter
public class UpdateProfileImageReqVo {

    private ImageType profileImageType;
    private String profileImageUrl;
    private String alt;
}
