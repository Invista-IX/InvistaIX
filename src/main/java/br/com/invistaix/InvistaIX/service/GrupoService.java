package br.com.invistaix.InvistaIX.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.invistaix.InvistaIX.model.GrupoModel;
import br.com.invistaix.InvistaIX.repository.GrupoRepository;

@Service
public class GrupoService {
	
    @Autowired
    GrupoRepository grupoRepository;
    
    public void salvar(GrupoModel novoGrupo) {
    	grupoRepository.save(novoGrupo);
    	System.out.println(novoGrupo.toString());
    }
    
    public List<GrupoModel> listarTodos() {
    	return grupoRepository.findAll();
    }
    
    public GrupoModel encontrarPorId(Integer id) {
    	return grupoRepository.findById(id).orElse(null);
    }
    
    public GrupoModel encontrarPorCodigo(String codigo) {
    	return grupoRepository.findByCodigo(codigo);
    }
    
    public boolean conferirExistencia(GrupoModel grupo) {
    	return grupoRepository.existsById(grupo.getId());
    }
    
    public void apagarGrupo(Integer id) {
    	grupoRepository.deleteById(id);
    }
}
