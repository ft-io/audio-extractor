package io.ft.audioextractor.downloader;

public abstract class AbstractInfoExtractor {
    protected abstract InfoDto extract(String url);
}
