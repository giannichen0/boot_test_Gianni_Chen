package it.corso.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.corso.model.Libro;

import it.corso.service.LibroService;

@Controller
@RequestMapping("/libri")
public class LibriController {
	
	@Autowired
	LibroService libroService;
	
	
	@GetMapping
	public String getPageLibri(Model model, @RequestParam(name = "id", required = false)Integer id) {
		
		
		Libro libro = id == null ? new Libro() : libroService.getLibroById(id);
		
		
		model.addAttribute("libri", libroService.getLibri());
		model.addAttribute("libro", libro);
		return "libri";
	}
	
	
	@GetMapping("/elimina")
	public String eliminaLibro(@RequestParam(name = "id")int id) {
		
		libroService.eliminaLibro(libroService.getLibroById(id));
		return "redirect:/libri";
	}
	
	
	@PostMapping("/registra")
	public String registraLibro(@RequestParam(name = "titolo")String titolo,@RequestParam(name = "trama")String trama,
			@RequestParam(name="nomeAutore")String nome, @RequestParam(name = "cognomeAutore")String cognome,
			@RequestParam(name = "categoria")String categoria,
			@RequestParam(name = "copertina", required = false)MultipartFile copertina, @RequestParam(name = "libroId")int id) {
		
		//per poter eseguire l'update. senza non lo avrebbe fatto. ho predisposto un input hidden con il valore dell'id del lilbro da modificare
		if(id != 0) 
		{
			libroService.modificaLibro(libroService.getLibroById(id),titolo, trama, nome, cognome, categoria, copertina);
			
		}else {

			libroService.aggiungiLibro(titolo, trama, nome, cognome, categoria, copertina);
		}
		
		return "redirect:/libri";
	}
	
	
	
	
}
