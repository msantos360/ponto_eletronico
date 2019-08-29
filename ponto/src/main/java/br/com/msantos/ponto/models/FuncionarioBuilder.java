package br.com.msantos.ponto.models;

import java.time.LocalDate;

public class FuncionarioBuilder {

	private String cpf;

	private String nome;

	private LocalDate dataNascimento;

	private Sexo sexo;

	private String telefone;

	private String email;

	private boolean ativo;

	private JornadaTrabalho jornadaTrabalho;

	private Empresa empresa;

	public FuncionarioBuilder comEmpresa(Empresa empresa) {
		this.empresa = empresa;
		return this;
	}
	
	public FuncionarioBuilder comJornadaDeTrabalho(JornadaTrabalho jornadaTrabalho) {
		this.jornadaTrabalho = jornadaTrabalho;
		return this;
	}

	public FuncionarioBuilder comCpf(String cpf) {
		this.cpf = cpf;
		return this;
	}
	
	public FuncionarioBuilder comNome(String nome) {
		this.nome = nome;
		return this;
	}

	public FuncionarioBuilder comDataNascimento(int dia, int mes, int ano) {
		
		this.dataNascimento = LocalDate.of(ano, mes, dia);
		return this;
	}

	public FuncionarioBuilder comSexo(Sexo sexo) {
		this.sexo = sexo;
		return this;
	}

	public FuncionarioBuilder comTelefone(String telefone) {
		this.telefone = telefone;
		return this;
	}

	public FuncionarioBuilder comEmail(String email) {
		this.email = email;
		return this;
	}

	public FuncionarioBuilder estaAtivo(boolean ativo) {
		this.ativo = ativo;
		return this;
	}

	public Funcionario builder() {
		return new Funcionario(jornadaTrabalho, cpf, nome, dataNascimento, sexo, telefone, email, ativo, empresa);
	}

}
