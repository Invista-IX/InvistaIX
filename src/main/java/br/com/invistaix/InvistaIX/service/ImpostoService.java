package br.com.invistaix.InvistaIX.service;

import br.com.invistaix.InvistaIX.model.ImpostoModel;
import br.com.invistaix.InvistaIX.repository.ImpostoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ImpostoService {

    @Autowired
    private ImpostoRepository impostoRepository;

    public ImpostoModel criarIptu(ImpostoModel iptu) {
            try {
                if (iptu.getValor() == null || iptu.getValor() <= 0) {
                    throw new IllegalArgumentException("Erro: o valor do IPTU deve ser um número positivo maior que zero.");
                }

            LocalDate data = iptu.getData();
            if (data == null) {
                throw new IllegalArgumentException("O ano deve ser informado.");
            }

            LocalDate inicioAno = LocalDate.of(data.getYear(), 1, 1);
            LocalDate fimAno = LocalDate.of(data.getYear(), 12, 31);

            Optional<ImpostoModel> existente = impostoRepository.findByIdimovelAndDataBetween(
                    iptu.getIdimovel(), inicioAno, fimAno
            );

            if(impostoRepository.existsByidimovelAndAno(iptu.getIdimovel(), iptu.getData().getYear())){
                    throw new IllegalArgumentException("Já existe um IPTU cadastrado para este imóvel neste ano.");
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


