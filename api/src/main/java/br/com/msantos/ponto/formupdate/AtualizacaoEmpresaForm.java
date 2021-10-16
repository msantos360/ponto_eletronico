package br.com.msantos.ponto.formupdate;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.msantos.ponto.models.Empresa;
import br.com.msantos.ponto.repository.EmpresaRepository;

public class AtualizacaoEmpresaForm {

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

	public Empresa atualiza(Long id, EmpresaRepository empresaRepository) {
		
		Empresa empresa = empresaRepository.getOne(id);
		
		empresa.setNomeEmpresa(nomeEmpresa);
		empresa.setEmail(email);
		empresa.setTelefone(telefone);
		empresa.setEndereco(endereco);
		return empresa;
	}

	
	
	
}
