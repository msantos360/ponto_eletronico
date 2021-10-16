package br.com.msantos.ponto.formupdate;

import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.msantos.ponto.models.Empresa;
import br.com.msantos.ponto.models.Funcionario;
import br.com.msantos.ponto.models.Sexo;
import br.com.msantos.ponto.repository.EmpresaRepository;
import br.com.msantos.ponto.repository.FuncionarioRepository;

public class AtualizacaoFuncionarioForm {

	@NotEmpty
	@NotNull
	private String nome;

	private LocalDate dataNascimento;

	private Sexo sexo;

	private String telefone;

	private String empresaCnpj;

	@NotEmpty
	@NotNull
	private String email;

	private boolean ativo;

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

	public void setEmpresaCnpj(String empresaCnpj) {
		this.empresaCnpj = empresaCnpj;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Funcionario atualiza(Long id, FuncionarioRepository funcionarioRepository,
			EmpresaRepository empresaRepository) {

		Funcionario funcionario = funcionarioRepository.getOne(id);
		Empresa empresaAssociada = empresaRepository.findByCnpj(empresaCnpj).get();

		funcionario.setNome(nome);
		funcionario.setDataNascimento(dataNascimento);
		funcionario.setSexo(sexo);
		funcionario.setTelefone(telefone);
		funcionario.setAtivo(ativo);
		funcionario.setEmail(email);
		funcionario.setEmpresa(empresaAssociada);

		return funcionario;
	}
}
