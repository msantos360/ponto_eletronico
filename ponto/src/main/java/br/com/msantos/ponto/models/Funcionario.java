package br.com.msantos.ponto.models;
/**Métodos com @Deprecate são exclusivos para o funcionamento do Hibernate. Uso não é autorizado
 * 
 * Michael Santos 28/AGO/2019
 * **/
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.caelum.stella.tinytype.CPF;

@Entity
public class Funcionario {

	@Transient
	private static final long IDADE_MINIMA = 18;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String cpf;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Sexo sexo;

	private String telefone;

	private String email;

	private boolean ativo;
	
	@OneToOne(mappedBy = "funcionario")
	private JornadaTrabalho jornadaTrabalho;
	
	@OneToMany(mappedBy = "funcionario", fetch = FetchType.EAGER)
	private Set<PontoEletronico> pontoEletronico = new HashSet<>();
	
	@ManyToOne
	private Empresa empresa;

	/**Hibernate only**/
	@Deprecated
	public Funcionario() {
	}

	public Funcionario(JornadaTrabalho jornadaTrabalho, String cpf, String nome, LocalDate dataNascimento, Sexo sexo, String telefone, String email,
			boolean ativo, Empresa empresa) {

		if (cpf == null || nome == null || dataNascimento == null) {
			throw new IllegalArgumentException("O campos CPF, NOME, DATA DE NASCIMENTO E SEXO são requeridos");
		}

		if (!cpfValido(cpf)) {
			throw new IllegalArgumentException("CPF inválido");
		}

		if (calculaDataNascimento(dataNascimento) < IDADE_MINIMA) {
			throw new IllegalArgumentException("Data de nascimento deve ser maior que: " + IDADE_MINIMA + " anos");
		}

		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.telefone = telefone;
		this.email = email;
		this.ativo = ativo;
		this.nome = nome;
		this.jornadaTrabalho = jornadaTrabalho;
		this.empresa = empresa;
	}
	
	public JornadaTrabalho getJornadaTrabalho() {
		return jornadaTrabalho;
	}

	public static long getIdadeMinima() {
		return IDADE_MINIMA;
	}

	public Long getId() {
		return id;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}
	
	public Set<PontoEletronico> getPontoEletronico() {
		return pontoEletronico;
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

	private Boolean cpfValido(String cpf) {

		return new CPF(cpf).isValido();
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	public void setPontoEletronico(Set<PontoEletronico> pontoEletronico) {
		this.pontoEletronico = pontoEletronico;
	}
	
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

	public void setJornadaTrabalho(JornadaTrabalho jornadaTrabalho) {
		this.jornadaTrabalho = jornadaTrabalho;
	}

	private long calculaDataNascimento(LocalDate dataNascimento) {

		return ChronoUnit.YEARS.between(dataNascimento, LocalDate.now());
	}

	@Override
	public String toString() {
		return "Funcionario [id=" + id + ", cpf=" + cpf + ", nome=" + nome + ", dataNascimento=" + dataNascimento
				+ ", sexo=" + sexo + ", telefone=" + telefone + ", email=" + email + ", ativo=" + ativo
				+ ", jornadaTrabalho=" + jornadaTrabalho + "]";
	}



}
