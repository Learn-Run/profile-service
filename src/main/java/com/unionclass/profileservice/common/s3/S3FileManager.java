package com.unionclass.profileservice.common.s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.unionclass.profileservice.common.exception.BaseException;
import com.unionclass.profileservice.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class S3FileManager {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public UploadFileResponse upload(MultipartFile multipartFile, String dirName) throws IOException {
        String originalName = multipartFile.getOriginalFilename();
        String extension = getFileExtension(originalName);
        String safeName = sanitizeFileName(originalName);
        String fileName = dirName + "/" + UUID.randomUUID() + "_" + safeName;

        byte[] bytes = IOUtils.toByteArray(multipartFile.getInputStream());
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(multipartFile.getContentType());
        metadata.setContentLength(bytes.length);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        try {
            amazonS3.putObject(new PutObjectRequest(bucket, fileName, byteArrayInputStream, metadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
        } catch (AmazonS3Exception e) {
            log.error("S3 업로드 실패: {}", e.getMessage());
            throw new BaseException(ErrorCode.FAILED_TO_UPLOAD_FILE);
        } finally {
            byteArrayInputStream.close();
        }

        String fileUrl = amazonS3.getUrl(bucket, fileName).toString();
        return new UploadFileResponse(originalName, extension, multipartFile.getSize(), fileUrl);
    }

    public void delete(String fileUrl) {
        try {
            URL url = new URL(fileUrl);
            String decodedKey = URLDecoder.decode(url.getPath(), StandardCharsets.UTF_8).substring(1);
            amazonS3.deleteObject(bucket, decodedKey);
        } catch (Exception e) {
            log.error("S3 삭제 실패: {}", e.getMessage());
            throw new BaseException(ErrorCode.FAILED_TO_DELETE_FILE);
        }
    }

    public UploadFileResponse update(MultipartFile newFile, String oldFileUrl, String dirName) throws IOException {
        delete(oldFileUrl);
        return upload(newFile, dirName);
    }

    private String sanitizeFileName(String filename) {
        return filename != null ? filename.replaceAll("[^a-zA-Z0-9._-]", "_") : "unknown";
    }

    private String getFileExtension(String filename) {
        return filename != null && filename.contains(".") ?
                filename.substring(filename.lastIndexOf('.') + 1) : "";
    }
}