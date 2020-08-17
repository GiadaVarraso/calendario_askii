package com.AskiiSport.CalendarioCorsi.auth;


import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.AskiiSport.CalendarioCorsi.entities.Corso;
import com.AskiiSport.CalendarioCorsi.security.Roles;

@Entity
@Table(name = "iscritti")
public class Iscritto implements UserDetails {

	private static final Map<String, Collection<? extends GrantedAuthority>> AUTHORITIES = new HashMap<>();

	{
		AUTHORITIES.put(Roles.ADMIN, Arrays.asList(new GrantedAuthority[] { new SimpleGrantedAuthority("ROLE_ADMIN"),
				new SimpleGrantedAuthority("management"), }));
		AUTHORITIES.put(Roles.USER, Arrays.asList(new GrantedAuthority[] { new SimpleGrantedAuthority("ROLE_USER") }));
		
	}
	
	
	public static Collection<? extends GrantedAuthority> getAuthorityOfRole(String role) {
		return AUTHORITIES.get(role);
	}
	private static final long serialVersionUID = 1237489217380966710L;
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id; 
	String nome;
	String cognome;
	@Column(unique = true)
	String eMail;
	@Column(unique = true)
	private String username;
	private String password;
	private String ruolo;
	
	
	 
    public Iscritto() {
		super();
	}
    
	public Iscritto(int id, String nome, String cognome, String eMail, String username, String password, String ruolo,
			Set<Corso> corsi) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.eMail = eMail;
		this.username = username;
		this.password = password;
		this.ruolo = ruolo;
		this.corsi = corsi;
	}
    
    
    
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "iscritti_corsi",
            joinColumns = {
                    @JoinColumn(name = "iscritti_id", referencedColumnName = "id",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "corsi_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
	
	
    private Set<Corso> corsi = new HashSet<>();	
	



	
	
	



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public Set<Corso> getCorsi() {
		return corsi;
	}

	public void setCorsi(Set<Corso> corsi) {
		this.corsi = corsi;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Iscritto [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", eMail=" + eMail + ", username="
				+ username + ", password=" + password + ", ruolo=" + ruolo + ", corsi=" + corsi + "]";
	}
	
	
	
	
	
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return getAuthorityOfRole(this.ruolo);
	}



	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}



	@Override
	public boolean isAccountNonExpired() {
		return true;
	}



	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}



	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}



	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
}
