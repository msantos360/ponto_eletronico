package br.com.msantos.ponto.models;

public interface EstadoAtualPonto {
	
	void inicioJornada(PontoEletronico pontoEletronico);
	
	void inicioIntervalo(PontoEletronico pontoEletronico);
	
	void terminoIntervalo(PontoEletronico pontoEletronico);
	
	void terminoJornada(PontoEletronico pontoEletronico);
	
	
}
