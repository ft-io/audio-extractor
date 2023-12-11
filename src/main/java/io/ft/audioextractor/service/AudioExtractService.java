package io.ft.audioextractor.service;

import io.ft.audioextractor.controller.AudioExtractController;
import io.ft.audioextractor.downloader.Downloader;
import io.ft.audioextractor.videoprocess.VideoProcessService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Service
public class AudioExtractService {
    private static final Logger logger = LoggerFactory.getLogger(AudioExtractService.class);

    private final Downloader downloader;
    private final VideoProcessService videoProcessService;

    @Autowired
    public AudioExtractService(Downloader downloader, VideoProcessService videoProcessService) {
        this.downloader = downloader;
        this.videoProcessService = videoProcessService;
    }
    public void audioExtract(String url, HttpServletResponse response) throws IOException {
        File videoFile = downloader.download(url);
        logger.info("download finish, fileName:{}", videoFile.getName());
        videoProcessService.process(videoFile);
        String fileName = String.format("%s.mp3", videoFile.getName());
        logger.info("videoProcessService finish, fileName:{}", fileName);
        response.setContentType(MediaType.MULTIPART_FORM_DATA_VALUE);
        response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName, "UTF-8")+"\";");
        response.setHeader("Content-Transfer-Encofing", "binary");

        byte[] fileByte = FileUtils.readFileToByteArray(new File("/Users/yubyeong-u/test_dir" + "/" + "result.mp3"));
        response.getOutputStream().write(fileByte);
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }
}
