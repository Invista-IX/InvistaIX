package br.com.invistaix.InvistaIX.api;

import br.com.invistaix.InvistaIX.model.DespesaModel;
import br.com.invistaix.InvistaIX.service.DespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/despesa")
public class DespesaRestController {

    @Autowired
    private DespesaService despesaService;

    @PostMapping("/criar")
    public ResponseEntity<?> cadastrarDespesa(@ModelAttribute DespesaModel despesa) {
        try {
            despesaService.criarDespesa(despesa);
            return ResponseEntity.ok("Sucesso");
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(ex.getMessage());
        }
    }
}
