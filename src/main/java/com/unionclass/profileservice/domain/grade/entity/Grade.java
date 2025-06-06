package com.unionclass.profileservice.domain.grade.entity;

import com.unionclass.profileservice.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
public class Grade extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("등급명")
    @Column(nullable = false, unique = true, length = 20)
    private String name;

    @Comment("등급 조건 설명")
    @Column(nullable = false, length = 255)
    private String requirement;

    @Comment("기본 등급 여부")
    @Column(nullable = false)
    private boolean defaultStatus;

    @Comment("수수료 할인율")
    @Column(precision = 5, scale = 2)
    private BigDecimal commissionDiscountRate;

    @Builder
    public Grade(Long id, String name, String requirement, boolean defaultStatus, BigDecimal commissionDiscountRate) {
        this.id = id;
        this.name = name;
        this.requirement = requirement;
        this.defaultStatus = defaultStatus;
        this.commissionDiscountRate = commissionDiscountRate;
    }
}
