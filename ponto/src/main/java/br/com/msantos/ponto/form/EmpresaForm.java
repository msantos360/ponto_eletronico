package br.com.msantos.ponto.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.msantos.ponto.models.Empresa;

public class EmpresaForm {

	@NotNull
	@NotEmpty
	private String cnpj;

	@NotNull
	@NotEmpty
	private String nomeEmpresa;

	@NotNull
	@NotEmpty
	private String email;

	private String telefone;

	@NotNull
	@NotEmpty
	private String endereco;

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Empresa converter(EmpresaForm form) {
		
		return new Empresa(cnpj, nomeEmpresa, email, telefone, endereco);
	}

}
