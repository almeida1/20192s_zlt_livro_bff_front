package com.fatec.scel.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fatec.scel.model.Livro;
import com.fatec.scel.servico.Servico;

@RestController
@RequestMapping(path = "/livros")
public class LivroController {

	private static final Logger logger = LoggerFactory.getLogger(LivroController.class);
	@Autowired
	Servico servico;

	/**
	 * quando o usuario digita localhost:8080/livros/cadastrar
	 * 
	 * @param livro
	 * @return o html /CadastraLivro
	 */
	@GetMapping("/cadastrar")
	public ModelAndView cadastraLivro(Livro livro) {

		ModelAndView mv = new ModelAndView("CadastrarLivro");
		mv.addObject("livro", livro);

		return mv;
	}

	@PostMapping("/save")
	public ModelAndView save(@Valid Livro livro, BindingResult result) {
		ModelAndView mv = new ModelAndView("CadastrarLivro");
		if (result.hasErrors()) {
			mv.addObject("fail","Dados inválidos"); //quando fail nao eh nulo a msg aparece na tela
			return mv;
		}
		try {
			Livro jaExiste=null;
			jaExiste = servico.consultaPorISBN(livro.getIsbn());
			if (jaExiste == null) {
				servico.save(livro);
				mv.addObject("success","Livro cadastrado com sucesso"); //quando success nao eh nulo
				return mv;
			} else {
				mv.addObject("fail","Livro já cadastrado."); //quando fail nao eh nulo a msg aparece na tela
				return mv;
			}
		} catch (RestClientException e) {
			mv.addObject("fail","erro ===> " +e.getMessage());
			return mv; 
		}

	}
}
