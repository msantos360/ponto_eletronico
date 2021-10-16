package br.com.msantos.ponto.rn;

import java.time.Duration;
import java.time.LocalTime;

import br.com.msantos.ponto.models.PontoEletronico;

public class AtrasoIntervalo implements ValidadorDuracaoPonto {

	@Override
	public Duration calcula(PontoEletronico ponto) {
		
		LocalTime inicioIntervaloReal = ponto.getInicioIntervalo().toLocalTime();
		LocalTime terminoIntervaloReal = ponto.getTerminoIntervalo().toLocalTime();
		
		Duration tempoIntervaloReal = Duration.between(inicioIntervaloReal, terminoIntervaloReal);
		
		Duration tempoIntervaloEstipulado = ponto.getFuncionario().getJornadaTrabalho().getTempoIntervalo();
		
		return tempoIntervaloEstipulado.minus(tempoIntervaloReal);		
	}

}
