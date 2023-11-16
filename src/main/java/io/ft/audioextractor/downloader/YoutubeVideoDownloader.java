package io.ft.audioextractor.downloader;

import io.ft.audioextractor.dto.YoutubeInfoDto;
import io.ft.audioextractor.dto.YoutubeStreamingDataFormatDto;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.DefaultUriBuilderFactory;
import reactor.core.publisher.Flux;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
public class YoutubeVideoDownloader {

    /**
     * [TODO]: 빈으로 빼기 ㅠㅠ
     */
    private final WebClient webClient;
    /**
     * [TODO]: env property로 빼기;
     */
    private final String destination = "";

    public YoutubeVideoDownloader() {
        DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory();
        factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);
        this.webClient = WebClient.builder().uriBuilderFactory(factory).build();
    }

    public void downloadVideo(YoutubeInfoDto ytInfoDto) {
        List<YoutubeStreamingDataFormatDto> formats = ytInfoDto.streamingDataFormats();
        if (formats.size() > 0) {
            // [TODO]: format 소팅하기
            String ytUri = formats.get(0).url();
            // webClient.get().uri(ytUri + "&range=0-240000").retrieve().bodyToMono(String.class).block();
            Flux<DataBuffer> flux = webClient.get().uri(ytUri).retrieve().bodyToFlux(DataBuffer.class);

            // [TODO]: mimeType 붙이기
            Path path = Paths.get(destination + "/" + String.valueOf(System.currentTimeMillis()));
            DataBufferUtils.write(flux, path)
                    .block();
        }
    }
}
