package io.ft.audioextractor.dto;

import java.util.List;

public record YoutubeInfoDto(String title, List<YoutubeStreamingDataFormatDto> streamingDataFormats) {
    public static YoutubeInfoDto of(String title, List<YoutubeStreamingDataFormatDto> streamingDataFormats) {
        return new YoutubeInfoDto(title, streamingDataFormats);
    }
}
