package br.com.msantos.ponto.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EstadoTerminoIntervalo implements EstadoAtualPonto {

	@Override
	public void inicioJornada(PontoEletronico pontoEletronico) {
		
		throw new RuntimeException("Não pode voltar ao inicio da jornada");
	}

	@Override
	public void inicioIntervalo(PontoEletronico pontoEletronico) {
		
		throw new RuntimeException("Não pode voltar ao inicio do intervalo");
	}

	@Override
	public void terminoIntervalo(PontoEletronico pontoEletronico) {
		
		System.out.println("Voltando do intervalo");
		pontoEletronico.estado = new EstadoTerminoJornada();
//		pontoEletronico.terminoIntervalo = LocalDateTime.now();
		
		LocalDate date = LocalDate.now();
		pontoEletronico.terminoIntervalo = LocalDateTime.of(date , LocalTime.of(13, 30));
	}

	@Override
	public void terminoJornada(PontoEletronico pontoEletronico) {
		
		throw new RuntimeException("Não pode voltar ao inicio do intervalo");
	}

}
