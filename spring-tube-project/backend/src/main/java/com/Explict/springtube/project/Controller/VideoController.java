package com.Explict.springtube.project.Controller;

import com.Explict.springtube.project.Service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/videos")
@RequiredArgsConstructor
public class VideoController {

    private final VideoService videoService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void uploadVideo(@RequestParam("file")MultipartFile file){
        videoService.uploadVideo(file);
    }
}
