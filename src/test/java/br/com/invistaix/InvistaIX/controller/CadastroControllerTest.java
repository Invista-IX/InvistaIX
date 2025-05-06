package br.com.invistaix.InvistaIX.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
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

import br.com.invistaix.InvistaIX.model.*;
import br.com.invistaix.InvistaIX.repository.UsuarioRepository;
import br.com.invistaix.InvistaIX.service.UsuarioService;

@SpringBootTest
@AutoConfigureMockMvc
public class CadastroControllerTest {
		
	@Autowired    
	private MockMvc mockMvc;
	    
	@Mock
	private UsuarioRepository usuarioRepository;
	    
	@Autowired
	private CadastroController cadastroController;
	
	@Autowired UsuarioService usuarioService;
	    
	
	private Model model;

	@Test
	public void deveCadastrarUsuarioComSucesso() throws Exception {
		
		//apaga o usuário de teste do banco de dados caso o mesmo esteja salvo
		if(!usuarioRepository.existsByEmail("carlos@teste.com")) {
			System.out.println("entrou no if");
			Usuario usuario = usuarioService.encontrarPorEmail("carlos@teste.com");
			System.out.println(usuario.getEmail());
			usuarioService.apagarUsuario(usuario.getId());
		}
		
		mockMvc.perform(post("/cadastro")
				.param("nome", "Carlos")
				.param("sobrenome", "maia")
				.param("email", "carlos@teste.com")
				.param("senha", "1234")
				.param("cpfCnpj", "12345678900")
				.param("telefone", "11999999999")
				.param("tipoPessoa", "0"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/login"));
	}
	
	@Test
	public void testSalvarUsuarioNovo() {
		
		//apaga o usuário de teste do banco de dados caso o mesmo esteja salvo
		if(!usuarioRepository.existsByEmail("carlos2@teste.com")) {
			System.out.println("entrou no if");
			Usuario usuario = usuarioService.encontrarPorEmail("carlos2@teste.com");
			System.out.println(usuario.getEmail());
			usuarioService.apagarUsuario(usuario.getId());
		}
		
	    Usuario usuario = new Usuario();
	    usuario.setNome("Carlos");
	    usuario.setSobrenome("silva");
	    usuario.setEmail("carlos2@teste.com");
	    usuario.setSenha("4599999999");
	    usuario.setCpfCnpj("12345678988");
	    usuario.setTelefone("senha");
	    usuario.setTipoPessoa(false);
	    
	    when(usuarioRepository.existsByEmail(usuario.getEmail())).thenReturn(false);

	    String view = cadastroController.salvarCadastro(usuario, model);

	    assertEquals("redirect:/login", view);
	}
	
}
