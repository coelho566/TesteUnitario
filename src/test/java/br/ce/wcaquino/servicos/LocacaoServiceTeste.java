package br.ce.wcaquino.servicos;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.junit.rules.ExpectedException;

import br.ce.wcaquino.entidades.Filme;
import br.ce.wcaquino.entidades.Locacao;
import br.ce.wcaquino.entidades.Usuario;
import br.ce.wcaquino.excption.FilmeSemEstoqueException;
import br.ce.wcaquino.excption.LocadoraException;
import br.ce.wcaquino.utils.DataUtils;

public class LocacaoServiceTeste  {
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Before
	public void setup() {
		System.out.println("Before");
	}
	
	@After
	public void tearDown() {
		System.out.println("After");
	}
	
	public void testeLocacao() throws Exception{
		
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Usuario 1");
		List<Filme> filme = Arrays.asList(new Filme("Rei leão", 1, 5.0));
		
		System.out.println("Teste!");
		
		Locacao locacao = service.alugarFilme(usuario, filme);
		
		error.checkThat(locacao.getValor(), is(equalTo(5.0)));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataLocacao(), new Date()), is(true));
		error.checkThat(DataUtils.isMesmaData(locacao.getDataRetorno(), DataUtils.obterDataComDiferencaDias(1)), is(true));
	}
	
	@Test(expected = FilmeSemEstoqueException.class)
	public void test() throws Exception {
		
		//cenario
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Leandro");
		List<Filme> filme = Arrays.asList(new Filme("Rei leão", 0, 4.0));
		
		//acao
		service.alugarFilme(usuario, filme);
	
	}
	
	@Test
	public void testLocacao() throws FilmeSemEstoqueException {
		
		LocacaoService service = new LocacaoService(); 
		List<Filme> filme = Arrays.asList(new Filme("Rei leão", 1, 5.0));
		Usuario usuario = new Usuario("Pedro Rocha");
		
		try {
			service.alugarFilme(usuario, filme);
			Assert.fail();
		} catch (LocadoraException e) {
			assertThat(e.getMessage(), is("Usuario vazio"));
		}
	}
	
	@Test
	public void testLocacao_FilmeVazio() throws FilmeSemEstoqueException, LocadoraException {
		LocacaoService service = new LocacaoService();
		Usuario usuario = new Usuario("Carlos Mattos");
		
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		
		service.alugarFilme(usuario, null);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
