package com.unionclass.profileservice.domain.profile.infrastructure;

import com.unionclass.profileservice.domain.profile.entity.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProfileRepository extends MongoRepository<Profile, String> {
    Optional<Profile> findByNickname(String nickname);
    Optional<Profile> findByMemberUuid(String memberUuid);
}