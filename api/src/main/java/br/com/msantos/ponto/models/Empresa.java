package br.com.msantos.ponto.models;
/**Métodos com @Deprecate são exclusivos para o funcionamento do Hibernate. Uso não é autorizado
 * 
 * Michael Santos 28/AGO/2019
 * **/
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import br.com.caelum.stella.tinytype.CNPJ;

@Entity
public class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String cnpj;

	private String nomeEmpresa;

	@Column(nullable = false)
	private String email;

	private String telefone;

	private String endereco;

	@OneToMany(mappedBy = "empresa", fetch = FetchType.EAGER)
	private Set<Funcionario> funcionario = new HashSet<Funcionario>();

	@Deprecated
	public Empresa() {
	}

	public Empresa(String cnpj, String nomeEmpresa, String email, String telefone, String endereco) {
		
		if (!validaCnpj(cnpj)) {
			throw new IllegalArgumentException("CNPJ inválido.");
		}
		
		this.cnpj = cnpj;
		this.nomeEmpresa = nomeEmpresa;
		this.email = email;
		this.telefone = telefone;
		this.endereco = endereco;
	}
	
	private boolean validaCnpj(String cnpj) {
		return new CNPJ(cnpj).isValid();
	}

	public Set<Funcionario> getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Set<Funcionario> funcionario) {
		this.funcionario = funcionario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

}
