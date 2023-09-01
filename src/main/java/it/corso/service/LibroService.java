package it.corso.service;

import java.util.List;

import it.corso.model.Autore;
import it.corso.model.Libro;

public interface LibroService {


	void aggiungiLibro(Object...dati);
	void eliminaLibro(Libro libro);
	List<Libro> getLibri();
	Libro getLibroById(int id);
	void modificaLibro(Libro libro, Object...dati);
	List<Libro> getLibriByAutore(Autore autore);
}
