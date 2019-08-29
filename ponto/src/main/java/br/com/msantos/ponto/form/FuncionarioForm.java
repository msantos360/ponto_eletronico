package br.com.msantos.ponto.form;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.msantos.ponto.models.Empresa;
import br.com.msantos.ponto.models.Funcionario;
import br.com.msantos.ponto.models.Sexo;
import br.com.msantos.ponto.repository.EmpresaRepository;

public class FuncionarioForm {

	@NotNull
	@NotEmpty
	private String cpf;

	@NotNull
	@NotEmpty
	private String nome;

	private LocalDate dataNascimento;

	private Sexo sexo;

	private String telefone;

	@NotNull
	@NotEmpty
	private String email;

	private boolean ativo;
	
	private String empresaCnpj;

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public void setEmpresaCnpj(String empresaCnpj) {
		this.empresaCnpj = empresaCnpj;
	}

	public Funcionario converter(FuncionarioForm form, EmpresaRepository er) {

		Empresa empresa = er.findByCnpj(empresaCnpj).get();
		
		return new Funcionario(null, cpf, nome, dataNascimento, sexo, telefone, email, ativo, empresa);
	}
	
	
}
