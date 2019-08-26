package br.com.msantos.ponto.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EstadoTerminoJornada implements EstadoAtualPonto {

	@Override
	public void inicioJornada(PontoEletronico pontoEletronico) {
		
		throw new RuntimeException("Não pode volvar ao inicio da jornada");
	}

	@Override
	public void inicioIntervalo(PontoEletronico pontoEletronico) {
		
		throw new RuntimeException("Não pode volvar ao inicio do intervalo");
	}

	@Override
	public void terminoIntervalo(PontoEletronico pontoEletronico) {
		
		throw new RuntimeException("Não pode volvar ao termino do intervalo");
	}

	@Override
	public void terminoJornada(PontoEletronico pontoEletronico) {
		System.out.println("Termino de espediente");
		pontoEletronico.estado = new EstadoTerminoJornada();
//		pontoEletronico.terminoJornada = LocalDateTime.now();
		
		LocalDate date = LocalDate.now();
		pontoEletronico.terminoJornada = LocalDateTime.of(date , LocalTime.of(16, 30));
	}

}
