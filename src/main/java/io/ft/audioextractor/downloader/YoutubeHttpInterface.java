package io.ft.audioextractor.downloader;

import io.ft.audioextractor.dto.YoutubeMetaRequestDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.Map;

@HttpExchange("https://www.youtube.com/youtubei/v1")
public interface YoutubeHttpInterface {
    /**
     * [TODO]: Definite Return Class
     * @return
     */
    @PostExchange("/search?key=AIzaSyAO_FJ2SlqU8Q4STEHLGCilw_Y9_11qcW8")
    Map searchMeta(@RequestBody YoutubeMetaRequestDto request);
}
