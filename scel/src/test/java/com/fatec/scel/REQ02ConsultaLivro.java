package com.fatec.scel;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fatec.scel.model.Livro;
import com.fatec.scel.servico.Servico;

class REQ02ConsultaLivro {

	@Autowired
	Servico servico;

	@Test
	public void CT01ConsultaLivroComSucesso() {
		//quando
		Livro livro = new Livro("1111","Nova Engenharia de Software","Roger Pressman");
		Livro ro = servico.consultaPorISBN(livro.getIsbn());
		assertThat(ro.getTitulo(), equalTo("Nova Engenharia de Software"));
	}

}
