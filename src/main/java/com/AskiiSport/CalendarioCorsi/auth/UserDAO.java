package com.AskiiSport.CalendarioCorsi.auth;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDAO {
	Optional<? extends UserDetails> findByUsername(String username);
}
