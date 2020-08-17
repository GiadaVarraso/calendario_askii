package com.AskiiSport.CalendarioCorsi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/accountmanager")
public class AccountController {
	
	@GetMapping
	public String get() {
		return "<h1>Welcome account manager</h1>";
	}
}
