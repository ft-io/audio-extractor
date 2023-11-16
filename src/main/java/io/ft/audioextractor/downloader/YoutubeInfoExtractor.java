package io.ft.audioextractor.downloader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.ft.audioextractor.dto.YoutubeInfoDto;
import io.ft.audioextractor.dto.YoutubeStreamingDataFormatDto;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class YoutubeInfoExtractor {

    private final YoutubeHttpInterface client;
    private final ObjectMapper objectMapper;
    private static final int MATCHED_FIRST_IDX = 1;
    /**
     * [ref]: https://jake-seo-dev.tistory.com/23
     */
    private static final Pattern _YT_INITIAL_PLAYER_RESPONSE_RE  = Pattern.compile("ytInitialPlayerResponse\\s*=\\s*(\\{.+?\\})\\s*;");

    public YoutubeInfoExtractor(YoutubeHttpInterface client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
    }

    public YoutubeInfoDto extract (String url) {

        // [TODO]: url validation
        MultiValueMap<String, String> queryParams = UriComponentsBuilder.fromUriString(url).build().getQueryParams();
        String page = client.getWebPage(queryParams);

        // 일단 디버깅 위해 여기서 예외 캐치
        try {
            List<YoutubeStreamingDataFormatDto> formats = parseYTPlayerResponse(page);
            return YoutubeInfoDto.of(formats);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<YoutubeStreamingDataFormatDto> parseYTPlayerResponse (String webpage) throws JsonProcessingException, Exception {
        Matcher m = _YT_INITIAL_PLAYER_RESPONSE_RE.matcher(webpage);
        if (m.find()) {
            Map<String, Object> playerResponse = objectMapper
                                                    .readValue(
                                                            m.group(MATCHED_FIRST_IDX),
                                                            new TypeReference<Map<String,Object>>() {
                                                    });
            Map<String, Object> streamingData = (Map<String, Object>) playerResponse.get("streamingData");
            List<Map<String, Object>> formats = (List<Map<String, Object>>) streamingData.get("formats");

            return formats.stream().map(format -> YoutubeStreamingDataFormatDto.of(
                    (int)format.get("itag"),
                    (String)format.get("url"),
                    (String)format.get("mimeType"),
                    (int)format.get("bitrate"),
                    (int)format.get("width"),
                    (int)format.get("height"),
                    (String)format.get("lastModified"),
                    (String)format.get("contentLength"),
                    (String)format.get("quality"),
                    (int)format.get("fps"),
                    (String)format.get("qualityLabel"),
                    (String)format.get("projectionType"),
//                    (int) format.get("averageBitrate"), // null ㅠㅠ
                    (String)format.get("audioQuality"),
                    (String)format.get("approxDurationMs"),
                    (String)format.get("audioSampleRate"),
                    (int)format.get("audioChannels")
            )).toList();
        } else {
            throw new Error("Invalid Youtube Web Page.");
        }
    }
}
