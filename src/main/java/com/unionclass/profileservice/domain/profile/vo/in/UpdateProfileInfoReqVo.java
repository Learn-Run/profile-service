package com.unionclass.profileservice.domain.profile.vo.in;

import com.unionclass.profileservice.domain.profile.entity.Category;
import lombok.Getter;

import java.util.List;

@Getter
public class UpdateProfileInfoReqVo {

    private String selfIntroduction;
    private List<Category> categoryList;
}