package br.com.msantos.ponto.models;

/**Métodos com @Deprecate são exclusivos para o funcionamento do Hibernate. Uso não é autorizado
 * 
 * Michael Santos 28/AGO/2019
 * **/
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.msantos.ponto.rn.AdicionalNoturno;
import br.com.msantos.ponto.rn.AtrasoInicioJornada;
import br.com.msantos.ponto.rn.AtrasoIntervalo;
import br.com.msantos.ponto.rn.CalculadorDePonto;
import br.com.msantos.ponto.rn.HoraExtra;
import br.com.msantos.ponto.rn.JornadaDiaria;
import br.com.msantos.ponto.rn.JornadaDiariaComIntervalo;
import br.com.msantos.ponto.rn.SaidaAntecipada;
import br.com.msantos.ponto.rn.TempoIntervalo;

@Entity
public class PontoEletronico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	protected LocalDateTime inicioJornada;

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	protected LocalDateTime inicioIntervalo;

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	protected LocalDateTime terminoIntervalo;

	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	protected LocalDateTime terminoJornada;

	private boolean diaEncerrado = false;

	private boolean justificativa = false;

	private String transacaoId = "TR" + System.currentTimeMillis();

	@ManyToOne
	private Funcionario funcionario;

	@Transient
	protected EstadoAtualPonto estado;

	public PontoEletronico() {
	}

	public PontoEletronico(Funcionario funcionario, LocalDateTime inicioJornada, LocalDateTime inicioIntervalo,
			LocalDateTime terminoIntervalo, LocalDateTime terminoJornada, boolean diaEncerrado, boolean justificativa) {

		this.inicioJornada = inicioJornada;
		this.inicioIntervalo = inicioIntervalo;
		this.terminoIntervalo = terminoIntervalo;
		this.terminoJornada = terminoJornada;
		this.funcionario = funcionario;
		this.diaEncerrado = diaEncerrado;
		this.justificativa = justificativa;
	}

	public Long getId() {
		return id;
	}

	public boolean isDiaEncerrado() {
		return diaEncerrado;
	}

	public boolean isJustificativa() {
		return justificativa;
	}

	public EstadoAtualPonto getEstado() {
		return estado;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public LocalDateTime getInicioJornada() {
		return inicioJornada;
	}

	public LocalDateTime getInicioIntervalo() {
		return inicioIntervalo;
	}

	public LocalDateTime getTerminoIntervalo() {
		return terminoIntervalo;
	}

	public LocalDateTime getTerminoJornada() {
		return terminoJornada;
	}

	public String getTransacaoId() {
		return transacaoId;
	}

	public void validaInicioJornada() {

		estado.inicioJornada(this);
	}

	public void validaInicioIntervalo() {

		estado.inicioIntervalo(this);
	}

	public void validaTerminoIntervalo() {

		estado.terminoIntervalo(this);
	}

	public void validaTerminoJornada() {

		estado.terminoJornada(this);
	}

	// Refatorar este metodo
	public void bateCartao() {

		if (getInicioJornada() == null) {
			this.estado = new EstadoInicioJornada();
			validaInicioJornada();
			return;

		}
		if (getInicioIntervalo() == null) {
			this.estado = new EstadoInicioIntervalo();
			validaInicioIntervalo();
			return;

		}
		if (getTerminoIntervalo() == null) {
			this.estado = new EstadoTerminoIntervalo();
			validaTerminoIntervalo();
			return;

		}
		if (getTerminoJornada() == null) {
			this.estado = new EstadoTerminoJornada();
			validaTerminoJornada();
			return;
		}
	}

	public Set<PontoEletronico> pontoPorFuncionariosEperiodo(PontoSelectBuilder psb) {

		Funcionario funcionario = psb.getFuncionarioRepository().findByCpf("63873882108").get();

		return psb.getPontoEletronicoRepository().findByPontoAndPeriodo(funcionario, psb.getInicioPeriodo(),
				psb.getTerminoPerido());

	}

	public Duration tempoPermanenciaJornadaDiaria() {

		return new CalculadorDePonto().realizaAcao(this, new JornadaDiaria());
	}

	public Duration tempoPermanenciaComDescontoIntervalo() {

		return new CalculadorDePonto().realizaAcao(this, new JornadaDiariaComIntervalo());
	}

	public Duration tempoDeAtrasadoInicioJornada() {

		return new CalculadorDePonto().realizaAcao(this, new AtrasoInicioJornada());
	}

	public Duration tempoAtrasadoIntervalo() {

		return new CalculadorDePonto().realizaAcao(this, new AtrasoIntervalo());
	}

	public Duration tempoSaidaAntecipada() {

		return new CalculadorDePonto().realizaAcao(this, new SaidaAntecipada());
	}

	public Duration tempoHoraExtra() {

		return new CalculadorDePonto().realizaAcao(this, new HoraExtra());
	}

	public Duration tempoAdicionalNoturno() {

		return new CalculadorDePonto().realizaAcao(this, new AdicionalNoturno());
	}

	public Duration tempoIntervalo() {
		return new CalculadorDePonto().realizaAcao(this, new TempoIntervalo());
	}

	@Deprecated
	public void setId(Long id) {
		this.id = id;
	}

	public void setJustificativa(boolean justificativa) {
		this.justificativa = justificativa;
	}

	public void setDiaEncerrado(boolean diaEncerrado) {
		this.diaEncerrado = diaEncerrado;
	}

	@Deprecated
	public void setInicioJornada(LocalDateTime inicioJornada) {
		this.inicioJornada = inicioJornada;
	}

	@Deprecated
	public void setInicioIntervalo(LocalDateTime inicioIntervalo) {
		this.inicioIntervalo = inicioIntervalo;
	}

	@Deprecated
	public void setTerminoIntervalo(LocalDateTime terminoIntervalo) {
		this.terminoIntervalo = terminoIntervalo;
	}

	@Deprecated
	public void setTerminoJornada(LocalDateTime terminoJornada) {
		this.terminoJornada = terminoJornada;
	}

	@Deprecated
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@Deprecated
	public void setEstado(EstadoAtualPonto estado) {
		this.estado = estado;
	}

	@Deprecated
	public void setTransacaoId(String transacaoId) {
		this.transacaoId = transacaoId;
	}

	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		String inicioJornada = ">>\tInício da jornada:\t" + this.inicioJornada.format(formatter) + "\n";
		String inicioIntervalo = ">\tInício intervalo:\t" + this.inicioIntervalo.format(formatter) + "\n";
		String terminoIntervalo = "<\tTérmino intervalo:\t" + this.terminoIntervalo.format(formatter) + "\n";
		String terminoJornada = "<<\tTérmino da jornada:\t" + this.terminoJornada.format(formatter) + "\n";

		return inicioJornada + inicioIntervalo + terminoIntervalo + terminoJornada + "\n" + funcionario;
	}

}
