package br.com.msantos.ponto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.msantos.ponto.models.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

	Optional<Empresa> findByCnpj(String cnpj);
}
