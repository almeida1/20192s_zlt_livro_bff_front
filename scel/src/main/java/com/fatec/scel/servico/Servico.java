package com.fatec.scel.servico;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fatec.scel.controller.LivroController;
import com.fatec.scel.model.Livro;

@Service
public class Servico {
	private static final Logger logger = LoggerFactory.getLogger(Servico.class);
	RestTemplate restTemplate = new RestTemplate();

	public void updateLivro(Livro livro) {
		logger.debug("Starting save Client!!!!");
		restTemplate.put("https://app-scel1.herokuapp.com/api/livros", livro, Livro.class);
	}

	public Livro consultaPorISBN(String isbn) {
		logger.debug("Starting save Client!!!!");
		ResponseEntity<Livro> responseEntity = restTemplate
				.getForEntity("https://app-scel1.herokuapp.com/api/livros/{isbn}", Livro.class, isbn);
		return responseEntity.getBody();
	}

	public Livro save() {
		logger.debug("Starting save Client!!!!");
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://app-scel1.herokuapp.com/api/livros/";

		// create headers
		HttpHeaders headers = new HttpHeaders();
		// set `content-type` header
		headers.setContentType(MediaType.APPLICATION_JSON);
		// set `accept` header
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		// create a map for post parameters
		Map<String, Object> map = new HashMap<>();
		map.put("userId", 1);
		map.put("title", "Introduction to Spring Boot");
		map.put("body", "Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications.");

		// build the request
		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers);

		// send POST request
		ResponseEntity<Livro> response = restTemplate.postForEntity(url, entity, Livro.class);

		// check response status code
		if (response.getStatusCode() == HttpStatus.CREATED) {
			// return response.getBody();
			return null;
		} else {
			return null;
		}

	}
}