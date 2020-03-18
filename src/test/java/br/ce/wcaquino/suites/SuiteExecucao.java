package br.ce.wcaquino.suites;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.ce.wcaquino.servicos.CalculadoraTeste;
import br.ce.wcaquino.servicos.CalculoValorLocacaoTest;
import br.ce.wcaquino.servicos.LocacaoServiceTeste;

@RunWith(Suite.class)
@SuiteClasses({
	CalculadoraTeste.class,
	CalculoValorLocacaoTest.class,
	LocacaoServiceTeste.class
})
public class SuiteExecucao {
	
	
}
