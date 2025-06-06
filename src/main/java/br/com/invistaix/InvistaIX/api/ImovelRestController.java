package br.com.invistaix.InvistaIX.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    
    @GetMapping("/findAllByGupo={idGrupo}")
    public List<ImovelModel> encontrarPorGrupo(@PathVariable Integer idGrupo) {
    	try {
    		return imovelService.buscarImoveisNoGrupo(idGrupo);
    	} catch (IllegalArgumentException ex) {
    		return null;
    	} catch (Exception ex) {
    		return null;
    	}
    }
}

