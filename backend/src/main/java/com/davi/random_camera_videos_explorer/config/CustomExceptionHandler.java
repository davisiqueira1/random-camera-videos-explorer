package com.davi.random_camera_videos_explorer.config;

import com.davi.random_camera_videos_explorer.exceptions.VideosNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler {
    private Map<String, String> response(String message) {
        var response = new HashMap<String, String>();
        response.put("message", message);
        return response;
    }

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<Map<String, String>> handleJsonProcessingException(JsonProcessingException e) {
        return new ResponseEntity<>(response(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(VideosNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleVideosNotFoundException(VideosNotFoundException e) {
        return new ResponseEntity<>(response(e.getMessage()), HttpStatus.NOT_FOUND);
    }
}
