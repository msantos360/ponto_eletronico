package br.com.msantos.ponto.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EstadoTerminoIntervalo implements EstadoAtualPonto {

	private String mensagem = "Funcionário deve terminar o intervalo.";
	
	@Override
	public void inicioJornada(PontoEletronico pontoEletronico) {
		
		throw new RuntimeException(mensagem);
	}

	@Override
	public void inicioIntervalo(PontoEletronico pontoEletronico) {
		
		throw new RuntimeException(mensagem);
	}

	@Override
	public void terminoIntervalo(PontoEletronico pontoEletronico) {
		
//		pontoEletronico.terminoIntervalo = LocalDateTime.now();
		pontoEletronico.estado = new EstadoTerminoJornada();
		
		LocalDate date = LocalDate.now();
		pontoEletronico.terminoIntervalo = LocalDateTime.of(date , LocalTime.of(13, 30));
	}

	@Override
	public void terminoJornada(PontoEletronico pontoEletronico) {
		
		throw new RuntimeException(mensagem);
	}

}
