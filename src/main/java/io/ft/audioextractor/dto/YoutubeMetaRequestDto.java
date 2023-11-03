package io.ft.audioextractor.dto;

import java.util.Map;

public class YoutubeMetaRequestDto {
    public String query;
    public Map context;

    public YoutubeMetaRequestDto(String query, Map context) {
        this.query = query;
        this.context = context;
    }

    public String getQuery() {
        return this.query;
    }

    public Map getContext() {
        return this.context;
    }

}
