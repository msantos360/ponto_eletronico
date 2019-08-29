package br.com.msantos.ponto.integracoes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import br.com.msantos.ponto.models.PontoEletronico;

public class EmailConfiguration {

	private Boolean emailEnviado = false;
	
	private LocalDateTime dataEhoraAtual = LocalDateTime.now();

	private DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
	
	private DateTimeFormatter dataFormatadaDia = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private String emailRemetente = "mariacicera.hope123@gmail.com";

	private String senhaEmail = "Cicera#1702";

	public Boolean getEmailEnviado() {
		return emailEnviado;
	}
	
	public String getDataEhoraAtual() {
		return dataEhoraAtual.format(dataFormatada);
	}
	
	public void enviar(PontoEletronico ponto) {

		try {
			Email email = new SimpleEmail();
			email.setHostName("smtp.googlemail.com");
			email.setSmtpPort(465);
			email.setAuthenticator(new DefaultAuthenticator(emailRemetente, senhaEmail));
			email.setSSLOnConnect(true);

			email.setFrom(emailRemetente);
			email.setSubject("MARCARÇÃO DE PONTO " + dataEhoraAtual.format(dataFormatadaDia));
			email.setMsg("Olá " + ponto.getFuncionario().getNome() + "!\n\n"
						+ "Sua marcação de ponto foi efetuada com sucesso na data e horários abaixo:\n\n"
						+ "\tInício jornada: " + ponto.getInicioJornada().format(dataFormatada) + "\n"
						+ "\tInício intervalo: " + ponto.getInicioIntervalo().format(dataFormatada) + "\n"
						+ "\tTérmino intervalo: " + ponto.getTerminoIntervalo().format(dataFormatada) + "\n"
						+ "\tTérmino jornada: " + getDataEhoraAtual()
						+"\n\nTRANSAÇÃO: " + ponto.getTransacaoId());
			
			email.addTo(ponto.getFuncionario().getEmail());
			email.send();
			emailEnviado = true;
			System.out.println("Email enviado para: " + ponto.getFuncionario().getEmail());

		} catch (EmailException e) {
			e.printStackTrace();
		}

	}

}
