package br.com.msantos.ponto.integracoes;

import br.com.msantos.ponto.models.PontoEletronico;

public interface Integracao {

	void executa(PontoEletronico ponto);
	
}
