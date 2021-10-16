package br.com.msantos.ponto.models;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.msantos.ponto.repository.EmpresaRepository;
import br.com.msantos.ponto.repository.FuncionarioRepository;
import br.com.msantos.ponto.repository.JornadaTrabalhoRepository;
import br.com.msantos.ponto.repository.PontoEletronicoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FuncionarioTest {

	@Autowired
	JornadaTrabalhoRepository jornadaTrabalhoRepository;

	@Autowired
	FuncionarioRepository funcionarioRepository;

	@Autowired
	private PontoEletronicoRepository pontoEletronicoRepository;

	@Autowired
	private EmpresaRepository empresaRepository;

	private JornadaTrabalho jornadaTrabalho = null;

	private Empresa empresa;

	@Before
	public void criaJornadaDeTrabalho() {
		jornadaTrabalho = new JornadaTrabalhoBuilder().comInicioJornada(8, 00).comTerminoJornada(17, 00)
				.comTipoTrabalhador(TipoTrabalhador.RURAL).comCargaHorariaSemanal(36).comSabado().builder();

		empresa = empresaRepository.findByCnpj("16036746000195").get();
	}

	@Test(expected = IllegalArgumentException.class)
	public void naoDevePermiteCpfIncorreto() {

		new Funcionario(jornadaTrabalho, "43865969097", "Paulo", LocalDate.now(), Sexo.M, "2569-9856",
				"teste@teste.com", true, empresa);

	}

	@Test(expected = IllegalArgumentException.class)
	public void naoDevePermitirDataDeNascimentoDeMenoresDeDezoitoAnos() {

		LocalDate dataNascimento = LocalDate.of(2005, 8, 23);
		new Funcionario(jornadaTrabalho, "43865969097", "Paulo", dataNascimento, Sexo.M, "1234", "teste", true,
				empresa);

	}

	@Test
	public void retiraPontuacaoDeCpf() {
		LocalDate dataNascimento = LocalDate.of(2000, 8, 23);
		new Funcionario(jornadaTrabalho, "438.659.690-97", "Paulo", dataNascimento, Sexo.M, "2569-9856",
				"teste@teste.com", true, empresa);
	}

	@Test
	public void funcionarioComFuncionarioBuider() {
		new FuncionarioBuilder().comCpf("43865969097").comDataNascimento(20, 2, 2000).comSexo(Sexo.M)
				.comTelefone("2659-0000").comJornadaDeTrabalho(jornadaTrabalho).comEmail("teste@teste.com")
				.estaAtivo(true).comNome("Paulo").comEmpresa(empresa).builder();

	}

	@Test(expected = IllegalArgumentException.class)
	public void exigeCamposRequeridosCpfNomeDataNascimentoEsexo() {

		new FuncionarioBuilder().comDataNascimento(20, 2, 2000).comSexo(Sexo.M).comTelefone("2659-0000")
				.comJornadaDeTrabalho(jornadaTrabalho).comEmail("teste@teste.com").estaAtivo(true).comNome("Paulo")
				.comEmpresa(empresa).builder();

	}

	@Test
	public void testePontoDoBancoDeDadosSelect() {
		Optional<PontoEletronico> pontoJoanaDoBanco = pontoEletronicoRepository.findById((long) 8);
		System.out.println(pontoJoanaDoBanco.get().getInicioJornada());

		System.out.println(pontoJoanaDoBanco.get().tempoDeAtrasadoInicioJornada());

	}

}
