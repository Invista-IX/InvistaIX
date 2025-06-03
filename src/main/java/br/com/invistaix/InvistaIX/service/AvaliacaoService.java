package br.com.invistaix.InvistaIX.service;

import br.com.invistaix.InvistaIX.model.AvaliacaoModel;
import br.com.invistaix.InvistaIX.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public AvaliacaoModel criarAvaliacao(AvaliacaoModel avaliacaoModel) {
        try {
            if (avaliacaoModel.getValorAvaliacao() == null || avaliacaoModel.getValorAvaliacao() <= 0) {
                throw new IllegalArgumentException("Erro: o valor deve ser um número positivo maior que zero.");
            }

            LocalDate data = avaliacaoModel.getDataAvaliacao();
            if (data == null) {
                throw new IllegalArgumentException("O ano deve ser informado.");
            }

            LocalDate inicioAno = LocalDate.of(data.getYear(), 1, 1);
            LocalDate fimAno = LocalDate.of(data.getYear(), 12, 31);


            avaliacaoRepository.save(avaliacaoModel);
            return avaliacaoModel;

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao criar avaliação: " + ex.getMessage(), ex);
        }
    }

    public AvaliacaoModel buscarPorId(Long id) {
        return avaliacaoRepository.findById(id).orElse(null);
    }

    public List<AvaliacaoModel> listarPorImovel(Long idimovel) {
        try {
            if (idimovel == null || idimovel <= 0) {
                throw new IllegalArgumentException("ID do imóvel inválido.");
            }
            return avaliacaoRepository.findByidimovel(idimovel);
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao listar avaliações por imóvel: " + ex.getMessage(), ex);
        }
    }
}
