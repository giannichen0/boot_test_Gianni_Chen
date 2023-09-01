package it.corso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.corso.service.LibroService;

@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	LibroService libroService;
	
	@GetMapping
	public String getPageIndex(Model model) {
		model.addAttribute("libri", libroService.getLibri());
		return "index";
	}
}
