package br.com.msantos.ponto.rn;

import java.time.Duration;

import br.com.msantos.ponto.models.JornadaTrabalho;

public class CalculadorDeJornadaTrabalho {

	public Duration realizaCalculo(JornadaTrabalho jornadaTrabalho, ValidadorDuracaoJornadaTrabalho vdjt) {
		
		return vdjt.calcula(jornadaTrabalho);
		
	}
}
