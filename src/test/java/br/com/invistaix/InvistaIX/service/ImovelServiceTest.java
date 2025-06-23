package br.com.invistaix.InvistaIX.service;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.invistaix.InvistaIX.model.EnderecoModel;
import br.com.invistaix.InvistaIX.model.GrupoModel;
import br.com.invistaix.InvistaIX.model.ImovelModel;
import br.com.invistaix.InvistaIX.model.ProprietarioModel;

@SpringBootTest
@AutoConfigureMockMvc
public class ImovelServiceTest {

    @Autowired
    GrupoService grupoService;
	
    @Autowired
    ImovelService imovelService;

    @Autowired
    EnderecoService enderecoService;
    
    @Autowired
    ProprietarioService proprietarioService;

    @Test
    void CadastrarEApagarImovel() {
    	ProprietarioModel proprietario = new ProprietarioModel();
    	proprietario.setNome("nome");
    	proprietario.setSobrenome("sobrenome");
    	proprietario.setTipoPessoa('F');
    	proprietario.setCnpjCpf("00987654321");
    	proprietario.setEmail("proprietario@email.com");
    	proprietario.setTelefone("45 99999-9999");
    	
    	proprietarioService.salvarProprietario(proprietario);
    	
    	GrupoModel grupo = new GrupoModel();
    	grupo.setNome("grupo");
    	grupo.setCodigo("codigoteste");
    	grupo.setSenha("senha");
    	
    	grupoService.salvar(grupo);
    	
		EnderecoModel endereco = new EnderecoModel();
		endereco.setRua("rua a");
		endereco.setNumero(1122);
		endereco.setLoteamento("loateamento a");
		endereco.setCidade("cidade a");
		endereco.setEstado("SP");
		endereco.setCEP("112233");
		
		enderecoService.salvarEndereco(endereco);

        ImovelModel imovel = new ImovelModel();
        imovel.setNome("imovel");
        imovel.setDataCadastro(LocalDateTime.now());
        imovel.setArea(100D);
        imovel.setPreco(2000.0D);
        imovel.setNumeroMatricula("123456789");
        imovel.setValorMatricula(10000D);
        imovel.setIdProprietario(proprietario.getId());
        imovel.setIdGrupo(grupo.getId());
        imovel.setEndereco(endereco);
        imovel.setImagemBase64(null);

        imovelService.salvarImovel(imovel);

        imovelService.apagarImovelPorId(imovel.getId());
        proprietarioService.apagarProprietarioPorId(proprietario.getId());
        grupoService.apagarGrupo(grupo.getId());
        enderecoService.apagarEnderecoPorId(endereco.getId());
    }
}
