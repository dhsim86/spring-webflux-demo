package com.dongho.demo.springwebflux.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dongho.demo.springwebflux.config.DemoConfiguration;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class FallbackController {

	@Autowired
	private DemoConfiguration demoConfiguration;

	@GetMapping("/fallback")
	public Mono<String> getDelay(ServerHttpRequest request) {
		log.info("path: {}, queryParams: {}, headers: {}", request.getPath(), request.getQueryParams(), request.getHeaders());
		return Mono.defer(() -> Mono.just(String.format("url: fallback, %s:%s", demoConfiguration.getHostname(), demoConfiguration.getServerPort())))
			.doOnNext(s -> log.info("{}", s));
	}

}
