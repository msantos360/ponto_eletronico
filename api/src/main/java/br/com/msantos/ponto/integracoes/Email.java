package br.com.msantos.ponto.integracoes;

import br.com.msantos.ponto.models.PontoEletronico;

public class Email implements Integracao {

	@Override
	public void executa(PontoEletronico ponto) {
		
		new EmailConfiguration().enviar(ponto);

	}

}
