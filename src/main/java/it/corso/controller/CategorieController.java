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

import it.corso.model.Categoria;
import it.corso.service.CategoriaService;

@Controller
@RequestMapping("/categorie")
public class CategorieController {
	
	@Autowired
	CategoriaService categoriaService;
	
	@GetMapping
	public String getPagecategorie (Model model, @RequestParam(name = "id", required = false)Integer id) {
		
		Categoria categoria = id == null ? new Categoria() : categoriaService.getCategoriaById(id);
		
		model.addAttribute("categorie", categoriaService.getCategorie());
		model.addAttribute(categoria);
		
		return "categorie";
	}
	
	
	
	//è sempre presente un input type hidden ma dato che uso modelAttribute, Hibernate riesce a capire se è un update o create. Lo uso nel html per il js
	@PostMapping
	public String aggiungiCategoria(@Valid @ModelAttribute("categoria")Categoria categoria, BindingResult result){
		
		if(result.hasErrors())
			return "redirect:/categorie";
		categoriaService.aggiungiCategoria(categoria);
		return "redirect:/categorie";
	}
	

	
	@GetMapping("/elimina")
	public String eliminaCategoria(@RequestParam(name = "id")int id) {
		categoriaService.eliminaCategoria(categoriaService.getCategoriaById(id));
		return "redirect:/categorie";
	}
}
