package io.ft.audioextractor.downloader;

import io.ft.audioextractor.dto.YoutubeInfoDto;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class Downloader {
    private final YoutubeInfoExtractor youtubeInfoExtractor;
    private final YoutubeVideoDownloader youtubeVideoDownloader;

    public Downloader(YoutubeInfoExtractor youtubeInfoExtractor, YoutubeVideoDownloader youtubeVideoDownloader) {
        this.youtubeInfoExtractor = youtubeInfoExtractor;
        this.youtubeVideoDownloader = youtubeVideoDownloader;
    }

    public File download(String url) {
        YoutubeInfoDto ytInfoDto = youtubeInfoExtractor.extract(url);
        return youtubeVideoDownloader.downloadVideo(ytInfoDto);
    }

}
