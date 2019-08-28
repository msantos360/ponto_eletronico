package br.com.msantos.ponto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.msantos.ponto.models.PontoEletronico;

public interface PontoEletronicoRepository extends JpaRepository<PontoEletronico, Long> {

	
}
