package br.com.invistaix.InvistaIX.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.ui.Model;

import br.com.invistaix.InvistaIX.model.UsuarioModel;
import br.com.invistaix.InvistaIX.repository.UsuarioRepository;
import br.com.invistaix.InvistaIX.service.UsuarioService;

@SpringBootTest
@AutoConfigureMockMvc
public class CadastroUsuarioControllerTest {
		
	@Autowired    
	private MockMvc mockMvc;
	    
	@Mock
	private UsuarioRepository usuarioRepository;
	    
	@Autowired
	private CadastroUsuarioController cadastroController;
	
	@Autowired UsuarioService usuarioService;
	    
	
	private Model model;

	@Test
	public void deveCadastrarEApagarUsuarioComSucesso() throws Exception {
		mockMvc.perform(post("/cadastro")
				.param("nome", "Carlos")
				.param("sobrenome", "maia")
				.param("email", "carlos@teste.com")
				.param("senha", "1234")
				.param("cpfCnpj", "12345678900")
				.param("telefone", "11999999999")
				.param("tipoPessoa", "F"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/login"));

		UsuarioModel usuarioModel = usuarioService.encontrarPorEmail("carlos@teste.com");
		System.out.println(usuarioModel.getEmail());
		usuarioService.apagarUsuario(usuarioModel.getId());
	}
	
	@Test
	public void testSalvarEApagarUsuarioNovo() {
	    UsuarioModel usuarioModel = new UsuarioModel();
	    usuarioModel.setNome("Carlos");
	    usuarioModel.setSobrenome("silva");
	    usuarioModel.setEmail("carlos2@teste.com");
	    usuarioModel.setSenha("4599999999");
	    usuarioModel.setCpfCnpj("12345678988");
	    usuarioModel.setTelefone("senha");
	    usuarioModel.setTipoPessoa('F');
	    
	    when(usuarioRepository.existsByEmail(usuarioModel.getEmail())).thenReturn(false);

	    String view = cadastroController.salvarCadastro(usuarioModel, model);

	    assertEquals("redirect:/login", view);

		UsuarioModel usuarioModelDelete = usuarioService.encontrarPorEmail("carlos2@teste.com");
		System.out.println(usuarioModelDelete.getEmail());
		usuarioService.apagarUsuario(usuarioModelDelete.getId());
	}
}
