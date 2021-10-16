package br.com.msantos.ponto.rn;

import java.time.Duration;
import java.time.LocalTime;

import br.com.msantos.ponto.models.PontoEletronico;

public class HoraExtra implements ValidadorDuracaoPonto {

	@Override
	public Duration calcula(PontoEletronico ponto) {

		LocalTime terminoJornadaReal = ponto.getTerminoJornada().toLocalTime();

		LocalTime terminoJornadaEstipulada = ponto.getFuncionario().getJornadaTrabalho().getTerminoJornada();

		return Duration.between(terminoJornadaEstipulada, terminoJornadaReal);
	}

}
