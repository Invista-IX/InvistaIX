package br.com.invistaix.InvistaIX.controller;

import static org.junit.Assert.assertEquals;
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

import br.com.invistaix.InvistaIX.model.GrupoModel;
import br.com.invistaix.InvistaIX.repository.GrupoRepository;
import br.com.invistaix.InvistaIX.service.GrupoService;

@SpringBootTest
@AutoConfigureMockMvc
public class DashboardControllerTest {

	@Autowired    
	private MockMvc mockMvc;
	
	@Mock
	private GrupoRepository grupoRepository;
	
	@Autowired 
	private DashboardController dashboardController;
	
	@Autowired
	GrupoService grupoService;
	
	@Mock
	private Model model;
	
	@Test
	void dashboardUrlGetTest() throws Exception {
		mockMvc.perform(get("/dashboard"))
				.andExpect(status().isOk());
		
		String pagina = dashboardController.dashboardController();
		assertEquals("dashboard", pagina);
	}
	
	@Test
	void cadastroGrupoUrlGetTest() throws Exception {
		mockMvc.perform(get("/dashboard/cadastro_grupo"))
				.andExpect(status().isOk());
		
		String pagina = dashboardController.cadastrarGrupo(model);
		assertEquals("dashboard/cadastro_grupo", pagina);
	}
	
	@Test
	void cadastroGrupoUrlPostTest() throws Exception {
		mockMvc.perform(post("/dashboard/cadastro_grupo")
				.param("nome", "grupo")
				.param("codigo", "grupoTeste")
				.param("senha", "senha"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/dashboard"));
			
		GrupoModel grupoTeste = grupoService.encontrarPorCodigo("grupoTeste");
		System.out.println(grupoTeste.getCodigo());
		grupoService.apagarGrupo(grupoTeste.getId());
		
		GrupoModel grupoTeste2 = new GrupoModel();
		grupoTeste2.setNome("grupo2");
		grupoTeste2.setCodigo("grupoTeste2");
		grupoTeste2.setSenha("senha2");
		
		String pagina = dashboardController.salvarGrupo(grupoTeste2, model);
		assertEquals("redirect:/dashboard", pagina);
		
		grupoService.apagarGrupo(grupoTeste2.getId());
	}
	
	@Test
	void grupoUrlGetTest() throws Exception {
		GrupoModel grupoTeste = new GrupoModel();
		grupoTeste.setNome("grupo3");
		grupoTeste.setCodigo("grupoTeste3");
		grupoTeste.setSenha("senha3");
		
		dashboardController.salvarGrupo(grupoTeste, model);
		
		String url = "/dashboard/grupo/" + grupoTeste.getId().toString();
		System.out.println(url);
		
		mockMvc.perform(get(url))
				.andExpect(status().isOk());
		
		grupoService.apagarGrupo(grupoTeste.getId());
	}
	
	@Test
	void cadastrarEApagarGrupoTest() throws Exception {
		mockMvc.perform(post("/dashboard/cadastro_grupo")
				.param("nome", "grupo")
				.param("codigo", "grupoTeste")
				.param("senha", "senha"))
				.andExpect(status().is3xxRedirection())
				.andExpect(redirectedUrl("/dashboard"));
		
		GrupoModel grupoTeste = grupoService.encontrarPorCodigo("grupoTeste");
		System.out.println(grupoTeste.getCodigo());
		grupoService.apagarGrupo(grupoTeste.getId());
	}
	
	@Test
	void cadastrarNomeNuloGrupoTest() throws Exception {
		try {
			mockMvc.perform(post("/dashboard/cadastro_grupo")
					.param("codigo", "grupoTeste")
					.param("senha", "senha"))
					.andExpect(status().is3xxRedirection())
					.andExpect(redirectedUrl("/dashboard"));
			
			GrupoModel grupoTeste = grupoService.encontrarPorCodigo("grupoTeste");
			System.out.println(grupoTeste.getCodigo());
			grupoService.apagarGrupo(grupoTeste.getId());
		} catch (Exception e){
			System.out.println("erro: campo nome identificado como nulo");
		}
	}
	
	@Test
	void cadastrarCodigoNuloGrupoTest() throws Exception {
		try {
			mockMvc.perform(post("/dashboard/cadastro_grupo")
					.param("nome", "grupo")
					.param("senha", "senha"))
					.andExpect(status().is3xxRedirection())
					.andExpect(redirectedUrl("/dashboard"));
			
			GrupoModel grupoTeste = grupoService.encontrarPorCodigo("grupoTeste");
			System.out.println(grupoTeste.getCodigo());
			grupoService.apagarGrupo(grupoTeste.getId());
		} catch (Exception e){
			System.out.println("erro: campo codigo identificado como nulo");
		}
	}
	
	@Test
	void cadastrarSenhaNuloGrupoTest() throws Exception {
		try {
			mockMvc.perform(post("/dashboard/cadastro_grupo")
					.param("nome", "grupo")
					.param("codigo", "grupoTeste"))
					.andExpect(status().is3xxRedirection())
					.andExpect(redirectedUrl("/dashboard"));
			
			GrupoModel grupoTeste = grupoService.encontrarPorCodigo("grupoTeste");
			System.out.println(grupoTeste.getCodigo());
			grupoService.apagarGrupo(grupoTeste.getId());
		} catch (Exception e){
			System.out.println("erro: campo senha identificado como nulo");
		}
	}
	
	@Test
	void cadastrarImagemNuloGrupoTest() throws Exception {
		try {
			mockMvc.perform(post("/dashboard/cadastro_grupo")
					.param("nome", "grupo")
					.param("codigo", "grupoTeste")
					.param("senha", "senha"))
					.andExpect(status().is3xxRedirection())
					.andExpect(redirectedUrl("/dashboard"));
			
			GrupoModel grupoTeste = grupoService.encontrarPorCodigo("grupoTeste");
			System.out.println(grupoTeste.getCodigo());
			grupoService.apagarGrupo(grupoTeste.getId());
		} catch (Exception e){
			System.out.println("erro: campo não nulo identificado como nulo");
		}
	}
}
