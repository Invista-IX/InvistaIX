package br.com.invistaix.InvistaIX.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.invistaix.InvistaIX.exception.UnauthorizedAccessException;
import br.com.invistaix.InvistaIX.model.DespesaModel;
import br.com.invistaix.InvistaIX.model.EnderecoModel;
import br.com.invistaix.InvistaIX.model.ImovelModel;
import br.com.invistaix.InvistaIX.model.ProprietarioModel;
import br.com.invistaix.InvistaIX.service.ImovelService;


@Controller
@RequestMapping("/imovel")
public class ImovelController {

    @Autowired
    private ImovelService imovelService;

    @GetMapping("/cadastroImovel")
    public String formCadastro(Model model) {
        model.addAttribute("imovel", new ImovelModel());
        model.addAttribute("endereco", new EnderecoModel());
        model.addAttribute("proprietario", new ProprietarioModel());
        return "cadastroImovel";
    }

    @GetMapping("/grupo={idGrupo}&imovel={idImovel}/gerenciar")
    public String gerenciarImovel(@PathVariable Long idGrupo,
                                  @PathVariable Long idImovel,
                                  Model model) {
        try {
            if (idGrupo == null || idGrupo <= 0) {
                throw new IllegalArgumentException("ID do grupo inválido.");
            }
            if (idImovel == null || idImovel <= 0) {
                throw new IllegalArgumentException("ID do imóvel inválido.");
            }
            ImovelModel imovel = imovelService.buscarPorId(idImovel);
            if (imovel == null) {
                throw new IllegalArgumentException("Imóvel com ID " + idImovel + " não encontrado.");
            }
            if (imovel.getIdGrupo() != idGrupo) {
                throw new UnauthorizedAccessException("Acesso negado: Imovél não pertence a esse grupo.");
            }
            DespesaModel despesa = new DespesaModel();
            despesa.setIdImovel(idImovel);
            model.addAttribute("despesa", despesa);
            model.addAttribute("imovel", imovel);
            model.addAttribute("idGrupo", idGrupo);
            return "gerenciarImovel";
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
    }
}

