package com.dongho.demo.springwebflux.config;

import java.net.InetAddress;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.SneakyThrows;

@Getter
@Configuration
public class DemoConfiguration {

	@Value("${server.port}")
	private String serverPort;

	private String hostname;

	@SneakyThrows
	@PostConstruct
	public void init() {
		hostname = InetAddress.getLocalHost().getHostName();
	}

}
