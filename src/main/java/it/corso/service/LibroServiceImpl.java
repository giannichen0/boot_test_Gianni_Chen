package it.corso.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import it.corso.dao.AutoreDao;
import it.corso.dao.CategoriaDao;
import it.corso.dao.LibroDao;
import it.corso.model.Autore;
import it.corso.model.Categoria;
import it.corso.model.Libro;

@Service
public class LibroServiceImpl implements LibroService {

	@Autowired
	LibroDao libroDao;
	
	@Autowired
	AutoreDao autoreDao;
	
	@Autowired
	CategoriaDao categoriaDao;

	@Override
	public void aggiungiLibro(Object...dati) {
		Libro libro = new Libro();
		Autore autore = null;
		Categoria categoria = null;
		
		
		//tutto il codice sottostante con gli optional mi serve per controllare se nel db è già presente o meno un autore del libro che aggiungo
		//se non lo facessi a ogni aggiunta mi creerebbe un nuovo autore e/o categoria anche se già presenti nel db
		Optional<Autore> autoreOptional = Optional.ofNullable(autoreDao.findByNomeAndCognome((String) dati[2], (String) dati[3]));
	    if (autoreOptional.isPresent())
	    {
	        autore = autoreOptional.get();
	    	autore.setNome((String) dati[2]);
	    	autore.setCognome((String) dati[3]);
	    	autoreDao.save(autore);
	    }else {
	     	autore = new Autore();
	        autore.setNome((String) dati[2]);
	        autore.setCognome((String) dati[3]);
	        //dato che ho cascade type refresh lo salvo prima con autoreDao.
	        autoreDao.save(autore);
	    }
		
	    
	        Optional<Categoria> categoriaOptional = Optional.ofNullable(categoriaDao.findByDescrizione((String) dati[4]));
			if(categoriaOptional.isPresent())
			{
				categoria = categoriaOptional.get();
				categoria.setDescrizione((String) dati[4]);
				categoriaDao.save(categoria);
			}else {
	        categoria = new Categoria();
			categoria.setDescrizione((String) dati[4]);
			//dato che ho cascade type refresh lo salvo prima con categoriaDao.
			categoriaDao.save(categoria);
			}
			
		MultipartFile copertina = (MultipartFile) dati[5];
		libro.setTitolo((String) dati[0]);
		libro.setTrama((String) dati[1]);
		libro.setAutore(autore);
		libro.setCategoria(categoria);
		
		
		if(copertina != null && !copertina.isEmpty())
		{
			try {
				libro.setCopertina("data:image/png;base64," + Base64Utils.encodeToString(copertina.getBytes()));
			} catch (IOException e) {
				// TODO forse un cookie
				e.printStackTrace();
			}
		}
		libroDao.save(libro);
	}

	@Override
	public void eliminaLibro(Libro libro) {
		libro.getAutore().getLibri().remove(libro);
		libro.getCategoria().getLibri().remove(libro);
		libroDao.delete(libro);
	}

	@Override
	public List<Libro> getLibri() {
		
		return (List<Libro>) libroDao.findAll();
	}

	@Override
	public Libro getLibroById(int id) {
		
		return libroDao.findById(id).get();
	}

	//questo metodo mi serve perchè altrimenti non mi farebbe fare l'update
	@Override
	public void modificaLibro(Libro libro, Object...dati) {
				
		
		Autore autore = null;
		
		Categoria categoria = null;
		
		//tutto il codice sottostante con gli optional mi serve per controllare se nel db è già presente o meno un autore del libro che modifico
		//se non lo facessi a ogni modifica mi creerebbe anche un nuovo autore o categoria anche se già presenti nel db
		Optional<Autore> autoreOptional = Optional.ofNullable(autoreDao.findByNomeAndCognome((String) dati[2], (String) dati[3]));
	    if (autoreOptional.isPresent())
	    {
	        autore = autoreOptional.get();
	    	autore.setNome((String) dati[2]);
	    	autore.setCognome((String) dati[3]);
	    	autoreDao.save(autore);
	    }
	    else {
	        autore = new Autore();
	        autore.setNome((String) dati[2]);
	        autore.setCognome((String) dati[3]);
	        autoreDao.save(autore);
	    }
		
	    Optional<Categoria> categoriaOptional = Optional.ofNullable(categoriaDao.findByDescrizione((String) dati[4]));
		if(categoriaOptional.isPresent())
		{
			categoria = categoriaOptional.get();
			categoria.setDescrizione((String) dati[4]);
			categoriaDao.save(categoria);
		}
		else {
			categoria = new Categoria();
			categoria.setDescrizione((String) dati[4]);
			categoriaDao.save(categoria);
		}
		
		
		MultipartFile copertina = (MultipartFile) dati[5];
		libro.setTitolo((String) dati[0]);
		libro.setTrama((String) dati[1]);
		libro.setAutore(autore);
		libro.setCategoria(categoria);
		
		
		if(copertina != null && !copertina.isEmpty())
		{
			try {
				libro.setCopertina("data:image/png;base64," + Base64Utils.encodeToString(copertina.getBytes()));
			} catch (IOException e) {
				// TODO forse un cookie
				e.printStackTrace();
			}
		}
		libroDao.save(libro);

	}

	@Override
	public List<Libro> getLibriByAutore(Autore autore) {
		
		return libroDao.findByAutore(autore);
	}

}
