package com.unionclass.profileservice.domain.profile.vo.in;

import com.unionclass.profileservice.domain.profile.enums.ImageType;
import lombok.Getter;

import java.util.List;

@Getter
public class CreateProfileReqVo {

    private String selfIntroduction;
    private ImageType imageType;
    private String profileImageUrl;
    private List<Long> categoryListIds;
}
