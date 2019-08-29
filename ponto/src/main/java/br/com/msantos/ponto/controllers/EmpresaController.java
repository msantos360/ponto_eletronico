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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.msantos.ponto.dtos.EmpresaDto;
import br.com.msantos.ponto.form.EmpresaForm;
import br.com.msantos.ponto.formupdate.AtualizacaoEmpresaForm;
import br.com.msantos.ponto.models.Empresa;
import br.com.msantos.ponto.repository.EmpresaRepository;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	@GetMapping("/{cnpj}")
	public ResponseEntity<EmpresaDto> buscaEmpresaPorCnpj(@PathVariable String cnpj) {
		
		Optional<Empresa> empresa = empresaRepository.findByCnpj(cnpj);
		
		if(empresa.isPresent()) {
			return ResponseEntity.ok(new EmpresaDto(empresa.get())); 
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<EmpresaDto> cadastra(@RequestBody @Valid EmpresaForm form, UriComponentsBuilder uriBuilder){
		
		Empresa empresa = form.converter(form);
		empresaRepository.save(empresa);
		URI uri = uriBuilder.path("/empresa/{cnpj}").buildAndExpand(empresa.getCnpj()).toUri();

		return ResponseEntity.created(uri).body(new EmpresaDto(empresa));
	}
	
	@DeleteMapping("/{cnpj}")
	@Transactional
	public ResponseEntity<?> remove(@PathVariable String cnpj){
		Optional<Empresa> empresa = empresaRepository.findByCnpj(cnpj);
		
		if (empresa.isPresent()) {
			empresaRepository.deleteById(empresa.get().getId());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{cnpj}")
	@Transactional
	public ResponseEntity<EmpresaDto> atualiza(@PathVariable String cnpj, @RequestBody @Valid AtualizacaoEmpresaForm form){
		
		Optional<Empresa> empresaOptional = empresaRepository.findByCnpj(cnpj);
		
		if (empresaOptional.isPresent()) {
			
			Empresa empresa = form.atualiza(empresaOptional.get().getId(), empresaRepository);
			return ResponseEntity.ok(new EmpresaDto(empresa));
		}
		return ResponseEntity.notFound().build();
	}
}
