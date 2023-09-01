package it.corso.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.Autore;
import it.corso.model.Libro;

public interface LibroDao extends CrudRepository<Libro, Integer>{

	List<Libro> findByAutore(Autore autore);
}
