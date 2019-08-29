package br.com.msantos.ponto.dtos;

import br.com.msantos.ponto.models.Empresa;

public class EmpresaDto {

	private Long id;

	private String cnpj;

	private String nomeEmpresa;

	private String email;

	private String telefone;

	private String endereco;

	public EmpresaDto(Empresa empresa) {
	
		this.id = empresa.getId();
		this.cnpj = empresa.getCnpj();
		this.nomeEmpresa = empresa.getNomeEmpresa();
		this.email = empresa.getEmail();
		this.telefone = empresa.getTelefone();
		this.endereco = empresa.getEndereco();
	}

	public Long getId() {
		return id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getEndereco() {
		return endereco;
	}

}
