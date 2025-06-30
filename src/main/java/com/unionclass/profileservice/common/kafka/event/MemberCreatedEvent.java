package com.unionclass.profileservice.common.kafka.event;

import com.unionclass.profileservice.domain.profile.entity.Profile;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberCreatedEvent {

    private String memberUuid;
    private String nickname;

    @Builder
    public MemberCreatedEvent(String memberUuid, String nickname) {
        this.memberUuid = memberUuid;
        this.nickname = nickname;
    }

    public Profile toEntity() {

        return Profile.builder()
                .memberUuid(memberUuid)
                .nickname(nickname)
                .build();
    }
}
