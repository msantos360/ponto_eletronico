package br.com.msantos.ponto.models;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.msantos.ponto.repository.EmpresaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmpresaTest {

	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Test
	public void cadastroEmpresa() {
		
//		Empresa empresa = new Empresa("16036746000195", "XPTO", "teste@xpto.com", "112020-3030", "Rua Joaquim Nabuco, 50, Olaria, Porto Velho, RO");
//		empresaRepository.save(empresa);
		
		Empresa empresa = empresaRepository.findByCnpj("16036746000195").get();
		
		String cnpjEsperado = "16036746000195";
		
		assertEquals(cnpjEsperado, empresa.getCnpj());
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void naoDeveAceitaCnpjIncorreto() {
		
		new Empresa("16036746000115", "XPTO", "teste@xpto.com", "112020-3030", "Rua Joaquim Nabuco, 50, Olaria, Porto Velho, RO");
		
	}
	

}
