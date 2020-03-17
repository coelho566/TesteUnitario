package br.ce.wcaquino.servicos;

import org.junit.Assert;
import org.junit.Test;


public class CalculadoraTeste {
	
	@Test
	public void deveSomarDoisValores() {
		
		int a = 5;
		int b = 3;
		
		Calculadora calc = new Calculadora();
		
		int resultado = calc.somar(a, b);
		
		Assert.assertEquals(4, resultado);
	}
	
}
