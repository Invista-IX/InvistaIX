package br.com.invistaix.InvistaIX.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
public class LoginControllerTest {

	@Autowired    
	private MockMvc mockMvc;

	@Mock
	private HttpSession session;

	@Mock
	private UsuarioRepository usuarioRepository;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

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
	    UsuarioModel usuarioModel = new UsuarioModel();
	    usuarioModel.setNome("Carlos");
	    usuarioModel.setSobrenome("silva");
	    usuarioModel.setEmail("carlos@teste.com");
	    usuarioModel.setTelefone("4599999999");
	    usuarioModel.setCpfCnpj("12345678988");
	    usuarioModel.setSenha("senha");
	    usuarioModel.setTipoPessoa('F');
	    
	    usuarioService.salvarCadastro(usuarioModel);
	    
	    when(usuarioRepository.existsByEmail(usuarioModel.getEmail())).thenReturn(false);

	    String view = loginController.login("carlos@teste.com", "senha", model, session);

	    assertEquals("redirect:/dashboard", view);

		UsuarioModel usuarioModelDelete = usuarioService.encontrarPorEmail("carlos@teste.com");
		System.out.println(usuarioModelDelete.getEmail());
		usuarioService.apagarUsuario(usuarioModelDelete.getId());
	}
	
}
