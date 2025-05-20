package br.com.invistaix.InvistaIX.controller;

import br.com.invistaix.InvistaIX.model.ImovelModel;
import br.com.invistaix.InvistaIX.model.Usuario;
import br.com.invistaix.InvistaIX.repository.EnderecoRepository;
import br.com.invistaix.InvistaIX.repository.ProprietarioRepository;
import br.com.invistaix.InvistaIX.service.ImovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        model.addAttribute("proprietarios", new ImovelModel());
        return "cadastroImovel";
    }

    @PostMapping("/salvar")
    public String cadastrarImovel(@ModelAttribute ImovelModel imovel) {
        imovelService.salvar(imovel);
        return "redirect:/imovel/cadastroImovel";
    }
}