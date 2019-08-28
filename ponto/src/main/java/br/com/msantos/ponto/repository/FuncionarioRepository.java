package br.com.msantos.ponto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.msantos.ponto.models.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

	public Funcionario findByCpf(String cpf); 
}
