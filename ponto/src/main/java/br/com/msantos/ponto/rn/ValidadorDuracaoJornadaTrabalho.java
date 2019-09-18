package br.com.msantos.ponto.rn;

import java.time.Duration;

import br.com.msantos.ponto.models.JornadaTrabalho;

public interface ValidadorDuracaoJornadaTrabalho {

	Duration calcula(JornadaTrabalho jornadaTrabalho);
}
