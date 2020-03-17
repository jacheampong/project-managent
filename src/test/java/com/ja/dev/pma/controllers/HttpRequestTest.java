package com.ja.dev.pma.controllers;

import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest
public class HttpRequestTest {

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestTemplate restTemplate;
	
	
}
