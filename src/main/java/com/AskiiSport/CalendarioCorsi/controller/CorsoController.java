package com.AskiiSport.CalendarioCorsi.controller;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AskiiSport.CalendarioCorsi.auth.Iscritto;
import com.AskiiSport.CalendarioCorsi.entities.Corso;
import com.AskiiSport.CalendarioCorsi.repositories.CrudCorso;



@RestController
@RequestMapping("/corsi")
public class CorsoController {

	@Autowired
	private CrudCorso database;
	
	
	@GetMapping    // mappatura get di default  /corso 
	public Iterable<Corso> get() {
		return database.corsi(); // return tutti i corsi
	}
	
	@GetMapping ("/count/{idCorso}")  // per il conteggio degli iscritti ad un corso
	public int getCount( @PathVariable int idCorso) {
		return database.getCount(idCorso); 
	}
	
	@GetMapping("/{nomeCorsi}")  // get corsi con quel nome 
	public Iterable<Corso> getCorsiNome(@PathVariable String nomeCorsi
			) {
		return database.getCorsiNome(nomeCorsi);
	}
	
	@GetMapping("/{idCorso}") //get corso con quell' id
	public Optional<Corso> getCorsoId(@PathVariable int idCorso) {
		return database.findById(idCorso);
	}
	
	@PostMapping     //aggiungi iscritto ad un determinato corso
	public void add(@RequestBody int iscrittoId,@RequestBody int corsoId) {
		database.aggiungiIscritto(iscrittoId, corsoId);
	}
	
	
	//cancella iscritto ad un determinato corso attraverso il suo id
	@DeleteMapping("/{idIscritto}") 
	public void delete(@PathVariable int idIscritto,@RequestBody int corsoId) {
		database.deleteIscritto(idIscritto,corsoId);
	}


	
}

