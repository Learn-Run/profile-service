package com.unionclass.profileservice.domain.grade.application;

import com.unionclass.profileservice.common.exception.BaseException;
import com.unionclass.profileservice.common.exception.ErrorCode;
import com.unionclass.profileservice.domain.grade.dto.in.CreateGradeReqDto;
import com.unionclass.profileservice.domain.grade.dto.in.UpdateGradeInfoReqDto;
import com.unionclass.profileservice.domain.grade.dto.out.GetAllGradeResDto;
import com.unionclass.profileservice.domain.grade.entity.Grade;
import com.unionclass.profileservice.domain.grade.infrastructure.GradeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GradeServiceImpl implements GradeService {

    private final GradeRepository gradeRepository;

    @Transactional
    @Override
    public void createGrade(CreateGradeReqDto createGradeReqDto) {
        try {
            gradeRepository.save(createGradeReqDto.toEntity());
            log.info("등급 생성 성공 - 등급명 : {}", createGradeReqDto.getGradeName());
        } catch (Exception e) {
            log.warn("등급 생성 실패 - 등급명 : {}", createGradeReqDto.getGradeName());
            throw new BaseException(ErrorCode.FAILED_TO_CREATE_GRADE);
        }
    }

    @Override
    public List<GetAllGradeResDto> getAllGrades() {
        return gradeRepository.findAll().stream().map(GetAllGradeResDto::from).toList();
    }

    @Override
    public Grade getDefaultGradeInfo() {
        return gradeRepository.findByDefaultStatusTrue();
    }

    @Transactional
    @Override
    public void updateGradeInfo(UpdateGradeInfoReqDto updateGradeInfoReqDto) {
        try {
            gradeRepository.save(updateGradeInfoReqDto.toEntity(
                    gradeRepository.findById(updateGradeInfoReqDto.getGradeId())
                            .orElseThrow(() -> new BaseException(ErrorCode.FAILED_TO_FIND_GRADE))));
        } catch (BaseException e) {
            throw  e;
        } catch (Exception e) {
            log.warn("등급 정보 변경 실패 - 등급 ID : {}", updateGradeInfoReqDto.getGradeId());
            throw new BaseException(ErrorCode.FAILED_TO_UPDATE_GRADE_INFORMATION);
        }
    }
}