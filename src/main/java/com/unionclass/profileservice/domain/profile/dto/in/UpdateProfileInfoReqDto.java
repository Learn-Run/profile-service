package com.unionclass.profileservice.domain.profile.dto.in;

import com.unionclass.profileservice.domain.profile.entity.Category;
import com.unionclass.profileservice.domain.profile.entity.Profile;
import com.unionclass.profileservice.domain.profile.enums.ImageType;
import com.unionclass.profileservice.domain.profile.vo.in.UpdateProfileInfoReqVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UpdateProfileInfoReqDto {

    private String memberUuid;
    private String selfIntroduction;
    private List<Category> categoryList;

    @Builder
    public UpdateProfileInfoReqDto(String memberUuid, String selfIntroduction, List<Category> categoryList) {
        this.memberUuid = memberUuid;
        this.selfIntroduction = selfIntroduction;
        this.categoryList = categoryList;
    }

    public static UpdateProfileInfoReqDto of(String memberUuid, UpdateProfileInfoReqVo updateProfileReqVo) {
        return UpdateProfileInfoReqDto.builder()
                .memberUuid(memberUuid)
                .selfIntroduction(updateProfileReqVo.getSelfIntroduction())
                .categoryList(updateProfileReqVo.getCategoryList())
                .build();
    }

    public Profile toEntity(Profile profile) {
        return Profile.builder()
                .id(profile.getId())
                .memberUuid(memberUuid)
                .nickname(profile.getNickname())
                .image(profile.getImage())
                .selfIntroduction(selfIntroduction == null ? profile.getSelfIntroduction() : selfIntroduction)
                .categoryList(categoryList == null ? profile.getCategoryList() : categoryList)
                .grade(profile.getGrade())
                .build();
    }
}
