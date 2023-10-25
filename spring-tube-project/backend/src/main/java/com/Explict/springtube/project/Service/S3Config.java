package com.Explict.springtube.project.Service;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {
        @Bean
        public static AmazonS3Client amazonS3Client() {
            return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                    .withRegion("eu-west-2")
                    .withCredentials(new DefaultAWSCredentialsProviderChain())
                    .build();
        }
}