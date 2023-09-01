package it.corso.service;

import java.util.List;

import it.corso.model.Categoria;


public interface CategoriaService {

	void aggiungiCategoria(Categoria categoria);
	void eliminaCategoria(Categoria categoria);
	List<Categoria> getCategorie();
	Categoria getCategoriaById(int id);
}
