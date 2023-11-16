package io.ft.audioextractor.dto;

import java.util.List;

public record YoutubeInfoDto(List<YoutubeStreamingDataFormatDto> streamingDataFormats) {
    public static YoutubeInfoDto of(List<YoutubeStreamingDataFormatDto> streamingDataFormats) {
        return new YoutubeInfoDto(streamingDataFormats);
    }
}
