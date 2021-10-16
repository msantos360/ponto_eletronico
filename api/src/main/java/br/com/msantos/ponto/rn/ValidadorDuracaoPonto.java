package br.com.msantos.ponto.rn;

import java.time.Duration;

import br.com.msantos.ponto.models.PontoEletronico;

public interface ValidadorDuracaoPonto {

	Duration calcula(PontoEletronico ponto);
	
}
