package com.AskiiSport.CalendarioCorsi.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.AskiiSport.CalendarioCorsi.auth.AuthService;


@Configuration

@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final PasswordEncoder passwordEncoder;
	private final AuthService authService;

	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, AuthService authService) {
		super();
		this.passwordEncoder = passwordEncoder;
		this.authService = authService;
	}

	@Bean
	public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
	    return new MySimpleUrlAuthenticationSuccessHandler();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http																		//FIXME
				.csrf().disable()
				.authorizeRequests()
				.antMatchers("/","/FrontEnd/Html/index.html",
							"/FrontEnd/Html/cerca.html",
							"/FrontEnd/Html/categorie.html",
							"/FrontEnd/Html/iscrizione.html", 
							"/signup", "/login", "/logout",
							"/FrontEnd/Html/fail.html",
							"/FrontEnd/Html/forbidden.html",
							"/FrontEnd/Html/loggedout.html",
							"/FrontEnd/Javascript/**",
							"/FrontEnd/Style/**",
							"/FrontEnd/Img/**")
				.permitAll()				
				.antMatchers("/FrontEnd/Html/carrello.html",
						"/FrontEnd/Html/listadesideri.html")
				.hasAnyRole(Roles.ADMIN, Roles.USER)
				.antMatchers("/accountmanager/**").hasAnyRole(Roles.ADMIN)//FIXME
				.anyRequest()
				.authenticated()
				.and()
				.exceptionHandling()
				.accessDeniedPage("/FrontEnd/forbidden.html")
				.and()
				.formLogin()
				.loginPage("/FrontEnd/Html/accesso.html") 
				.loginProcessingUrl("/login")  
				.permitAll()		
				//questo per fare un reindirizzamento specifico per l admin dopo il login
				.successHandler(myAuthenticationSuccessHandler())						
				//.defaultSuccessUrl(myAuthenticationSuccessHandler())
				
				.failureUrl("/FrontEnd/Html/fail.html") 
				.and()
				.logout().logoutUrl("/logout")
				.logoutSuccessUrl("/FrontEnd/Html/loggedout.html")
				.clearAuthentication(true)
				.deleteCookies("JSESSIONID","remember-me")
				.invalidateHttpSession(true)
				.logoutSuccessUrl("/FrontEnd/Html/index.html")
				.and()
				.formLogin()
				;
				
	}
	
	

	@Bean 
	// questo oggetto servirà a spring security per andare a cercare gli utenti da un service
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(authService);
		return provider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}

	
	

	
	
	
	

}
