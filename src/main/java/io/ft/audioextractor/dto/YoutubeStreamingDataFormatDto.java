package io.ft.audioextractor.dto;

/**
 * [TODO] 빌더 패턴 적용 할 것 ㅠㅠ
 */
public record YoutubeStreamingDataFormatDto (
        int itag,
        String url,
        String mimeType,
        int bitrate,
        int width,
        int height,
        String lastModified,
        String contentLength,
        String quality,
        int fps,
        String qualityLabel,
        String projectionType,
//        int averageBitrate, // null ㅠㅠ
        String audioQuality,
        String approxDurationMs,
        String audioSampleRate,
        int audioChannels) {

    public static YoutubeStreamingDataFormatDto of(
            int itag,
            String url,
            String mimeType,
            int bitrate,
            int width,
            int height,
            String lastModified,
            String contentLength,
            String quality,
            int fps,
            String qualityLabel,
            String projectionType,
//            int averageBitrate,
            String audioQuality,
            String approxDurationMs,
            String audioSampleRate,
            int audioChannels
    ) {
        return new YoutubeStreamingDataFormatDto(
                itag, url, mimeType, bitrate,
                width, height, lastModified,
                contentLength, quality, fps,
                qualityLabel, projectionType,
                // averageBitrate,
                audioQuality, approxDurationMs, audioSampleRate,
                audioChannels);
    }

}
