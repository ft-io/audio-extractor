package io.ft.audioextractor.videoprocess;

import net.bramp.ffmpeg.FFmpeg;
import net.bramp.ffmpeg.FFmpegExecutor;
import net.bramp.ffmpeg.FFprobe;
import net.bramp.ffmpeg.builder.FFmpegBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class VideoProcessService {
    private static final String VIDEO = "C:\\Users\\07601\\Desktop\\ffmpeg\\IMG_2722.MOV";

    @Value("${ffmpeg.path}")
    private String ffmpegPath;

    @Value("${ffprobe.path}")
    private String ffprobePath;

    @Value("${temp.path}")
    private String tempPath;

    public boolean process(File uploadVideo) {

        FFmpeg ffmpeg = null;
        FFprobe ffprobe = null;

        try {
            ffmpeg = new FFmpeg(ffmpegPath);
            ffprobe = new FFprobe(ffprobePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        FFmpegBuilder builder = new FFmpegBuilder()
                .overrideOutputFiles(true)
                .setInput(uploadVideo.toPath().toString())
                .addOutput(tempPath + "/result.mp3")
                .done();

        FFmpegExecutor executor = new FFmpegExecutor(ffmpeg, ffprobe);

        try {
            executor.createJob(builder).run();
        } catch (Exception e) {

            throw new RuntimeException(e);
        }

        return true;
    }
}
