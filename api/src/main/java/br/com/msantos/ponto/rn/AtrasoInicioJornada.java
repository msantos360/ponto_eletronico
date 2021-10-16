package br.com.msantos.ponto.rn;

import java.time.Duration;
import java.time.LocalTime;

import br.com.msantos.ponto.models.PontoEletronico;

public class AtrasoInicioJornada implements ValidadorDuracaoPonto {

	@Override
	public Duration calcula(PontoEletronico ponto) {

		LocalTime inicioJornadaEstipulada = ponto.getFuncionario().getJornadaTrabalho().getInicioJornada();
		LocalTime inicioJornadaReal = ponto.getInicioJornada().toLocalTime();
		return Duration.between(inicioJornadaReal, inicioJornadaEstipulada);
	}

}
