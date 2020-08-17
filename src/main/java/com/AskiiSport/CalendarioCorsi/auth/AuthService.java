package com.AskiiSport.CalendarioCorsi.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//import org.generation.italy.progettoMario.auth.Utente;

import com.AskiiSport.CalendarioCorsi.security.Roles;


import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {
	
	private IscrittoRepository dao;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	
	public AuthService(IscrittoRepository dao, PasswordEncoder passwordEncoder) {
		this.dao = dao;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<? extends UserDetails> user = dao.findByUsername(username);
		
		if(user.isPresent())
		{
			return user.get();
		}
		throw new UsernameNotFoundException("Nessun utente col username: " + username);
	}
	
	
	
		public void signup(String nome,String cognome, String email, String username, String password) {
			Iscritto newUtente = new Iscritto();
			newUtente.setNome(nome);
			newUtente.setCognome(cognome);		
			newUtente.seteMail(email);
			newUtente.setUsername(username);
			newUtente.setPassword(passwordEncoder.encode(password));
			
			if (username.equalsIgnoreCase("mario")) {
				newUtente.setRuolo(Roles.ADMIN);
			}else {
				newUtente.setRuolo(Roles.USER);
			}
			
			
			try {			
				dao.save(newUtente);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

}
