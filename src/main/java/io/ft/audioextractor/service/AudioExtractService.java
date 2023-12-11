package io.ft.audioextractor.service;

import io.ft.audioextractor.controller.AudioExtractController;
import io.ft.audioextractor.downloader.Downloader;
import io.ft.audioextractor.videoprocess.VideoProcessService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URLEncoder;

@Service
public class AudioExtractService {
    private static final Logger logger = LoggerFactory.getLogger(AudioExtractService.class);

    private final Downloader downloader;
    private final VideoProcessService videoProcessService;

    @Value("${temp.path}")
    private String tempPath;

    @Autowired
    public AudioExtractService(Downloader downloader, VideoProcessService videoProcessService) {
        this.downloader = downloader;
        this.videoProcessService = videoProcessService;
    }
    public void audioExtract(String url, HttpServletResponse response) throws IOException {
        File videoFile = downloader.download(url);
        logger.info("download finish, fileName:{}", videoFile.getName());
        videoProcessService.process(videoFile);
        String fileName = "result.mp3";
        logger.info("videoProcessService finish, fileName:{}", fileName);
        response.setContentType(MediaType.MULTIPART_FORM_DATA_VALUE);
        response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName, "UTF-8")+"\";");
        response.setHeader("Content-Transfer-Encofing", "binary");

        try (
            FileInputStream fis = new FileInputStream(tempPath + "/result.mp3");
            OutputStream out = response.getOutputStream();
        ) {
            int readCount= 0;
            byte[] buffer = new byte[1024];
            while((readCount = fis.read(buffer)) != -1) {
                out.write(buffer, 0, readCount);
            }
        } catch (Exception e) {
            throw new IOException();
        }
    }
}
