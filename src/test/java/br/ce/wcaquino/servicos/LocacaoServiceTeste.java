package br.ce.wcaquino.servicos;

import static br.ce.wcaquino.builders.FilmeBuilder.umFilme;
import static br.ce.wcaquino.builders.FilmeBuilder.umFilmeSemEstoque;
import static br.ce.wcaquino.builders.UsuarioBuilder.umUsuario;
import static br.ce.wcaquino.matchers.DataDiferencaDiasMatcher.ehHoje;
import static br.ce.wcaquino.matchers.DataDiferencaDiasMatcher.ehHojeComDiferencaDias;
import static br.ce.wcaquino.matchers.MatchersProprios.caiEm;
import static br.ce.wcaquino.matchers.MatchersProprios.caiNumaSegunda;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Assume;
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
import buildermaster.BuilderMaster;

public class LocacaoServiceTeste  {
	
	@Rule
	public ErrorCollector error = new ErrorCollector();
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	private LocacaoService service;
	
	@Before
	public void setup() {
		service = new LocacaoService();
	}
	
	
	@Test
	public void deveAlugarFilme() throws Exception{
		
		Assume.assumeFalse(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
		
		Usuario usuario = umUsuario().agora();
		List<Filme> filme = Arrays.asList(umFilme().comValor(5.0).agora());
		
		System.out.println("Teste!");
		
		Locacao locacao = service.alugarFilme(usuario, filme);
		
		error.checkThat(locacao.getValor(), is(equalTo(5.0)));
		error.checkThat(locacao.getDataLocacao(), ehHoje());
		error.checkThat(locacao.getDataRetorno(), ehHojeComDiferencaDias(1));
	}
	
	@Test(expected = FilmeSemEstoqueException.class)
	public void naoDeveAlugarFilmeSemEstoque() throws Exception {
		
		//cenario
		Usuario usuario = umUsuario().agora();
		List<Filme> filme = Arrays.asList(umFilmeSemEstoque().agora());
		
		//acao
		service.alugarFilme(usuario, filme);
	
	}
	
	@Test
	public void naoDeveAlugarFilmeSemUsuario() throws FilmeSemEstoqueException {
	
		List<Filme> filme = Arrays.asList(umFilme().agora());
		
		
		try {
			service.alugarFilme(null, filme);
			Assert.fail();
		} catch (LocadoraException e) {
			assertThat(e.getMessage(), is("Usuario vazio"));
		}
	}
	
	@Test
	public void naoDeveAlugarFilmeSemFilme() throws FilmeSemEstoqueException, LocadoraException {

		Usuario usuario = umUsuario().agora();
		
		exception.expect(LocadoraException.class);
		exception.expectMessage("Filme vazio");
		
		service.alugarFilme(usuario, null);
	}
	
	
	@Test
	public void naoDeveDevolverFilmeNoDomingo() throws FilmeSemEstoqueException, LocadoraException {
		Assume.assumeTrue(DataUtils.verificarDiaSemana(new Date(), Calendar.SATURDAY));
		
		Usuario usuario = umUsuario().agora();
		List<Filme> filmes = Arrays.asList(umFilme().agora());
		
		Locacao retorno = service.alugarFilme(usuario, filmes);
		
		assertThat(retorno.getDataRetorno(), caiEm(Calendar.SUNDAY));
		assertThat(retorno.getDataRetorno(), caiNumaSegunda());
		
	}
	
	public static void main(String[] args) {
		new BuilderMaster().gerarCodigoClasse(Locacao.class);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
