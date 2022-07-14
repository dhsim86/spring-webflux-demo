package com.dongho.demo.springwebflux.web.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dongho.demo.springwebflux.config.DemoConfiguration;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class StatusController {

	@Autowired
	private DemoConfiguration demoConfiguration;

	@GetMapping("/status")
	public Mono<String> getStatus(ServerHttpRequest request, ServerHttpResponse response, @RequestParam(name = "code", required = false) Integer code) {
		if (code == null) {
			code = HttpStatus.OK.value();
		}

		log.info("path: {}, queryParams: {}, headers: {}", request.getPath(), request.getQueryParams(), request.getHeaders());

		response.setStatusCode(HttpStatus.valueOf(code));
		return Mono.defer(() -> Mono.just(String.format("url: status, %s:%s", demoConfiguration.getHostname(), demoConfiguration.getServerPort())))
			.doOnNext(s -> log.info("{}", s));
	}

	@GetMapping("/random-status")
	public Mono<String> getRandomStatus(ServerHttpRequest request, ServerHttpResponse response) {
		log.info("path: {}, queryParams: {}, headers: {}", request.getPath(), request.getQueryParams(), request.getHeaders());

		Random random = new Random();
		int result = random.nextInt(2);
		HttpStatus status;

		if (result % 2 == 0) {
			status = HttpStatus.OK;
		} else {
			status = HttpStatus.SERVICE_UNAVAILABLE;
		}

		response.setStatusCode(status);
		return Mono.defer(() -> Mono.just(String.format("url: status, %s:%s", demoConfiguration.getHostname(), demoConfiguration.getServerPort())))
			.doOnNext(s -> log.info("{}", s));
	}

}
