package com.dongho.demo.springwebflux.web.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.net.InetAddress;

@Slf4j
@RestController
public class HostController {

    @Value("${server.port}")
    private String serverPort;

    private String hostname;

    @SneakyThrows
    @PostConstruct
    public void init() {
        hostname = InetAddress.getLocalHost().getHostName();
    }

    @GetMapping("/host-info")
    public Mono<String> getHostInfo() {
         return Mono.defer(() -> Mono.just(String.format("%s:%s", hostname, serverPort)))
            .doOnNext(s -> log.info("{}", s));
    }

}
