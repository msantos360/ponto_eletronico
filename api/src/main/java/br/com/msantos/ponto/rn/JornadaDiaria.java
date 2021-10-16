package br.com.msantos.ponto.rn;

import java.time.Duration;

import br.com.msantos.ponto.models.PontoEletronico;

public class JornadaDiaria implements ValidadorDuracaoPonto {

	@Override
	public Duration calcula(PontoEletronico ponto) {
		return Duration.between(ponto.getInicioJornada(), ponto.getTerminoJornada());
	}

}
