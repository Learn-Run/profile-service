package com.unionclass.profileservice.common.kafka.util;

import com.unionclass.profileservice.common.kafka.event.MemberCreatedEvent;
import com.unionclass.profileservice.domain.profile.application.ProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final ProfileService profileService;

    @KafkaListener(
            topics = "member-created",
            groupId = "profile-group",
            containerFactory = "memberCreatedEventListener"
    )
    public void consumeMemberCreatedEvent(
            MemberCreatedEvent memberCreatedEvent,
            ConsumerRecord<String, MemberCreatedEvent> consumerRecord
    ) {
        log.info("회원 생성 이벤트 수신 완료: {}", memberCreatedEvent);
        log.info("회원 생성 이벤트 수신 - topic: {}, partition: {}, offset: {}",
                consumerRecord.topic(), consumerRecord.partition(), consumerRecord.offset());
        profileService.initializeProfile(memberCreatedEvent);
    }
}
