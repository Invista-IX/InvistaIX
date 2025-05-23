package br.com.invistaix.InvistaIX.controller;

import br.com.invistaix.InvistaIX.model.ReceitaModel;
import br.com.invistaix.InvistaIX.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/receita")
public class ReceitaController {

    @Autowired
    private ReceitaService receitaService;

    @PostMapping("/criar")
    public ResponseEntity<?> cadastrarReceita(@ModelAttribute ReceitaModel receita) {
        try {
            receitaService.criarReceita(receita);
            return ResponseEntity.ok("Sucesso");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(ex.getMessage());
        }
    }
}
