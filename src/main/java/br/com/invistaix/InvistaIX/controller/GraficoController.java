package br.com.invistaix.InvistaIX.controller;

import br.com.invistaix.InvistaIX.model.GraficoModel;
import br.com.invistaix.InvistaIX.service.GraficoService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/graficos")
public class GraficoController {

    private final GraficoService service;

    public GraficoController(GraficoService service) {
        this.service = service;
    }

    @GetMapping("/ReceitaDespesa/{idImovel}")
    public GraficoModel getReceitaDespesa(@PathVariable Long idImovel) {
        int anoAtual = LocalDate    .now().getYear();
        return service.montarGrafico(idImovel, anoAtual);
    }

}