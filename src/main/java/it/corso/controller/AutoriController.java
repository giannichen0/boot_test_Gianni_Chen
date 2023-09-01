package it.corso.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.corso.model.Autore;
import it.corso.service.AutoreService;


@Controller
@RequestMapping("/autori")
public class AutoriController {

	@Autowired
	AutoreService autoreService;
	
	
	@GetMapping
	public String getPageAutori(Model model, @RequestParam(name = "id", required = false)Integer id) {
		
		Autore autore = id == null ? new Autore() : autoreService.getAutoreById(id);
		
		model.addAttribute("autori", autoreService.getAutori());
		model.addAttribute("autore", autore);
		return "autori";
	}
	
	
	
	@PostMapping
	public String registraAutore(@Valid @ModelAttribute(name = "autore")Autore autore, BindingResult result) {
		if(result.hasErrors())
			return "/autori";
			autoreService.aggiungiAutore(autore);
		return "redirect:/autori";
	}
	
	@GetMapping("/elimina")
	public String eliminaAutore(@RequestParam(name = "id")int id) {
		autoreService.eliminaAutore(autoreService.getAutoreById(id));
		return "redirect:/autori";
	}
}
