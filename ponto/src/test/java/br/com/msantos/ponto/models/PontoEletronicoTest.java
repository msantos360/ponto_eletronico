package br.com.msantos.ponto.models;

import static org.junit.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.msantos.ponto.repository.FuncionarioRepository;
import br.com.msantos.ponto.repository.PontoEletronicoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PontoEletronicoTest {

	@Autowired
	private PontoEletronicoRepository pontoRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	private Funcionario paulo;
	
	private JornadaTrabalho jornadaTrabalho;

	@Before
	public void constroiFuncionario() {

		jornadaTrabalho = new JornadaTrabalhoBuilder().comInicioJornada(8, 00).comTerminoJornada(17, 00)
				.comTipoTrabalhador(TipoTrabalhador.RURAL).comCargaHorariaSemanal(44).comIntervaloOptional(60)
				.comSabado().builder();

		paulo = new FuncionarioBuilder().comCpf("43865969097").comJornadaDeTrabalho(jornadaTrabalho)
				.comNome("Paulo Lopes").comDataNascimento(20, 2, 2000).comSexo(Sexo.M).comTelefone("2659-0000")
				.comEmail("teste@teste.com").estaAtivo(true).builder();
	}

	@Test
	public void pontoEletronicoComPontoEletronicoBuider() {

		PontoEletronico pauloBuider = new PontoEletronicoBuilder().comFuncionario(paulo)
				.comInicioJornada(22, 07, 2019, 8, 0).comInicioIntervalo(22, 07, 2019, 12, 0)
				.comTerminoIntervalo(22, 07, 2019, 13, 0).comTerminoJornada(22, 07, 2019, 18, 0).buider();

		LocalDateTime inicioJornadaEsperado = LocalDateTime.of(2019, 7, 22, 8, 0);
		LocalDateTime inicioIntervaloEsperado = LocalDateTime.of(2019, 7, 22, 12, 0);
		LocalDateTime terminoIntervaloEsperado = LocalDateTime.of(2019, 7, 22, 13, 0);
		LocalDateTime terminoJornadaEsperado = LocalDateTime.of(2019, 7, 22, 18, 0);

		assertEquals(inicioJornadaEsperado, pauloBuider.getInicioJornada());
		assertEquals(inicioIntervaloEsperado, pauloBuider.getInicioIntervalo());
		assertEquals(terminoIntervaloEsperado, pauloBuider.getTerminoIntervalo());
		assertEquals(terminoJornadaEsperado, pauloBuider.getTerminoJornada());

	}

	@Test
	public void calculaPermanenciaEmHoras() {
		PontoEletronico pauloBuider = new PontoEletronicoBuilder().comFuncionario(paulo)
				.comInicioJornada(22, 07, 2019, 8, 0).comInicioIntervalo(22, 07, 2019, 12, 0)
				.comTerminoIntervalo(22, 07, 2019, 13, 0).comTerminoJornada(22, 07, 2019, 18, 0).buider();

		Duration permanenciaEsperada = Duration.ofHours(10);

		assertEquals(permanenciaEsperada, pauloBuider.tempoPermanenciaJornadaDiaria());
	}

	@Test
	public void calculaPermanenciaEmHorasMenosOintervalo() {
		PontoEletronico pauloBuider = new PontoEletronicoBuilder().comFuncionario(paulo)
				.comInicioJornada(22, 07, 2019, 8, 0).comInicioIntervalo(22, 07, 2019, 12, 0)
				.comTerminoIntervalo(22, 07, 2019, 13, 0).comTerminoJornada(22, 07, 2019, 18, 0).buider();

		Duration permanenciaEsperada = Duration.ofHours(9);

		assertEquals(permanenciaEsperada, pauloBuider.tempoPermanenciaComDescontoIntervalo());
	}

	@Test
	public void funcionarioEntrouAtrasadoEmRelacaoAoSeuHorario() {
		PontoEletronico pontoPaulo = new PontoEletronicoBuilder().comFuncionario(paulo)
				.comInicioJornada(22, 07, 2019, 8, 15).comInicioIntervalo(22, 07, 2019, 12, 0)
				.comTerminoIntervalo(22, 07, 2019, 13, 0).comTerminoJornada(22, 07, 2019, 18, 0).buider();

		Duration atrasoEsperado = Duration.ofMinutes(-15);

		assertEquals(atrasoEsperado, pontoPaulo.tempoDeAtrasadoInicioJornada());
	}

	@Test
	public void fezMaisTempoQueOpermitidoDeIntervalo() {
		PontoEletronico pontoPaulo = new PontoEletronicoBuilder().comFuncionario(paulo)
				.comInicioJornada(22, 07, 2019, 8, 00).comInicioIntervalo(22, 07, 2019, 12, 00)
				.comTerminoIntervalo(22, 07, 2019, 13, 30).comTerminoJornada(22, 07, 2019, 18, 00).buider();

		Duration tempoIntervaloEstimado = Duration.ofMinutes(-30);

		assertEquals(tempoIntervaloEstimado, pontoPaulo.tempoAtrasadoIntervalo());
	}

	@Test
	public void saiuMaisCedoQueOterminoDaJornadaEstipulado() {

		PontoEletronico pontoPaulo = new PontoEletronicoBuilder().comFuncionario(paulo)
				.comInicioJornada(22, 07, 2019, 8, 00).comInicioIntervalo(22, 07, 2019, 12, 00)
				.comTerminoIntervalo(22, 07, 2019, 13, 00).comTerminoJornada(22, 07, 2019, 16, 30).buider();

		Duration tempoAntecipadoEsperado = Duration.ofMinutes(-30);

		assertEquals(tempoAntecipadoEsperado, pontoPaulo.tempoSaidaAntecipada());
	}

	@Test
	public void calculaHoraExtra() {

		PontoEletronico pontoPaulo = new PontoEletronicoBuilder().comFuncionario(paulo)
				.comInicioJornada(22, 07, 2019, 8, 00).comInicioIntervalo(22, 07, 2019, 12, 00)
				.comTerminoIntervalo(22, 07, 2019, 13, 00).comTerminoJornada(22, 07, 2019, 18, 00).buider();

		Duration horaExtraEsperada = Duration.ofMinutes(60);

		assertEquals(horaExtraEsperada, pontoPaulo.tempoHoraExtra());
	}

	@Test
	public void fezAdicionalNoturno() {

		PontoEletronico pontoPaulo = new PontoEletronicoBuilder().comFuncionario(paulo)
				.comInicioJornada(22, 07, 2019, 8, 00).comInicioIntervalo(22, 07, 2019, 12, 00)
				.comTerminoIntervalo(22, 07, 2019, 13, 00).comTerminoJornada(22, 07, 2019, 22, 00).buider();

		Duration duracaoDoAdicionalEsperado = Duration.ofMinutes(60);

		assertEquals(duracaoDoAdicionalEsperado, pontoPaulo.tempoAdicionalNoturno());

	}

	@Test
	public void naoFezAdicionalNoturno() {

		PontoEletronico pontoPaulo = new PontoEletronicoBuilder().comFuncionario(paulo)
				.comInicioJornada(22, 07, 2019, 8, 00).comInicioIntervalo(22, 07, 2019, 12, 00)
				.comTerminoIntervalo(22, 07, 2019, 13, 00).comTerminoJornada(22, 07, 2019, 21, 00).buider();

		Duration duracaoDoAdicionalEsperado = Duration.ZERO;

		assertEquals(duracaoDoAdicionalEsperado, pontoPaulo.tempoAdicionalNoturno());
	}

	@Test
	public void deveGarantirIntervaloEntreDias() {

		PontoEletronico pontoPaulo = new PontoEletronicoBuilder().comFuncionario(paulo)
				.comInicioJornada(22, 07, 2019, 20, 00).comInicioIntervalo(22, 07, 2019, 23, 30)
				.comTerminoIntervalo(23, 07, 2019, 00, 30).comTerminoJornada(23, 07, 2019, 6, 00).buider();

		Duration duracaoIntervaloEsperado = Duration.ofHours(1);

		assertEquals(duracaoIntervaloEsperado, pontoPaulo.tempoIntervalo());
	}

	@Test
	public void fezMaisQue12horasDeTrabalho() {
		PontoEletronico pontoPaulo = new PontoEletronicoBuilder().comFuncionario(paulo)
				.comInicioJornada(22, 07, 2019, 20, 00).comInicioIntervalo(22, 07, 2019, 23, 30)
				.comTerminoIntervalo(23, 07, 2019, 00, 30).comTerminoJornada(23, 07, 2019, 9, 00).buider();

		Duration duracaoEsperada = Duration.ofHours(13);

		assertEquals(duracaoEsperada, pontoPaulo.tempoPermanenciaJornadaDiaria());
	}

	@Test
	public void validaEntradasESaidasDoFuncionarioComEstado() {

		PontoEletronico pontoPauloComEstado = new PontoEletronicoBuilder().comFuncionario(paulo).buider();

		pontoPauloComEstado.validaInicioJornada();
		pontoPauloComEstado.validaInicioIntervalo();
		pontoPauloComEstado.validaTerminoIntervalo();
		pontoPauloComEstado.validaTerminoJornada();

		LocalDate date = LocalDate.now();
		LocalDateTime inicioJornadaEsperada = LocalDateTime.of(date, LocalTime.of(8, 00));
		LocalDateTime inicioIntervaloEsperado = LocalDateTime.of(date, LocalTime.of(12, 30));
		LocalDateTime terminoIntervaloEsperado = LocalDateTime.of(date, LocalTime.of(13, 30));
		LocalDateTime terminoJornadaEsperada = LocalDateTime.of(date, LocalTime.of(16, 30));

		assertEquals(inicioJornadaEsperada, pontoPauloComEstado.getInicioJornada());
		assertEquals(inicioIntervaloEsperado, pontoPauloComEstado.getInicioIntervalo());
		assertEquals(terminoIntervaloEsperado, pontoPauloComEstado.getTerminoIntervalo());
		assertEquals(terminoJornadaEsperada, pontoPauloComEstado.getTerminoJornada());

	}
	
	//63873882108
	@Test
	public void selectPontoPorFuncionarioEporPeriodo() {
		
		LocalDateTime inicio = LocalDateTime.of(2019, 8, 1, 0, 0);
		LocalDateTime termino  = LocalDateTime.of(2019, 8, 28, 23, 59);
		
		PontoSelectBuilder psb = new PontoSelectBuilder().comFuncionarioRepository(funcionarioRepository)
		.comPontoRepository(pontoRepository)
		.comCPF("63873882108").comDataInicioEm(inicio).comDataTerminoEm(termino);
		
		Set<PontoEletronico> listaPonto = new PontoEletronico().pontoPorFuncionariosEperiodo(psb);
		
		for (PontoEletronico pontoEletronico : listaPonto) {
			System.out.println(pontoEletronico.getTerminoJornada());
		}

	}
	
	@Test
	public void selectPontoPorTransacaoId() {
		
		PontoEletronico transacao = pontoRepository.findByTransacaoId("T1567036928285");
		
		String transacaoEsperada = "T1567036928285";
		
		assertEquals(transacaoEsperada, transacao.getTransacaoId());
	}

	/*
	 * interjornada 11h
	 */

}
