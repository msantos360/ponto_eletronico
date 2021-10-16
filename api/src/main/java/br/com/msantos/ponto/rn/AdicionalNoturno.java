package br.com.msantos.ponto.rn;

import java.time.Duration;
import java.time.LocalTime;

import br.com.msantos.ponto.models.PontoEletronico;

public class AdicionalNoturno implements ValidadorDuracaoPonto {

	@Override
	public Duration calcula(PontoEletronico ponto) {

		LocalTime inicioAdicionalEstipulado = ponto.getFuncionario().getJornadaTrabalho().getInicioAdicional();

		LocalTime terminoJornadaReal = ponto.getTerminoJornada().toLocalTime();

		if (inicioAdicionalEstipulado.isBefore(terminoJornadaReal)) {
			return Duration.between(inicioAdicionalEstipulado, terminoJornadaReal);
		}
		
		return Duration.ZERO;
	}

}
