package br.com.msantos.ponto.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.msantos.ponto.models.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

	Optional<Funcionario> findByCpf(String cpf); 
}
