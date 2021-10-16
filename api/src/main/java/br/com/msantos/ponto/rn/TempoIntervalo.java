package br.com.msantos.ponto.rn;

import java.time.Duration;

import br.com.msantos.ponto.models.PontoEletronico;

public class TempoIntervalo implements ValidadorDuracaoPonto {

	@Override
	public Duration calcula(PontoEletronico ponto) {
		
		return Duration.between(ponto.getInicioIntervalo(), ponto.getTerminoIntervalo());
	}

}
