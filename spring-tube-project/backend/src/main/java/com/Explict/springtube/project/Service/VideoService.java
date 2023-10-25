package com.Explict.springtube.project.Service;

import com.Explict.springtube.project.Model.Video;
import com.Explict.springtube.project.Repository.VideoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final  S3Service s3Service;
    private final VideoRepository videoRepository;
    public void uploadVideo(MultipartFile file){
        String videoUrl = s3Service.uploadFile(file);
        var video = new Video();
        video.setVideoUrl(videoUrl);

        videoRepository.save(video);
    }
}
