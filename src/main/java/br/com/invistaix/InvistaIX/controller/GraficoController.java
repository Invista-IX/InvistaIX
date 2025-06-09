package br.com.invistaix.InvistaIX.controller;

import br.com.invistaix.InvistaIX.model.GraficoModel;
import br.com.invistaix.InvistaIX.service.GraficoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GraficoController {
    @GetMapping("/graficos")
    public String exibirGraficos(){
        return "graficos";
    }
//    @Autowired
//    private GraficoService service;
//
//    @GetMapping("/graficos")
//    public String mostrarGraficos(Model model) {
//        GraficoModel receitaDespesa = service.buscarDadosGrafico();
//        model.addAttribute("meses", receitaDespesa.getMeses());
//        model.addAttribute("receita", receitaDespesa.getReceita());
//        model.addAttribute("despesa", receitaDespesa.getDespesa());
//        return "graficos";
//    }
}
