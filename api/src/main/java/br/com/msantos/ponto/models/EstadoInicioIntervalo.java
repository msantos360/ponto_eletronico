package br.com.msantos.ponto.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class EstadoInicioIntervalo implements EstadoAtualPonto {
	
	private String mensagem = "Funcionário deve iniciar o intervalo";
	
	@Override
	public void inicioJornada(PontoEletronico pontoEletronico) {
		
		throw new RuntimeException(mensagem);
	}

	@Override
	public void inicioIntervalo(PontoEletronico pontoEletronico) {
//		pontoEletronico.inicioIntervalo = LocalDateTime.now();
		pontoEletronico.estado = new EstadoTerminoIntervalo();
		
		LocalDate date = LocalDate.now();
		pontoEletronico.inicioIntervalo = LocalDateTime.of(date , LocalTime.of(12, 30));
	}

	@Override
	public void terminoIntervalo(PontoEletronico pontoEletronico) {
		
		throw new RuntimeException(mensagem);
	}

	@Override
	public void terminoJornada(PontoEletronico pontoEletronico) {
		
		throw new RuntimeException(mensagem);
	}

}
