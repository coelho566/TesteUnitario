package br.ce.wcaquino.builders;

import br.ce.wcaquino.entidades.Filme;

public class FilmeBuilder {
	
	private Filme filme;
	
	private FilmeBuilder() {
		
	}
	
	public static FilmeBuilder umFilme() {
		FilmeBuilder builder = new FilmeBuilder();
		builder.filme = new Filme();
		builder.filme.setNome("Filme1");
		builder.filme.setEstoque(2);
		builder.filme.setPrecoLocacao(5.0);
		
		return builder;
	}
	
	public Filme agora() {
		return filme;
	}
}
