package br.com.msantos.ponto.dtos;

import java.time.LocalDate;

import br.com.msantos.ponto.models.Funcionario;
import br.com.msantos.ponto.models.Sexo;

public class FuncionarioDto {

	private Long id;

	private String cpf;

	private String nome;

	private LocalDate dataNascimento;

	private Sexo sexo;

	private String telefone;

	private String email;

	private boolean ativo;

	public FuncionarioDto(Funcionario funcionario) {
		this.id = funcionario.getId();
		this.cpf = funcionario.getCpf();
		this.nome = funcionario.getNome();
		this.dataNascimento = funcionario.getDataNascimento();
		this.sexo = funcionario.getSexo();
		this.telefone = funcionario.getTelefone();
		this.email = funcionario.getEmail();
		this.ativo = funcionario.isAtivo();
	}

	public Long getId() {
		return id;
	}

	public String getCpf() {
		return cpf;
	}

	public String getNome() {
		return nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getEmail() {
		return email;
	}

	public boolean isAtivo() {
		return ativo;
	}
	
	
}
