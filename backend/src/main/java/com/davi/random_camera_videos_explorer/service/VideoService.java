package com.davi.random_camera_videos_explorer.service;

import com.davi.random_camera_videos_explorer.dto.VideoInfoDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class VideoService {
    private final RestTemplate restTemplate;
    @Value("${api.key}")
    private String apiKey;

    public VideoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public VideoInfoDTO getRandomVideoInfo() throws JsonProcessingException {
        final String keyword = "IMG_" + getRandomNumberBetween(0, 1000);

        List<JsonNode> searchItems = getSearchItems(keyword);
        JsonNode randomVideo = searchItems.get(getRandomNumberBetween(0, searchItems.size() - 1));

        String videoId = randomVideo.path("id").path("videoId").asText();
        String url = "https://www.youtube.com/watch?v=" + videoId;
        String title = randomVideo.path("snippet").path("title").asText();
        String publishDateString = randomVideo.path("snippet").path("publishedAt").asText();
        LocalDateTime publishDate = LocalDateTime.parse(publishDateString, DateTimeFormatter.ISO_DATE_TIME);

        JsonNode videoStatistics = getVideoStatistics(videoId);
        int views = videoStatistics.path("viewCount").asInt();

        return new VideoInfoDTO(url, title, publishDate, views);
    }

    private String getSearchApiUrl(String keyword) {
        return "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=5&q=" + keyword + "&type=video&key=" + apiKey;
    }

    private String getVideosApiUrl(String videoId) {
        return "https://www.googleapis.com/youtube/v3/videos?part=statistics&id=" + videoId + "&key=" + apiKey;
    }

    private List<JsonNode> getSearchItems(String keyword) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        ResponseEntity<String> searchResponse = restTemplate.getForEntity(getSearchApiUrl(keyword), String.class);
        JsonNode searchRootNode = mapper.readTree(searchResponse.getBody());
        JsonNode searchItemsNode = searchRootNode.path("items");
        return StreamSupport.stream(searchItemsNode.spliterator(), false).toList();
    }

    private JsonNode getVideoStatistics(String videoId) throws JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        ResponseEntity<String> videoResponse = restTemplate.getForEntity(getVideosApiUrl(videoId), String.class);
        JsonNode videoRootNode = mapper.readTree(videoResponse.getBody());
        JsonNode videoItemsNode = videoRootNode.path("items");
        JsonNode video = StreamSupport.stream(videoItemsNode.spliterator(), false).toList().get(0);
        return video.path("statistics");
    }

    private int getRandomNumberBetween(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
