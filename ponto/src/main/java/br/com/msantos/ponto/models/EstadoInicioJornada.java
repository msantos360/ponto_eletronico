package br.com.msantos.ponto.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EstadoInicioJornada implements EstadoAtualPonto {
	
	@Override
	public void inicioJornada(PontoEletronico pontoEletronico) {
		System.out.println("Inicio Jornada ok");
//		pontoEletronico.inicioJornada = LocalDateTime.now();
		LocalDate date = LocalDate.now();
		pontoEletronico.inicioJornada = LocalDateTime.of(date , LocalTime.of(8, 00));
		pontoEletronico.estado = new EstadoInicioIntervalo();
	}

	@Override
	public void inicioIntervalo(PontoEletronico pontoEletronico) {
		
		throw new RuntimeException("Funcionário deve iniciar a jornada primeiro");
	}

	@Override
	public void terminoIntervalo(PontoEletronico pontoEletronico) {
		
		throw new RuntimeException("Funcionário deve iniciar a jornada primeiro");
	}

	@Override
	public void terminoJornada(PontoEletronico pontoEletronico) {
		
		throw new RuntimeException("Funcionário deve iniciar a jornada primeiro");
	}

}
