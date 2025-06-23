package br.com.invistaix.InvistaIX.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.invistaix.InvistaIX.model.ProprietarioModel;

@SpringBootTest
@AutoConfigureMockMvc
public class ProprietarioServiceTest {

	@Autowired
	ProprietarioService proprietarioService;
	
	@Test
	void cadastrarEApagarProprietario() {
		ProprietarioModel proprietario = new ProprietarioModel();
		proprietario.setNome("junim");
		proprietario.setSobrenome("teste");
		proprietario.setTipoPessoa('F');
		proprietario.setCnpjCpf("12345678900");
		proprietario.setEmail("junimteste@teste.com");
		proprietario.setTelefone("(45) 9999-9999");
		
		proprietarioService.salvarProprietario(proprietario);
		
		proprietarioService.apagarProprietarioPorId(proprietario.getId());
	}
}
