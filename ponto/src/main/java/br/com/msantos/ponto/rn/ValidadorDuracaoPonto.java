package br.com.msantos.ponto.rn;

import java.time.Duration;

import br.com.msantos.ponto.models.PontoEletronico;

public interface ValidadorDuracaoPonto {

	public Duration calcula(PontoEletronico ponto);
	
}
