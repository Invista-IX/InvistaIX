package br.com.invistaix.InvistaIX.service;

import br.com.invistaix.InvistaIX.model.ProprietarioModel;
import br.com.invistaix.InvistaIX.repository.ProprietarioRepository;
import br.com.invistaix.InvistaIX.repository.ProprietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProprietarioService {

    @Autowired
    private ProprietarioRepository proprietarioRepository;

}
