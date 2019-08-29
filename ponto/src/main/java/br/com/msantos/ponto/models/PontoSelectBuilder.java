package br.com.msantos.ponto.models;

import java.time.LocalDateTime;

import br.com.msantos.ponto.repository.FuncionarioRepository;
import br.com.msantos.ponto.repository.PontoEletronicoRepository;

public class PontoSelectBuilder {

	private FuncionarioRepository funcionarioRepository;

	private PontoEletronicoRepository pontoEletronicoRepository;

	private String cpf;

	private LocalDateTime inicioPeriodo;

	private LocalDateTime terminoPerido;

	public PontoSelectBuilder comFuncionarioRepository(FuncionarioRepository funcionarioRepository) {

		this.funcionarioRepository = funcionarioRepository;
		return this;
	}

	public PontoSelectBuilder comPontoRepository(PontoEletronicoRepository pontoEletronicoRepository) {

		this.pontoEletronicoRepository = pontoEletronicoRepository;
		return this;
	}

	public PontoSelectBuilder comCPF(String cpf) {

		this.cpf = cpf;
		return this;
	}

	public PontoSelectBuilder comDataInicioEm(LocalDateTime inicio) {

		this.inicioPeriodo = inicio;
		return this;
	}

	public PontoSelectBuilder comDataTerminoEm(LocalDateTime termino) {

		this.terminoPerido = termino;
		return this;
	}

	public FuncionarioRepository getFuncionarioRepository() {
		return funcionarioRepository;
	}

	public PontoEletronicoRepository getPontoEletronicoRepository() {
		return pontoEletronicoRepository;
	}

	public String getCpf() {
		return cpf;
	}

	public LocalDateTime getInicioPeriodo() {
		return inicioPeriodo;
	}

	public LocalDateTime getTerminoPerido() {
		return terminoPerido;
	}

}
