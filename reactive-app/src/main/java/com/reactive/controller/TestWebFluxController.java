package com.reactive.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;

@RestController
@AllArgsConstructor
@RequestMapping("/test")
@CrossOrigin("*")
public class TestWebFluxController {

    @GetMapping
    public Flux<Object> getAllUsers() {
        return getFlux().delayElements(Duration.ofSeconds(2));
    }

    public Flux<Object> getFlux() {
        return Flux.create(sink -> {
                    for (long i = 0; i < 1000; i++) {
                        sink.next(i);
                    }
                    sink.complete();
                }, FluxSink.OverflowStrategy.BUFFER)
                .subscribeOn(Schedulers.boundedElastic());
    }
}
