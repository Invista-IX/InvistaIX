package br.com.invistaix.InvistaIX.controller;

import br.com.invistaix.InvistaIX.model.ImpostoModel;
import br.com.invistaix.InvistaIX.service.ImpostoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/iptu")
public class ImpostoController {

    @Autowired
    private ImpostoService iptuService;

    @PostMapping("/criar")
    public ResponseEntity<?> cadastrarIptu(@ModelAttribute ImpostoModel iptu) {
        try {
            if (iptu.getData() == null) {
                iptu.setData(LocalDate.now());
            }

            iptuService.criarIptu(iptu);
            return ResponseEntity.ok("Sucesso");

        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());

        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Erro ao salvar IPTU: " + ex.getMessage());
        }
    }
}
