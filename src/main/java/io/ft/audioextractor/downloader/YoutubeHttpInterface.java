package io.ft.audioextractor.downloader;

import io.ft.audioextractor.dto.YoutubeSearchMetaRequestDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.Map;

@HttpExchange("https://www.youtube.com")
public interface YoutubeHttpInterface {
    /**
     * [TODO]: Definite Return Class
     * @return
     */
    @PostExchange("/youtubei/v1/search?key=AIzaSyAO_FJ2SlqU8Q4STEHLGCilw_Y9_11qcW8")
    Map searchMeta(@RequestBody YoutubeSearchMetaRequestDto request);

    @GetExchange("/watch")
    String getWebPage(@RequestParam Map queryParams);
}
