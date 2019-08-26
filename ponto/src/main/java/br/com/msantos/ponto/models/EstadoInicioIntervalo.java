package br.com.msantos.ponto.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EstadoInicioIntervalo implements EstadoAtualPonto {
	
	@Override
	public void inicioJornada(PontoEletronico pontoEletronico) {
		
		throw new RuntimeException("Funcionário deve terminar a jornada 1");
	}

	@Override
	public void inicioIntervalo(PontoEletronico pontoEletronico) {
		System.out.println("Saindo para o intervalo");
		pontoEletronico.estado = new EstadoTerminoIntervalo();
//		pontoEletronico.inicioIntervalo = LocalDateTime.now();
		
		LocalDate date = LocalDate.now();
		pontoEletronico.inicioIntervalo = LocalDateTime.of(date , LocalTime.of(12, 30));
	}

	@Override
	public void terminoIntervalo(PontoEletronico pontoEletronico) {
		
		throw new RuntimeException("Funcionário deve terminar o intervalo 3");
	}

	@Override
	public void terminoJornada(PontoEletronico pontoEletronico) {
		
		throw new RuntimeException("Funcionário deve terminar o intervalo 4");
	}

}
