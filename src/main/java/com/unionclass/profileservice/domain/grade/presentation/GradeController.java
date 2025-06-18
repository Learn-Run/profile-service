package com.unionclass.profileservice.domain.grade.presentation;

import com.unionclass.profileservice.common.response.BaseResponseEntity;
import com.unionclass.profileservice.common.response.ResponseMessage;
import com.unionclass.profileservice.domain.grade.application.GradeService;
import com.unionclass.profileservice.domain.grade.dto.in.CreateGradeReqDto;
import com.unionclass.profileservice.domain.grade.dto.out.GetAllGradeResDto;
import com.unionclass.profileservice.domain.grade.vo.in.CreateGradeReqVo;
import com.unionclass.profileservice.domain.grade.vo.out.GetAllGradeResVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/grade")
@Tag(name = "grade")
public class GradeController {

    private final GradeService gradeService;

    /**
     * /api/v1/grade
     *
     * 1. 등급 생성
     * 2. 등급 전체 조회
     */

    /**
     * 1. 등급 생성
     *
     * @param createGradeReqVo
     * @return
     */
    @Operation(
            summary = "등급 생성 (개발/테스트용)",
            description = """
                    ⚠️ 본 API 는 실제 사용자에게는 제공되지 않으며, 개발 환경 또는 테스트 목적에 한해 사용됩니다.
                    
                    새로운 등급을 생성할 때 사용하는 API 입니다.
        
                    [요청 바디]
                    - gradeName : (String) 등급 이름 (SILVER, GOLD, PLATINUM, DIAMOND, MASTER)
                    - gradeLevel : (int) 등급 레벨
                    - gradeColor : (String) 등급 색상
                    - gradeRequirement : (String) 등급 조건 설명
                    - defaultStatus : (boolean) 기본 등급 여부
                    - commissionDiscountRate : (BigDecimal) 수수료 할인율
        
                    [처리 로직]
                    - 요청 데이터를 기반으로 새로운 등급 엔티티 생성
        
                    [예외 상황]
                    - FAILED_TO_CREATE_GRADE : 등급 저장 중 내부 서버 오류
                    """
    )
    @PostMapping
    public BaseResponseEntity<Void> createGrade(
            @RequestBody CreateGradeReqVo createGradeReqVo
    ) {
        gradeService.createGrade(CreateGradeReqDto.from(createGradeReqVo));
        return new BaseResponseEntity<>(ResponseMessage.SUCCESS_CREATE_GRADE.getMessage());
    }

    @Operation(
            summary = "등급 전체 조회",
            description = """
                    모든 등급 ID 와 등급명을 조회하는 API 입니다.
        
                    [응답 필드]
                    - gradeId : (Long) 등급  ID
                    - gradeName : (String) 등급명
        
                    [처리 로직]
                    - 등급 테이블에서 모든 등급 엔티티를 조회하여 등급 ID 와 등급명을 리스트로 반환
                    """
    )
    @GetMapping("/all")
    public BaseResponseEntity<List<GetAllGradeResVo>> getAllGrades() {
        return new BaseResponseEntity<>(
                ResponseMessage.SUCCESS_GET_ALL_GRADES.getMessage(),
                gradeService.getAllGrades().stream().map(GetAllGradeResDto::toVo).toList());
    }
}