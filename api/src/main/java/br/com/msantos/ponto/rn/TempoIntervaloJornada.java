package br.com.msantos.ponto.rn;

import java.time.Duration;

import br.com.msantos.ponto.models.JornadaTrabalho;

public class TempoIntervaloJornada implements ValidadorDuracaoJornadaTrabalho {

	@Override
	public Duration calcula(JornadaTrabalho jornadaTrabalho) {
			
		if (jornadaTrabalho.getCargaHorariaSemanal().toHours() <= 20) {
			return Duration.ZERO;

		} else if (jornadaTrabalho.getCargaHorariaSemanal().toHours() <= 36) {
			return Duration.ofMinutes(15);

		} else {
			return jornadaTrabalho.getTempoIntervalo();
		}
	}

}
