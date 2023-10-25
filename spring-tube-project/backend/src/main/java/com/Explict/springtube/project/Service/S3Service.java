package com.Explict.springtube.project.Service;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service implements FileService{

    public static final String BUCKET_NAME = "spring-tube";

    private final AmazonS3Client s3Client;
    @Override
    public String uploadFile(MultipartFile file){
        //upload file to awsS3
        var filenameExtension= StringUtils.getFilenameExtension(file.getOriginalFilename()) ;
        var key = UUID.randomUUID().toString() + "." + filenameExtension;

        var metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        try {
            s3Client.putObject(BUCKET_NAME, key, file.getInputStream(), metadata);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "An exception occur while uploading a file");
        }
        s3Client.setObjectAcl(BUCKET_NAME, key, CannedAccessControlList.PublicRead);
        return s3Client.getResourceUrl(BUCKET_NAME, key);
    }
}
