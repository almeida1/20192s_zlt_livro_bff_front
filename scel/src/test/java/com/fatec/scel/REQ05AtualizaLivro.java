package com.fatec.scel;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.fatec.scel.controller.LivroController;
import com.fatec.scel.model.Livro;
import com.fatec.scel.servico.Servico;
@RunWith(SpringRunner.class)
@SpringBootTest
class REQ05AtualizaLivro {
    @Autowired
	LivroController livroController;
	@Autowired
	Servico servico;

	@Test
	public void CT01AtualizaLivroComSucesso() {
		//quando
		Livro livro = new Livro("1111","Nova Engenharia de Software","Roger Pressman");
		
		servico.updateLivro(livro);
		Livro ro = servico.consultaPorISBN(livro.getIsbn());
		assertThat(ro.getTitulo(), equalTo("Nova Engenharia de Software"));
	}

}
