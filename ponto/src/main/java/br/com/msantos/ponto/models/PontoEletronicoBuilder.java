package br.com.msantos.ponto.models;

import java.time.LocalDateTime;

public class PontoEletronicoBuilder {

	private LocalDateTime inicioJornada;

	private LocalDateTime inicioIntervalo;

	private LocalDateTime terminoIntervalo;

	private LocalDateTime terminoJornada;

	private Funcionario funcionario;
	
	public PontoEletronicoBuilder comFuncionario(Funcionario funcionario) {
		
		this.funcionario = funcionario;
		return this;
	}

	public PontoEletronicoBuilder comInicioJornada(int dia, int mes, int ano, int hora, int minuto) {

		this.inicioJornada = LocalDateTime.of(ano, mes, dia, hora, minuto);
		return this;
	}

	public PontoEletronicoBuilder comInicioIntervalo(int dia, int mes, int ano, int hora, int minuto) {

		this.inicioIntervalo = LocalDateTime.of(ano, mes, dia, hora, minuto);
		return this;
	}

	public PontoEletronicoBuilder comTerminoIntervalo(int dia, int mes, int ano, int hora, int minuto) {

		this.terminoIntervalo = LocalDateTime.of(ano, mes, dia, hora, minuto);
		return this;
	}

	public PontoEletronicoBuilder comTerminoJornada(int dia, int mes, int ano, int hora, int minuto) {

		this.terminoJornada = LocalDateTime.of(ano, mes, dia, hora, minuto);
		return this;
	}
	
	public PontoEletronico buider() {
		return new PontoEletronico(funcionario, inicioJornada, inicioIntervalo, terminoIntervalo, terminoJornada);
	}
}
