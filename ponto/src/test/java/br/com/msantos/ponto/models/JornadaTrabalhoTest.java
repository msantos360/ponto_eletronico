package br.com.msantos.ponto.models;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

public class JornadaTrabalhoTest {
	
	private JornadaTrabalho jornadaUrbana = null;
	
	private JornadaTrabalho jornadaRural = null;
	
	private JornadaTrabalho jornadaPecuaria = null;

	@Before
	public void constroiJornadasDeTrabalhos(){
		
		jornadaUrbana = new JornadaTrabalhoBuilder()
			.comTipoTrabalhador(TipoTrabalhador.URBANO)
			.comCargaHorariaSemanal(44)
			.comIntervaloOptional(60)
			.comInicioJornada(8, 00)
			.comTerminoJornada(18, 00)
			.builder();
	
		jornadaRural = new JornadaTrabalhoBuilder()
			.comTipoTrabalhador(TipoTrabalhador.RURAL)
			.comCargaHorariaSemanal(44)
			.comIntervaloOptional(60)
			.comInicioJornada(8, 00)
			.comTerminoJornada(18, 00)
			.builder();
		
		jornadaPecuaria = new JornadaTrabalhoBuilder()
			.comTipoTrabalhador(TipoTrabalhador.PECUARIO)
			.comCargaHorariaSemanal(44)
			.comInicioJornada(8, 00)
			.comTerminoJornada(18, 00)
//			.comIntervaloOptional(60)
			.builder();
		
	}

	@Test
	public void determinaHorarioAdicionalPorTipoDeTrabalhador() {
		
		
		LocalTime inicioAdicionalUrbanoEsperado = LocalTime.of(22, 00);
		LocalTime terminoAdicionalUrbanoEsperado = LocalTime.of(5, 00);
		
		assertEquals(inicioAdicionalUrbanoEsperado, jornadaUrbana.getInicioAdicional());
		assertEquals(terminoAdicionalUrbanoEsperado, jornadaUrbana.getTerminoAdicional());
		
		
		LocalTime inicioAdicionalRuralEsperado = LocalTime.of(21, 00);
		LocalTime terminoAdicionalRuralEsperado = LocalTime.of(5, 00);
		
		assertEquals(inicioAdicionalRuralEsperado, jornadaRural.getInicioAdicional());
		assertEquals(terminoAdicionalRuralEsperado, jornadaRural.getTerminoAdicional());
		
		
		LocalTime inicioAdicionalPecuarioEsperado = LocalTime.of(20, 00);
		LocalTime terminoAdicionalPecuarioEsperado = LocalTime.of(4, 00);
		
		assertEquals(inicioAdicionalPecuarioEsperado, jornadaPecuaria.getInicioAdicional());
		assertEquals(terminoAdicionalPecuarioEsperado, jornadaPecuaria.getTerminoAdicional());

	}
	
	@Test(expected = IllegalArgumentException.class)
	public void jornadaDiariaNaoDeveUltrapassar12horas_MesmoDia() {
		
		JornadaTrabalho jornadaPaulo = new JornadaTrabalhoBuilder()
			.comTipoTrabalhador(TipoTrabalhador.URBANO)
			.comCargaHorariaSemanal(44)
			.comInicioJornada(8, 00)
			.comTerminoJornada(21, 00)
			.comIntervaloOptional(80)
			.comSabado()
			.builder();
		
		Duration cargahorariaEsperada = Duration.ofHours(13);
		
		assertEquals(cargahorariaEsperada, jornadaPaulo.validaCargaHoraria());
		
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void jornadaDiariaNaoDeveUltrapassar12horas_DiaSeguinte() {
		
		JornadaTrabalho jornadaPaulo = new JornadaTrabalhoBuilder()
			.comTipoTrabalhador(TipoTrabalhador.URBANO)
			.comCargaHorariaSemanal(44)
			.comInicioJornada(20, 00)
			.comTerminoJornada(8, 30)
			.comIntervaloOptional(80)
			.comSabado()
			.builder();
		
		Duration cargahorariaEsperada = Duration.ofMinutes(30);
		
		assertEquals(cargahorariaEsperada, jornadaPaulo.validaCargaHoraria());
	}
	

	
}
