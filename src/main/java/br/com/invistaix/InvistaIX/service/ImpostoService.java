package br.com.invistaix.InvistaIX.service;

import br.com.invistaix.InvistaIX.model.ImpostoModel;
import br.com.invistaix.InvistaIX.repository.ImpostoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ImpostoService {

    @Autowired
    private ImpostoRepository impostoRepository;

    public ImpostoModel criarIptu(ImpostoModel iptu) {
        try {
            if (iptu.getValor() == null || iptu.getValor() <= 0) {
                throw new IllegalArgumentException("Valor do IPTU deve ser maior que zero.");
            }

            return impostoRepository.save(iptu);

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao criar IPTU: " + ex.getMessage(), ex);
        }
    }

    public List<ImpostoModel> listarPorImovel(Long idimovel) {
        try {
            if (idimovel == null || idimovel <= 0) {
                throw new IllegalArgumentException("ID do imóvel inválido.");
            }
            return impostoRepository.findByidimovel(idimovel);
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao listar IPTUs por imóvel: " + ex.getMessage(), ex);
        }
    }
}


