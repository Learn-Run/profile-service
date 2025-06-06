package com.unionclass.profileservice.domain.profile.dto.in;

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
    private List<Long> categoryListIds;

    @Builder
    public UpdateProfileInfoReqDto(String memberUuid, String selfIntroduction, List<Long> categoryListIds) {
        this.memberUuid = memberUuid;
        this.selfIntroduction = selfIntroduction;
        this.categoryListIds = categoryListIds;
    }

    public static UpdateProfileInfoReqDto of(String memberUuid, UpdateProfileInfoReqVo updateProfileReqVo) {
        return UpdateProfileInfoReqDto.builder()
                .memberUuid(memberUuid)
                .selfIntroduction(updateProfileReqVo.getSelfIntroduction())
                .categoryListIds(updateProfileReqVo.getCategoryListIds())
                .build();
    }

    public Profile toEntity(Profile profile) {
        return Profile.builder()
                .id(profile.getId())
                .memberUuid(memberUuid)
                .selfIntroduction(selfIntroduction == null ? profile.getSelfIntroduction() : selfIntroduction)
                .categoryListIds(categoryListIds == null ? profile.getCategoryListIds() : categoryListIds)
                .build();
    }
}
