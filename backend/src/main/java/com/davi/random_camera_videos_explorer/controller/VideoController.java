package com.davi.random_camera_videos_explorer.controller;

import com.davi.random_camera_videos_explorer.dto.VideoInfoDTO;
import com.davi.random_camera_videos_explorer.service.VideoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/videos")
@CrossOrigin(origins = "*")
public class VideoController {
    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/random")
    public ResponseEntity<VideoInfoDTO> getRandomVideoInfo() {
        try {
            VideoInfoDTO videoInfo = videoService.getRandomVideoInfo();
            return new ResponseEntity<>(videoInfo, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
