package br.com.invistaix.InvistaIX.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.invistaix.InvistaIX.model.EnderecoModel;

@SpringBootTest
@AutoConfigureMockMvc
public class EnderecoServiceTest {
	
	@Autowired
	EnderecoService enderecoService;
	
	@Test
	void cadastrarEApagarEndereco() {
		EnderecoModel endereco = new EnderecoModel();
		endereco.setRua("Rua do teste");
		endereco.setNumero(1234);
		endereco.setLoteamento("pindamonhangaba 2");
		endereco.setCidade("Test City");
		endereco.setEstado("SP");
		endereco.setCEP("11111111");
		
		enderecoService.salvarEndereco(endereco);
		
		enderecoService.apagarEnderecoPorId(endereco.getId()); 
	}
}
