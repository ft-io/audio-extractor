package io.ft.audioextractor.controller;

import io.ft.audioextractor.dto.AudioRequestDto;
import io.ft.audioextractor.dto.InfoDto;
import io.ft.audioextractor.service.AudioExtractService;
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

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/video")
public class AudioExtractController {

    private final AudioExtractService audioExtractService;
    private static final Logger logger = LoggerFactory.getLogger(AudioExtractController.class);
    private static final String FILE_DIRECTORY = "C:/Users/07601/Desktop/ffmpeg";

    @Autowired
    public AudioExtractController(AudioExtractService audioExtractService) {
        this.audioExtractService = audioExtractService;
    }

    @PostMapping("/audio-extract")
    public void extract(@RequestParam String url, HttpServletResponse response) throws IOException {
        audioExtractService.audioExtract(url, response);
//        String fileName = request.getFileName();
//        logger.info(fileName);
//        Path filePath = Paths.get(FILE_DIRECTORY).resolve(fileName);
//        Resource resource = new org.springframework.core.io.PathResource(filePath);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
//        headers.add(HttpHeaders.CONTENT_TYPE, Files.probeContentType(filePath));
//
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentLength(resource.contentLength())
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .body(resource);
    }
}
