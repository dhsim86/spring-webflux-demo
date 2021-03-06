package com.dongho.demo.springwebflux.web.controller;

import java.time.Duration;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

import com.dongho.demo.springwebflux.config.DemoConfiguration;

@Slf4j
@RestController
public class HostController {

	@Autowired
	private DemoConfiguration demoConfiguration;

	@GetMapping("/host-info")
	public Mono<String> getHostInfo(ServerHttpRequest request, @RequestParam(name = "delay", required = false) Integer second) {
		if (second == null) {
			second = 0;
		}

		log.info("path: {}, queryParams: {}, headers: {}", request.getPath(), request.getQueryParams(), request.getHeaders());
		return Mono.defer(() -> Mono.just(String.format("url: host-info, %s:%s", demoConfiguration.getHostname(), demoConfiguration.getServerPort())))
			.delayElement(Duration.ofSeconds(second))
			.doOnNext(s -> log.info("{}", s));
	}

}
