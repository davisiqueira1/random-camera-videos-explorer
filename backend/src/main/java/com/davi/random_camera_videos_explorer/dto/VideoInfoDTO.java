package com.davi.random_camera_videos_explorer.dto;

import java.time.LocalDateTime;

public record VideoInfoDTO(
        String url,
        String title,
        LocalDateTime publishDate,
        int views
) {
}