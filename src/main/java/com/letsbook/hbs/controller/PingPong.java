package com.letsbook.hbs.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingPong {
    
	@GetMapping("/ping")
	public static String ping() {
		return "pong";
	}
}
