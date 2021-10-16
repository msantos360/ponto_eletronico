package br.com.msantos.ponto.rn;

import java.time.Duration;
import java.time.LocalTime;

import br.com.msantos.ponto.models.PontoEletronico;

public class SaidaAntecipada implements ValidadorDuracaoPonto {

	@Override
	public Duration calcula(PontoEletronico ponto) {

		LocalTime saidaAntecipadaReal = ponto.getTerminoJornada().toLocalTime();

		LocalTime saidaEstipulada = ponto.getFuncionario().getJornadaTrabalho().getTerminoJornada();

		return Duration.between(saidaEstipulada, saidaAntecipadaReal);
	}

}
