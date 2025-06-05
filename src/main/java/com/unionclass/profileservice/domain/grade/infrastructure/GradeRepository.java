package com.unionclass.profileservice.domain.grade.infrastructure;

import com.unionclass.profileservice.domain.grade.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}
