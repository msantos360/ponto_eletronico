package br.com.msantos.ponto.models;

public interface EstadoAtualPonto {
	
	public void inicioJornada(PontoEletronico pontoEletronico);
	
	public void inicioIntervalo(PontoEletronico pontoEletronico);
	
	public void terminoIntervalo(PontoEletronico pontoEletronico);
	
	public void terminoJornada(PontoEletronico pontoEletronico);
	
	
}
