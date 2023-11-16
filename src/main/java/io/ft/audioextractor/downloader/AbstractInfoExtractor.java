package io.ft.audioextractor.downloader;

import io.ft.audioextractor.dto.InfoDto;

/**
 * not used
 */
public abstract class AbstractInfoExtractor {
    protected abstract InfoDto extract(String url);
}
