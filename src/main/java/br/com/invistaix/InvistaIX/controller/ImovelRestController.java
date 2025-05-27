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
import br.com.invistaix.InvistaIX.model.ImovelModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cadastroImovel")
public class ImovelRestController {

    @Autowired
    private ImovelService imovelService;

    @Autowired
    private ProprietarioRepository proprietarioRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @PostMapping
    public ResponseEntity<?> salvarImovel(@RequestBody ImovelModel imovel) {
        try {
            ImovelModel imovelSalvo = imovelService.salvarImovel(imovel);
            return ResponseEntity.ok(imovelSalvo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao salvar imóvel: " + e.getMessage());
        }
    }
}
