package io.ft.audioextractor.controller;

import io.ft.audioextractor.dto.AudioRequestDto;
import io.ft.audioextractor.videoprocess.VideoProcessService;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/video")
public class AudioController {

    private final VideoProcessService service;
    private static final Logger logger = LoggerFactory.getLogger(AudioController.class);
    private static final String FILE_DIRECTORY = "C:/Users/07601/Desktop/ffmpeg";

    @Autowired
    public AudioController(VideoProcessService service) {
        this.service = service;
    }

    @PostMapping("/audio-extract")
    public ResponseEntity<Resource> extract(@RequestBody AudioRequestDto request) throws IOException {

        String fileName = request.getFileName();
        logger.info(fileName);
//        service.process();
        Path filePath = Paths.get(FILE_DIRECTORY).resolve(fileName);
        Resource resource = new org.springframework.core.io.PathResource(filePath);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        headers.add(HttpHeaders.CONTENT_TYPE, Files.probeContentType(filePath));

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(resource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
