package br.com.msantos.ponto.models;

import java.time.LocalDateTime;

import br.com.msantos.ponto.integracoes.Email;

public class EstadoTerminoJornada implements EstadoAtualPonto {

	private String mensagem = "Funcionário deve terminar a jornada de trabalho.";
	
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
		
		throw new RuntimeException(mensagem);
	}

	@Override
	public void terminoJornada(PontoEletronico pontoEletronico) {
		pontoEletronico.terminoJornada = LocalDateTime.now();
		pontoEletronico.estado = new EstadoTerminoJornada();
		
//		LocalDate date = LocalDate.now();
//		pontoEletronico.terminoJornada = LocalDateTime.of(date , LocalTime.of(16, 30));
		
		//Encerra a jornada de trabalho
		pontoEletronico.setDiaEncerrado(true);
		new Email().executa(pontoEletronico);
	}

}
