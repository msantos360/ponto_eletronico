package br.com.msantos.ponto.rn;

import java.time.Duration;

import br.com.msantos.ponto.models.PontoEletronico;

public class JornadaDiariaComIntervalo implements ValidadorDuracaoPonto {

	@Override
	public Duration calcula(PontoEletronico ponto) {

		Duration intervaloDia = Duration.between(ponto.getInicioIntervalo(), ponto.getTerminoIntervalo());
		return Duration.between(ponto.getInicioJornada(), ponto.getTerminoJornada()).minus(intervaloDia);
	}

}
