package com.reactive.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@AllArgsConstructor
public class VideoStreamingService {

     private static final String FORMAT = "classpath:videos/%s.mp4";
    @Autowired
    ResourceLoader resourceLoader;

    public  Mono<Resource> streamVideo(String title){
      return Mono.fromSupplier(()->resourceLoader.getResource(String.format(FORMAT,title)));
    }

}
