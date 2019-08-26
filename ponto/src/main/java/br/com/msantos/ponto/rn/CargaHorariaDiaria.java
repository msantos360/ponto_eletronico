package br.com.msantos.ponto.rn;

import java.time.Duration;
import java.time.LocalTime;

import br.com.msantos.ponto.models.JornadaTrabalho;

public class CargaHorariaDiaria implements ValidadorDuracaoJornadaTrabalho {

	private String mensagem = "Carga horária não deve ser superior a 12h";

	@Override
	public Duration calcula(JornadaTrabalho jornadaTrabalho) {

		LocalTime inicioJornada = jornadaTrabalho.getInicioJornada();
		LocalTime terminoJornada = jornadaTrabalho.getTerminoJornada();

		Duration cargaHorariaMesmoDia = Duration.between(inicioJornada, terminoJornada);

		Duration duracaoAteMeiaNoite = Duration.between(inicioJornada, LocalTime.of(23, 59));
		Duration duracaoAteOTermino = Duration.between(LocalTime.MIDNIGHT, terminoJornada);

		if (cargaHorariaMesmoDia.isNegative()) {

			Duration cargaHorariaDiaSeguinte = duracaoAteMeiaNoite.plus(duracaoAteOTermino).plusMinutes(1);

			if (cargaHorariaDiaSeguinte.toMinutes() > 720) {
				throw new IllegalArgumentException(mensagem);
			}

			return cargaHorariaDiaSeguinte;
		}

		if (cargaHorariaMesmoDia.toMinutes() > 720) {
			throw new IllegalArgumentException(mensagem);
		}

		return cargaHorariaMesmoDia;
	}

}
