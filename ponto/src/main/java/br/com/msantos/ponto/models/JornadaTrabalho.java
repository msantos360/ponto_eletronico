package br.com.msantos.ponto.models;

import java.time.Duration;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.msantos.ponto.rn.CalculadorDeJornadaTrabalho;
import br.com.msantos.ponto.rn.CargaHorariaDiaria;
import br.com.msantos.ponto.rn.TempoIntervaloJornada;

@Entity
public class JornadaTrabalho {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@Column(nullable = false)
	@DateTimeFormat(pattern = "HH:mm:ss")
	private LocalTime inicioJornada;
	
	@Column(nullable = false)
	@DateTimeFormat(pattern = "HH:mm:ss")
	private LocalTime terminoJornada;
	
	@Column(nullable = false)
	@DateTimeFormat(pattern = "HH:mm:ss")
	private LocalTime inicioAdicional;

	@Column(nullable = false)
	@DateTimeFormat(pattern = "HH:mm:ss")
	private LocalTime terminoAdicional;

	private Duration tempoIntervalo;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoTrabalhador tipoTrabalhador;

	private Duration cargaHorariaSemanal;

	@Column(nullable = false)
	private boolean trabalhaSabado;

	@Column(nullable = false)
	private boolean trabalhaDomingo;

	@Column(nullable = false)
	private boolean trabalhaFeriado;
	
	@OneToOne
	private Funcionario funcionario;

	public JornadaTrabalho(TipoTrabalhador tipoTrabalhador, LocalTime inicioJornada,
			LocalTime terminoJornada, Duration cargaHorariaSemanal, Duration tempoIntervalo,
			boolean trabalhaSabado, boolean trabalhaDomingo, boolean trabalhaFeriado) {

		this.tipoTrabalhador = tipoTrabalhador;
		this.inicioJornada = inicioJornada;
		this.terminoJornada = terminoJornada;
		this.cargaHorariaSemanal = cargaHorariaSemanal;
		this.tempoIntervalo = tempoIntervalo;
		this.trabalhaSabado = trabalhaSabado;
		this.trabalhaDomingo = trabalhaDomingo;
		this.trabalhaFeriado = trabalhaFeriado;

		determinaTempoAdicional();
		validaTempoDeIntervalo();
		validaCargaHoraria();
	}

	private void determinaTempoAdicional() {
		
		if (tipoTrabalhador.equals(TipoTrabalhador.URBANO)) {
			this.inicioAdicional = LocalTime.of(22, 00);
			this.terminoAdicional = LocalTime.of(5, 00);

		} else if (tipoTrabalhador.equals(TipoTrabalhador.RURAL)) {
			this.inicioAdicional = LocalTime.of(21, 00);
			this.terminoAdicional = LocalTime.of(5, 00);

		} else if (tipoTrabalhador.equals(TipoTrabalhador.PECUARIO)) {
			this.inicioAdicional = LocalTime.of(20, 00);
			this.terminoAdicional = LocalTime.of(4, 00);
		}
		
	}

	private void validaTempoDeIntervalo() {
		
		this.tempoIntervalo = new CalculadorDeJornadaTrabalho().realizaCalculo(this, new TempoIntervaloJornada());
	}
	
	public Duration validaCargaHoraria() {
		
		return new CalculadorDeJornadaTrabalho().realizaCalculo(this, new CargaHorariaDiaria());
	}
	
	public Long getId() {
		return id;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}
	
	public LocalTime getInicioJornada() {
		return inicioJornada;
	}
	
	public LocalTime getTerminoJornada() {
		return terminoJornada;
	}

	public Duration getTempoIntervalo() {
		return tempoIntervalo;
	}

	public LocalTime getInicioAdicional() {
		return inicioAdicional;
	}

	public LocalTime getTerminoAdicional() {
		return terminoAdicional;
	}

	public TipoTrabalhador getTipoTrabalhador() {
		return tipoTrabalhador;
	}

	public Duration getCargaHorariaSemanal() {
		return cargaHorariaSemanal;
	}

	public boolean isTrabalhaSabado() {
		return trabalhaSabado;
	}

	public boolean isTrabalhaDomingo() {
		return trabalhaDomingo;
	}

	public boolean isTrabalhaFeriado() {
		return trabalhaFeriado;
	}

	@Deprecated
	public void setId(Long id) {
		this.id = id;
	}

	@Deprecated
	public void setInicioJornada(LocalTime inicioJornada) {
		this.inicioJornada = inicioJornada;
	}

	@Deprecated
	public void setTerminoJornada(LocalTime terminoJornada) {
		this.terminoJornada = terminoJornada;
	}

	@Deprecated
	public void setInicioAdicional(LocalTime inicioAdicional) {
		this.inicioAdicional = inicioAdicional;
	}

	@Deprecated
	public void setTerminoAdicional(LocalTime terminoAdicional) {
		this.terminoAdicional = terminoAdicional;
	}

	@Deprecated
	public void setTempoIntervalo(Duration tempoIntervalo) {
		this.tempoIntervalo = tempoIntervalo;
	}

	@Deprecated
	public void setTipoTrabalhador(TipoTrabalhador tipoTrabalhador) {
		this.tipoTrabalhador = tipoTrabalhador;
	}

	@Deprecated
	public void setCargaHorariaSemanal(Duration cargaHorariaSemanal) {
		this.cargaHorariaSemanal = cargaHorariaSemanal;
	}

	@Deprecated
	public void setTrabalhaSabado(boolean trabalhaSabado) {
		this.trabalhaSabado = trabalhaSabado;
	}

	@Deprecated
	public void setTrabalhaDomingo(boolean trabalhaDomingo) {
		this.trabalhaDomingo = trabalhaDomingo;
	}

	@Deprecated
	public void setTrabalhaFeriado(boolean trabalhaFeriado) {
		this.trabalhaFeriado = trabalhaFeriado;
	}
	
	@Deprecated
	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@Override
	public String toString() {
		
		return "\nJornadaTrabalho [inicioJornada=" + inicioJornada + ", terminoJornada=" + terminoJornada
				+ ", inicioAdicional=" + inicioAdicional + ", terminoAdicional=" + terminoAdicional
				+ ", tempoIntervalo=" + tempoIntervalo + ", tipoTrabalhador=" + tipoTrabalhador
				+ ", cargaHorariaSemanal=" + cargaHorariaSemanal + ", trabalhaSabado=" + trabalhaSabado
				+ ", trabalhaDomingo=" + trabalhaDomingo + ", trabalhaFeriado=" + trabalhaFeriado + "]";
	}

}
