package com.unionclass.profileservice.domain.profile.vo.out;

import com.unionclass.profileservice.domain.grade.entity.Grade;
import com.unionclass.profileservice.domain.profile.entity.Image;
import com.unionclass.profileservice.domain.profile.entity.ProfileGrade;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetAuthorInfoVo {

    private String memberUuid;
    private String nickname;
    private ProfileGrade grade;
    private Image profileImage;

    @Builder
    public GetAuthorInfoVo(String memberUuid, String nickname, ProfileGrade grade, Image profileImage) {
        this.memberUuid = memberUuid;
        this.nickname = nickname;
        this.grade = grade;
        this.profileImage = profileImage;
    }
}
