package br.com.invistaix.InvistaIX.controller;

import br.com.invistaix.InvistaIX.model.*;
import br.com.invistaix.InvistaIX.repository.EnderecoRepository;
import br.com.invistaix.InvistaIX.repository.ProprietarioRepository;
import br.com.invistaix.InvistaIX.model.ImovelModel;
import br.com.invistaix.InvistaIX.service.ImovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/imovel")
public class ImovelController {

    @Autowired
    private ImovelService imovelService;

    @Autowired
    private ProprietarioRepository proprietarioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping("/cadastroImovel")
    public String formCadastro(Model model) {
        model.addAttribute("imovel", new ImovelModel());
        model.addAttribute("proprietarios", new EnderecoModel());
        return "cadastroImovel";
    }

    @GetMapping("/{idGrupo}/{idImovel}/gerenciar")
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