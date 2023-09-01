package it.corso.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.corso.dao.CategoriaDao;
import it.corso.dao.LibroDao;
import it.corso.model.Categoria;
import it.corso.model.Libro;

@Service
public class CategoriaServiceImpl implements CategoriaService{

	@Autowired
	CategoriaDao categoriaDao;
	
	@Autowired
	LibroDao libroDao;
	@Override
	public void aggiungiCategoria(Categoria categoria) {
		
		categoriaDao.save(categoria);
		
	}

	@Override
	public void eliminaCategoria(Categoria categoria) {
		
		//vincolo di integrità. elimino prima tutti i libri associati alla categoria
		List<Libro> libri = categoria.getLibri();
		for(Libro l : libri) 
			libroDao.delete(l);
		
		categoriaDao.delete(categoria);
		
	}

	@Override
	public List<Categoria> getCategorie() {
		
		return (List<Categoria>) categoriaDao.findAll();
	}

	@Override
	public Categoria getCategoriaById(int id) {
		
		return categoriaDao.findById(id).get();
	}

}
