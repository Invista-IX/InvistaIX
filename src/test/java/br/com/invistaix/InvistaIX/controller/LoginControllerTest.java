package br.com.invistaix.InvistaIX.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import br.com.invistaix.InvistaIX.model.Usuario;
import br.com.invistaix.InvistaIX.repository.UsuarioRepository;
import br.com.invistaix.InvistaIX.service.UsuarioService;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

	@Autowired    
	private MockMvc mockMvc;
	    
	@Mock
	private UsuarioRepository usuarioRepository;
	    
	@Autowired
	private LoginController loginController;
	
	@Autowired 
	private UsuarioService usuarioService;
	
	private Model model;
	
	@Test
	public void mostrarLoginTest() throws Exception {
		mockMvc.perform(get("/login"))
		.andExpect(status().isOk());
	}
	
	@Test
	public void criarUsuarioERealizarLoginTest() {
	    Usuario usuario = new Usuario();
	    usuario.setNome("Carlos");
	    usuario.setSobrenome("silva");
	    usuario.setEmail("carlos@teste.com");
	    usuario.setTelefone("4599999999");
	    usuario.setCpfCnpj("12345678988");
	    usuario.setSenha("senha");
	    usuario.setTipoPessoa('F');
	    
	    usuarioService.salvarCadastro(usuario);
	    
	    when(usuarioRepository.existsByEmail(usuario.getEmail())).thenReturn(false);

	    String view = loginController.login("carlos@teste.com", "senha", model);

	    assertEquals("redirect:/dashboard", view);

		Usuario usuarioDelete = usuarioService.encontrarPorEmail("carlos@teste.com");
		System.out.println(usuarioDelete.getEmail());
		usuarioService.apagarUsuario(usuarioDelete.getId());
	}
	
}
