package com.Explict.springtube.project.Service;

import com.Explict.springtube.project.Dto.UploadVideoResponse;
import com.Explict.springtube.project.Dto.VideoDto;
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
    public UploadVideoResponse uploadVideo(MultipartFile file){
        String videoUrl = s3Service.uploadFile(file);
        var video = new Video();
        video.setVideoUrl(videoUrl);

        var saveVideo = videoRepository.save(video);
        return new UploadVideoResponse(saveVideo.getId(), saveVideo.getVideoUrl());
    }

    public VideoDto editVideo(VideoDto videoDto) {
        // find the video ById
        var saveVideo = getVideoById( videoDto.getId());
        // Map the videoDto fields to video
        saveVideo.setTitle(videoDto.getTitle());
        saveVideo.setDescription(videoDto.getDescription());
        saveVideo.setTags(videoDto.getTags());
        saveVideo.setThumbnail(videoDto.getThumbnailUrl());
        saveVideo.setVideoStatus(videoDto.getVideoStatus());

        // save video to database
        videoRepository.save(saveVideo);
        return videoDto;
    }

    public String uploadThumbnail(MultipartFile file, String videoId) {
        // find video ById
        var saveVideo = getVideoById( videoId);
        String thumbnail = s3Service.uploadFile(file);
        saveVideo.setThumbnail(thumbnail);

        // save thumbnail to video database
        videoRepository.save(saveVideo);
        return thumbnail;
    }
    Video getVideoById(String videoId){
        return videoRepository.findById(videoId)
                .orElseThrow(() -> new IllegalArgumentException("can not find video by id - " + videoId));
    }

    public VideoDto getVideoDetails(String videoId) {
        Video saveVideo = getVideoById(videoId);
        VideoDto videoDto = new VideoDto();
        videoDto.setVideoUrl(saveVideo.getVideoUrl());
        videoDto.setThumbnailUrl(saveVideo.getThumbnail());
        videoDto.setTitle(saveVideo.getTitle());
        videoDto.setId(saveVideo.getId());
        videoDto.setDescription(saveVideo.getDescription());
        videoDto.setTags(saveVideo.getTags());
        videoDto.setVideoStatus(saveVideo.getVideoStatus());

        return videoDto;
    }
}
