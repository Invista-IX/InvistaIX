package br.com.invistaix.InvistaIX.controller;

import br.com.invistaix.InvistaIX.model.IRModel;
import br.com.invistaix.InvistaIX.service.CalculoIRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/IR")
public class IRController {

    @Autowired
    private CalculoIRService service;

    @PostMapping("/pf/rendimento={rendimento}&despesas=")
    public ResponseEntity<IRModel> calcularPF(@RequestParam double rendimento,
                                              @RequestParam double despesas,
                                              @RequestParam Long idImovel) {
        double valor = service.calcularIRPessoaFisica(rendimento, despesas);
        IRModel ir = service.salvarIR(valor, idImovel);
        return ResponseEntity.ok(ir);
    }

    @PostMapping("/pj")
    public ResponseEntity<IRModel> calcularPJ(@RequestParam double receita,
                                         @RequestParam Long idImovel) {
        double valor = service.calcularIRPessoaJuridica(receita);
        IRModel ir = service.salvarIR(valor, idImovel);
        return ResponseEntity.ok(ir);
    }
}
