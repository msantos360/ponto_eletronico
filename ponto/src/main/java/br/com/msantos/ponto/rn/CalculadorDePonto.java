package br.com.msantos.ponto.rn;

import java.time.Duration;

import br.com.msantos.ponto.models.PontoEletronico;

public class CalculadorDePonto {

	public Duration realizaAcao(PontoEletronico pontoEletronico, ValidadorDuracaoPonto pontoValidator) {

		return pontoValidator.calcula(pontoEletronico);

	}
}
