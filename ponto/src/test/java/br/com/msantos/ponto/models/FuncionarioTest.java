package br.com.msantos.ponto.models;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

public class FuncionarioTest {
	
	private JornadaTrabalho jornadaTrabalho = null;
	
	@Before
	public void criaJornadaDeTrabalho() {
		jornadaTrabalho = new JornadaTrabalhoBuilder()
			.comInicioJornada(8, 00)
			.comTerminoJornada(17, 00)
			.comTipoTrabalhador(TipoTrabalhador.RURAL)
			.comCargaHorariaSemanal(36)
			.comSabado()
			.builder();
	}

	@Test(expected = IllegalArgumentException.class)
	public void naoDevePermiteCpfIncorreto() {

		new Funcionario(jornadaTrabalho, "43865969097", "Paulo", LocalDate.now(), Sexo.M, "2569-9856", "teste@teste.com", true);

	}

	@Test(expected = IllegalArgumentException.class)
	public void naoDevePermitirDataDeNascimentoDeMenoresDeDezoitoAnos() {

		LocalDate dataNascimento = LocalDate.of(2005, 8, 23);
		new Funcionario(jornadaTrabalho, "43865969097", "Paulo", dataNascimento, Sexo.M, "1234", "teste", true);

	}

	@Test
	public void retiraPontuacaoDeCpf() {
		LocalDate dataNascimento = LocalDate.of(2000, 8, 23);
		new Funcionario(jornadaTrabalho, "438.659.690-97", "Paulo", dataNascimento, Sexo.M, "2569-9856", "teste@teste.com", true);
	}

	@Test
	public void funcionarioComFuncionarioBuider() {
		Funcionario builder = new FuncionarioBuilder()
			.comCpf("43865969097")
			.comDataNascimento(20,2,2000)
			.comSexo(Sexo.M)
			.comTelefone("2659-0000")
			.comJornadaDeTrabalho(jornadaTrabalho)
			.comEmail("teste@teste.com")
			.estaAtivo(true)
			.comNome("Paulo").builder();
		
		System.out.println(builder);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void exigeCamposRequeridosCpfNomeDataNascimentoEsexo() {
		
		new FuncionarioBuilder()
			.comDataNascimento(20,2,2000)
			.comSexo(Sexo.M)
			.comTelefone("2659-0000")
			.comJornadaDeTrabalho(jornadaTrabalho)
			.comEmail("teste@teste.com")
			.estaAtivo(true)
			.comNome("Paulo").builder();

	}

}
