package com.dongho.demo.springwebflux.web.controller;

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

}
