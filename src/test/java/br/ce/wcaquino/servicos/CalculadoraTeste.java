package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.ce.wcaquino.excption.NaoPodeDividirPorZeroException;


public class CalculadoraTeste {
	
	private Calculadora calc;
	
	@Before
	public void calculadora() {
		calc = new Calculadora();
	}
	
	
	@Test
	public void deveSomarDoisValores() {
		
		int a = 5;
		int b = 3;
		
		
		int resultado = calc.somar(a, b);
		
		Assert.assertEquals(8, resultado);
	}
	
	@Test
	public void deveSubstrairDoisValores() {
		int a = 8;
		int b = 5;
		
		int resultado = calc.subtrair(a, b);
		Assert.assertEquals(3, resultado);
	}
	
	@Test
	public void deveDividirDoisValores() throws NaoPodeDividirPorZeroException {
		int a = 10;
		int b = 2;
		
		int resultado = calc.dividir(a, b);
		Assert.assertEquals(5, resultado);
		
	}
	
	@Test(expected = NaoPodeDividirPorZeroException.class)
	public void deveLancarExcecaoAoDividirPorZero() throws NaoPodeDividirPorZeroException {
		
		int a = 10;
		int b = 0;
		
		calc.dividir(a, b);
		
	}
	
	@Test
	public void deveDividir() {
		String a = "6";
		String b = "3";
		
		int resultado = calc.divide(a, b);
		
		Assert.assertEquals(2, resultado);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
