package com.AskiiSport.CalendarioCorsi.entities;



import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.AskiiSport.CalendarioCorsi.auth.Iscritto;


@Entity
@Table(name = "corsi")
public class Corso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String data;
	private float orario;
	private int maxIscritti;
	
	@ManyToMany(mappedBy = "corsi", fetch = FetchType.LAZY)
    private Set<Iscritto> iscritti = new HashSet<>();


	
	
	
	
	public Corso() {
		super();
	}
	public Corso( int id,String nome, String data,
			float orario, int maxIscritti) {
		super();
		this.id = id;
		this.nome = nome;
		this.data = data;
		this.orario = orario;
		this.maxIscritti = maxIscritti;
	}
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
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public float getOrario() {
		return orario;
	}
	public void setOrario(float orario) {
		this.orario = orario;
	}
	
	public int getMaxIscritti() {
		return maxIscritti;
	}
	public void setMaxIscritti(int maxIscritti) {
		this.maxIscritti = maxIscritti;
	}
	
	
	
	@Override
	public String toString() {
		return id+"  nome corso=" + nome +", data=" + data + ", orario=" + orario
				+ ", numero massimo iscrizioni=" + maxIscritti ;
	}
	
	
	
	
}
