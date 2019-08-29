package br.com.msantos.ponto.models;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
	PontoEletronicoRepository pontoEletronicoRepository;

	private JornadaTrabalho jornadaTrabalho = null;

	@Before
	public void criaJornadaDeTrabalho() {
		jornadaTrabalho = new JornadaTrabalhoBuilder().comInicioJornada(8, 00).comTerminoJornada(17, 00)
				.comTipoTrabalhador(TipoTrabalhador.RURAL).comCargaHorariaSemanal(36).comSabado().builder();
	}

	@Test(expected = IllegalArgumentException.class)
	public void naoDevePermiteCpfIncorreto() {

		new Funcionario(jornadaTrabalho, "43865969097", "Paulo", LocalDate.now(), Sexo.M, "2569-9856",
				"teste@teste.com", true);

	}

	@Test(expected = IllegalArgumentException.class)
	public void naoDevePermitirDataDeNascimentoDeMenoresDeDezoitoAnos() {

		LocalDate dataNascimento = LocalDate.of(2005, 8, 23);
		new Funcionario(jornadaTrabalho, "43865969097", "Paulo", dataNascimento, Sexo.M, "1234", "teste", true);

	}

	@Test
	public void retiraPontuacaoDeCpf() {
		LocalDate dataNascimento = LocalDate.of(2000, 8, 23);
		new Funcionario(jornadaTrabalho, "438.659.690-97", "Paulo", dataNascimento, Sexo.M, "2569-9856",
				"teste@teste.com", true);
	}

	@Test
	public void funcionarioComFuncionarioBuider() {
		new FuncionarioBuilder().comCpf("43865969097").comDataNascimento(20, 2, 2000).comSexo(Sexo.M)
				.comTelefone("2659-0000").comJornadaDeTrabalho(jornadaTrabalho).comEmail("teste@teste.com")
				.estaAtivo(true).comNome("Paulo").builder();

	}

	@Test(expected = IllegalArgumentException.class)
	public void exigeCamposRequeridosCpfNomeDataNascimentoEsexo() {

		new FuncionarioBuilder().comDataNascimento(20, 2, 2000).comSexo(Sexo.M).comTelefone("2659-0000")
				.comJornadaDeTrabalho(jornadaTrabalho).comEmail("teste@teste.com").estaAtivo(true).comNome("Paulo")
				.builder();

	}

//	@Test
//	public void cadastraFuncionarioEJornadaNoBancoDeDados() {
//
//		Funcionario funcionario = new FuncionarioBuilder().comNome("Michael Santos")
//				.comCpf("63873882108")
//				.comDataNascimento(20, 02, 1995)
//				.comSexo(Sexo.M)
//				.comTelefone("6228971430")
//				.estaAtivo(true)
//				.comEmail("michael.shel96@gmail.com").builder();
//
//		funcionarioRepository.save(funcionario);
//		
//		JornadaTrabalho jornadaDeTrabalhoJoana = new JornadaTrabalhoBuilder()
//			.comTipoTrabalhador(TipoTrabalhador.URBANO)
//			.comCargaHorariaSemanal(44)
//			.comIntervaloOptional(60)
//			.comInicioJornada(8, 00)
//			.comTerminoJornada(18, 00)
//			.comFuncionario(funcionario)
//			.builder();
//		
//		jornadaTrabalhoRepository.save(jornadaDeTrabalhoJoana);
//		
//	}

//	@Test
//	public void cadastraPonto() {
//		
//		Funcionario funcionariaJoanaBanco = funcionarioRepository.findById((long) 2).get();
//		
//		PontoEletronico pontoJoana = new PontoEletronicoBuilder()
//				.comFuncionario(funcionariaJoanaBanco)
//				.buider();
//		
//		pontoJoana.baterCartao();
//
//		pontoEletronicoRepository.save(pontoJoana);
//		
//	}

	@Test
	public void testePontoDoBancoDeDadosSelect() {
		Optional<PontoEletronico> pontoJoanaDoBanco = pontoEletronicoRepository.findById((long) 1);
		System.out.println(pontoJoanaDoBanco.get().getInicioJornada());

		System.out.println(pontoJoanaDoBanco.get().tempoDeAtrasadoInicioJornada());

	}

	// cpf 19532110542, 30083209450
	@Test
	public void testaBateCartaoFuncionario() {

		Optional<Funcionario> funcionario = funcionarioRepository.findByCpf("63873882108");

		if (funcionario.isPresent()) {
			Optional<PontoEletronico> ponto = pontoEletronicoRepository
					.findByPontoAtualDoFuncionario(funcionario.get());

			if (ponto.isPresent()) {
				ponto.get().bateCartao();
				pontoEletronicoRepository.save(ponto.get());
			
			} else {
				PontoEletronico newPonto = new PontoEletronicoBuilder()
						.comFuncionario(funcionario.get())
						.buider();
				
				newPonto.bateCartao();
				pontoEletronicoRepository.save(newPonto);
				
			}
			
		}

	}

}
