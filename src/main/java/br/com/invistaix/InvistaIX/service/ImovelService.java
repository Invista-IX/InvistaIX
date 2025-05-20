package br.com.invistaix.InvistaIX.service;

import br.com.invistaix.InvistaIX.model.ImovelModel;
import br.com.invistaix.InvistaIX.model.Usuario;
import br.com.invistaix.InvistaIX.repository.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImovelService {

    @Autowired
    private ImovelRepository imovelRepository;

    public void salvar(ImovelModel imovel) {
        imovelRepository.save(imovel);
    }
}