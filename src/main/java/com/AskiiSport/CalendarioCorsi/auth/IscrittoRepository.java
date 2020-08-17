package com.AskiiSport.CalendarioCorsi.auth;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IscrittoRepository extends CrudRepository<Iscritto, Integer>, UserDAO{

}
