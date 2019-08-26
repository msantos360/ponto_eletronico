package br.com.msantos.ponto.models;

import java.time.Duration;
import java.time.LocalTime;

public class JornadaTrabalhoBuilder {

	private LocalTime inicioJornada;
	
	private LocalTime terminoJornada;
	
	private Duration tempoIntervalo = Duration.ofMinutes(0);

	private TipoTrabalhador tipoTrabalhador;

	private Duration cargaHorariaSemanal;

	private boolean trabalhaSabado = false;

	private boolean trabalhaDomingo = false;

	private boolean trabalhaFeriado = false;
	
	public JornadaTrabalhoBuilder comInicioJornada(int hora, int minuto) {
		this.inicioJornada = LocalTime.of(hora, minuto);
		return this;
	}
	
	public JornadaTrabalhoBuilder comTerminoJornada(int hora, int minuto) {
		this.terminoJornada = LocalTime.of(hora, minuto);
		return this;
	}

	public JornadaTrabalhoBuilder comIntervaloOptional(long tempoIntervaloEmMinutos) {
		this.tempoIntervalo = Duration.ofMinutes(tempoIntervaloEmMinutos);
		return this;
	}

	public JornadaTrabalhoBuilder comTipoTrabalhador(TipoTrabalhador tipoTrabalhador) {
		this.tipoTrabalhador = tipoTrabalhador;
		return this;
	}

	public JornadaTrabalhoBuilder comCargaHorariaSemanal(long cargaHorariaSemanalEmHoras) {
		this.cargaHorariaSemanal = Duration.ofHours(cargaHorariaSemanalEmHoras);
		return this;
	}

	public JornadaTrabalhoBuilder comSabado() {
		this.trabalhaSabado = true;
		return this;
	}

	public JornadaTrabalhoBuilder comDomingo() {
		this.trabalhaDomingo = true;
		return this;
	}

	public JornadaTrabalhoBuilder comFeriado() {
		this.trabalhaFeriado = true;
		return this;
	}

	public JornadaTrabalho builder() {
		return new JornadaTrabalho(tipoTrabalhador, inicioJornada, terminoJornada, cargaHorariaSemanal,
				tempoIntervalo,	trabalhaSabado, trabalhaDomingo, trabalhaFeriado);
	}

}
