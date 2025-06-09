package br.com.invistaix.InvistaIX.api;

import br.com.invistaix.InvistaIX.model.AvaliacaoModel;
import br.com.invistaix.InvistaIX.service.AvaliacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;
import java.util.Base64;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoRestController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @PostMapping("/rest/criar")
    public ResponseEntity<?> cadastrarAvaliacao(@ModelAttribute AvaliacaoModel avaliacaoModel) {
        try {
            MultipartFile file = avaliacaoModel.getDocAvaliacaoFile();

            if (file == null || file.isEmpty() || !file.getContentType().equals("application/pdf")) {
                return ResponseEntity.badRequest().body("Apenas arquivos PDF são permitidos.");
            }

            byte[] bytes = file.getBytes();
            String base64 = Base64.getEncoder().encodeToString(bytes);
            avaliacaoModel.setDocAvaliacao(base64);

            if (avaliacaoModel.getDataAvaliacao() == null) {
                avaliacaoModel.setDataAvaliacao(LocalDate.now());
            }

            avaliacaoService.criarAvaliacao(avaliacaoModel);
            return ResponseEntity.ok(avaliacaoModel.getIdavaliacao());

        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());

        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Erro ao salvar avaliação: " + ex.getMessage());
        }
    }

    @GetMapping("/rest/base64")
    public ResponseEntity<String> getPdfBase64(@RequestParam Long idAvaliacao) {
        AvaliacaoModel avaliacao = avaliacaoService.buscarPorId(idAvaliacao);
        if (avaliacao == null || avaliacao.getDocAvaliacao() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(avaliacao.getDocAvaliacao());
    }

}
