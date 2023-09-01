package it.corso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.service.LibroService;

@Controller
@RequestMapping("/dettaglio")
public class DettaglioController {

	@Autowired
	LibroService libroService;
	
	@GetMapping
	public String getPageDettaglio(@RequestParam(name = "id")int id, Model model) {
		model.addAttribute("libro", libroService.getLibroById(id));
		return "dettaglio";
	}
}
