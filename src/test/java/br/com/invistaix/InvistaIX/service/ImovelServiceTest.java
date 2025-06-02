package br.com.invistaix.InvistaIX.service;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.invistaix.InvistaIX.model.EnderecoModel;
import br.com.invistaix.InvistaIX.model.ImovelModel;

@SpringBootTest
@AutoConfigureMockMvc
public class ImovelServiceTest {

    @Autowired
    ImovelService imovelService;

    @Autowired
    EnderecoService enderecoService;

    @Test
    void CadastrarEApagarImovel() {
		/*
		EnderecoModel endereco = new EnderecoModel();
		endereco.setId(100);
		endereco.setRua("rua a");
		endereco.setNumero(1122);
		endereco.setLoteamento("loateamento a");
		endereco.setCidade("cidade a");
		endereco.setEstado("SP");
		endereco.setCEP("112233");
		*/

        EnderecoModel endereco = enderecoService.encontrarPorId(1);

        ImovelModel imovel = new ImovelModel();
        imovel.setNome("imovel");
        imovel.setDataCadastro(LocalDateTime.now());
        imovel.setArea(100D);
        imovel.setPreco(2000.0D);
        imovel.setNumeroMatricula("123456789");
        imovel.setValorMatricula(10000D);
        imovel.setIdProprietario(1L);
        imovel.setIdGrupo(1L);
        imovel.setEndereco(endereco);
        imovel.setImagemBase64(null);

        imovelService.salvarImovel(imovel);

        imovelService.apagarImovelPorId(imovel.getId());
    }
}
