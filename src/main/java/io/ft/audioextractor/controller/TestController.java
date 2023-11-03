package io.ft.audioextractor.controller;

import io.ft.audioextractor.videoprocess.VideoProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    private final VideoProcessService service;

    @Autowired
    public TestController(VideoProcessService service) {
        this.service = service;
    }

    @PostMapping("/audio")
    public boolean extract() {

        service.process();
        return true;
    }
}
