package it.corso.service;

import java.util.List;

import it.corso.model.Autore;

public interface AutoreService {

	void aggiungiAutore(Autore autore);
	void eliminaAutore(Autore autore);
	Autore getAutoreById(int id);
	List<Autore> getAutori();
	
}
