package com.unionclass.profileservice.domain.profile.util;

import org.springframework.stereotype.Component;

@Component
public class ImageAltTextTemplateProvider {

    public String getProfileImageAltTextTemplate(String nickname) {
        return nickname + "의 프로필 이미지입니다.";
    }
}
