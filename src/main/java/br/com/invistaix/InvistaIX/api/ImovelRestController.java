package br.com.invistaix.InvistaIX.api;

import java.util.List;

import br.com.invistaix.InvistaIX.DTO.PerformanceDTO;
import br.com.invistaix.InvistaIX.DTO.ValorizacaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.invistaix.InvistaIX.model.ImovelModel;
import br.com.invistaix.InvistaIX.service.ImovelService;

@RestController
@RequestMapping("/imovel")
public class ImovelRestController {

    @Autowired
    private ImovelService imovelService;

    @PostMapping("/salvarImovel")
    public ResponseEntity<?> salvarImovel(@ModelAttribute ImovelModel imovel) {
        try {
            ImovelModel imovelSalvo = imovelService.salvarImovel(imovel);
            return ResponseEntity.ok(imovelSalvo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Erro ao salvar imóvel: " + e.getMessage());
        }
    }

    @GetMapping("/findByMatricula={matricula}")
    public ImovelModel encontrarPorMatricula(@PathVariable String matricula) {
        try {
            return imovelService.buscarPorMatricula(matricula);
        } catch (IllegalArgumentException ex) {
            return null;
        } catch (Exception ex) {
            return null;
        }
    }

    @GetMapping("/buscar/performance/{imovelId}")
    public ResponseEntity<?> getPerformance(@PathVariable Long imovelId) {
        try {
            List<PerformanceDTO> resultado = imovelService.buscaPerformance(imovelId);
            return ResponseEntity.ok(resultado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    
    @GetMapping("/grafico/valorizacao/{imovelId}")
    public ResponseEntity<?> getValorizacao(@PathVariable Long imovelId) {
        try {
            List<ValorizacaoDTO> resultado = imovelService.buscaValorizacao(imovelId);
            return ResponseEntity.ok(resultado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    
    @GetMapping("/findAllByGupo={idGrupo}")
    public List<ImovelModel> encontrarPorGrupo(@PathVariable Long idGrupo) {
    	try {
    		return imovelService.buscarImoveisNoGrupo(idGrupo);
    	} catch (IllegalArgumentException ex) {
    		return null;
    	} catch (Exception ex) {
    		throw new RuntimeException("Erro ao buscar imóveis: " + ex.getMessage(), ex);
    	}
    }
    
    @DeleteMapping("/deletar={idImovel}")
    public ResponseEntity<?> excluirImovel(@PathVariable Long idImovel) {
    	try {
    		String resultado = imovelService.apagarImovelPorId(idImovel);
    		return ResponseEntity.ok(resultado);
    	} catch (IllegalArgumentException ex) {
    		return ResponseEntity.badRequest().body(ex.getMessage());
    	} catch (Exception ex) {
    		return ResponseEntity.internalServerError().body("Erro ao salvar imóvel: " + ex.getMessage());
    	}
    }
}

