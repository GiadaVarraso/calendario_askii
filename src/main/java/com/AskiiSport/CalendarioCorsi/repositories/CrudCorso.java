package com.AskiiSport.CalendarioCorsi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.AskiiSport.CalendarioCorsi.auth.Iscritto;
import com.AskiiSport.CalendarioCorsi.entities.Corso;

@Repository
public interface CrudCorso extends CrudRepository<Corso, Integer> {
	
	public static final String SELECT_CORSI_TOT = "SELECT * from corsi ;";
	public static final String SELECT_CORSO_NOME = "SELECT * from corsi where nome=? ;";
	public static final String SELECT_CORSO_ID = "SELECT * from corsi where id=? ;";
	public static final String SELECT_CORSO_COUNT =
			"select count(iscritti.id) as numero_iscritti from iscritti " + 
			"inner join iscritti_corsi on corsi_id=iscritti.id" + 
			"inner join corsi on corsi.id=iscritti_corsi.corsi_id" + 
			"where corsi.nome=? group by corsi.id;";
	public static final String ADD_ISCRITTO = ""; 					//FIXME
	public static final String DELETE_ISCRITTO_ID = ""; 			//FIXME
	
	

	@Query(value = SELECT_CORSI_TOT, nativeQuery = true)
	public List<Corso> corsi();
	
	// trova corsi con nome
	@Query(value = SELECT_CORSO_NOME, nativeQuery = true)
	public List<Corso> getCorsiNome(String nome);
	
	//trova il corso attraverso il suo id
	@Query(value = SELECT_CORSO_ID, nativeQuery = true)
	public Corso getCorso(int idCorso);
	
	
	//conteggio iscritti ad un corso
	@Query(value = SELECT_CORSO_COUNT, nativeQuery = true)
	public int getCount(int idCorso);
	
	//iscrizione 														//FIXME
	@Query(value = ADD_ISCRITTO, nativeQuery = true)
	public void aggiungiIscritto(int iscrittoId, int corsoId);
	
	//cancella iscrizione												//FIXME
	@Query(value = DELETE_ISCRITTO_ID, nativeQuery = true)
	public void deleteIscritto(int idIscritto,int corsoId);
	
	
	
	








	
	
}
