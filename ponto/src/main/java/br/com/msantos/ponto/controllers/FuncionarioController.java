package br.com.msantos.ponto.controllers;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.msantos.ponto.dtos.FuncionarioDto;
import br.com.msantos.ponto.form.FuncionarioForm;
import br.com.msantos.ponto.models.Funcionario;
import br.com.msantos.ponto.repository.EmpresaRepository;
import br.com.msantos.ponto.repository.FuncionarioRepository;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;

	@GetMapping("/{cpf}")
	public ResponseEntity<FuncionarioDto> buscaFuncionarioPorCpf(@PathVariable String cpf) {

		Optional<Funcionario> funcionario = funcionarioRepository.findByCpf(cpf);

		if (funcionario.isPresent()) {
			return ResponseEntity.ok(new FuncionarioDto(funcionario.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<FuncionarioDto> cadastra(@RequestBody @Valid FuncionarioForm form, UriComponentsBuilder uriBuilder){
		
		Funcionario funcionario = form.converter(form, empresaRepository);
		funcionarioRepository.save(funcionario);
		URI uri = uriBuilder.path("/funcionario/{cpf}").buildAndExpand(funcionario.getCpf()).toUri();

		return ResponseEntity.created(uri).body(new FuncionarioDto(funcionario));
	}
	
	@DeleteMapping("/{cpf}")
	@Transactional
	public ResponseEntity<?> remove(@PathVariable String cpf){
		Optional<Funcionario> funcionario = funcionarioRepository.findByCpf(cpf);
		
		if (funcionario.isPresent()) {
			funcionarioRepository.deleteById(funcionario.get().getId());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}
