package br.com.msantos.ponto.repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.msantos.ponto.models.Funcionario;
import br.com.msantos.ponto.models.PontoEletronico;

public interface PontoEletronicoRepository extends JpaRepository<PontoEletronico, Long> {

	@Query("select p from PontoEletronico p where p.funcionario = :funcionario and p.diaEncerrado is false and p.justificativa is false")
	Optional<PontoEletronico> findByPontoAtualDoFuncionario(@Param("funcionario") Funcionario funcionario);
	
	@Query("select p from PontoEletronico p where p.funcionario = :funcionario "
			+ "and p.inicioJornada between :inicio and :termino order by p.inicioJornada")
	Set<PontoEletronico> findByPontoAndPeriodo(@Param("funcionario") Funcionario funcionario,
				@Param("inicio") LocalDateTime inicio,
				@Param("termino") LocalDateTime termino);
	
	PontoEletronico findByTransacaoId(String transacaoId);
	
	
}
