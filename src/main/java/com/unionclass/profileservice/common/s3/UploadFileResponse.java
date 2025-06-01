package com.unionclass.profileservice.common.s3;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UploadFileResponse {
    private String originalFilename;
    private String extension;
    private long size;
    private String url;
}