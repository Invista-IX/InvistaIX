package br.com.invistaix.InvistaIX.service;

import br.com.invistaix.InvistaIX.model.ImovelModel;
import br.com.invistaix.InvistaIX.repository.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ImovelService {

    @Autowired
    private ImovelRepository imovelRepository;

    public ImovelModel buscarPorId(Long idImovel) {
        try {
            if (idImovel == null || idImovel <= 0) {
                throw new IllegalArgumentException("ID do imóvel inválido.");
            }
            Optional<ImovelModel> optional = imovelRepository.findById(idImovel);
            return optional.orElseThrow(() ->
                    new IllegalArgumentException("Imóvel com ID " + idImovel + " não encontrado.")
            );
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao buscar imóvel: " + ex.getMessage(), ex);
        }
    }
}
