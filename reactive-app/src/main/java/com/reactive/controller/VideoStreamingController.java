package com.reactive.controller;

import com.reactive.service.VideoStreamingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/video")
@CrossOrigin("*")
@Slf4j
public class VideoStreamingController {

    private final VideoStreamingService videoStreamingService;

    @GetMapping(value = "/{title}", produces = "video/mp4")
    Mono<Resource> getVideo(@PathVariable String title, @RequestHeader("Range") String range){
        log.info("range :"+range);
     return videoStreamingService.streamVideo(title);
    }
}
