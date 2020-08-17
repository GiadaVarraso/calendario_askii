package com.AskiiSport.CalendarioCorsi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.AskiiSport.CalendarioCorsi.auth.AuthService;

//import java.time.LocalDate;   DAVA ERRORE MENTRE PARSAVA



@RestController
@RequestMapping("/signup")

public class SignUpController {

   @Autowired
	private AuthService authService;
	
	@PostMapping
	public String signup(@RequestParam String nome, @RequestParam String cognome,
			        @RequestParam String email,@RequestParam String username, @RequestParam String password) {
		
		authService.signup(nome,cognome,email,username, password);
		return "OK";
	}
	
	//commento a caso per GitHub
}
