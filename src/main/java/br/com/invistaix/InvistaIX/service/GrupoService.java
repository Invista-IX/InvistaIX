package br.com.invistaix.InvistaIX.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.invistaix.InvistaIX.model.Grupo;
import br.com.invistaix.InvistaIX.repository.GrupoRepository;

@Service
public class GrupoService {
	
    @Autowired
    GrupoRepository grupoRepository;
    
    public void salvar(Grupo novoGrupo) {
    	grupoRepository.save(novoGrupo);
    }
    
    public List<Grupo> listarTodos() {
    	return grupoRepository.findAll();
    }
    
    public Grupo encontrarPorId(Integer id) {
    	return grupoRepository.findById(id).orElse(null);
    }
    
    public Grupo encontrarPorCodigo(String codigo) {
    	return grupoRepository.findByCodigo(codigo);
    }
    
    public boolean conferirExistencia(Grupo grupo) {
    	return grupoRepository.existsById(grupo.getId());
    }
    
    public void apagarGrupo(Integer id) {
    	grupoRepository.deleteById(id);
    }
}
