package br.com.invistaix.InvistaIX.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.invistaix.InvistaIX.model.ProprietarioModel;
import br.com.invistaix.InvistaIX.repository.ProprietarioRepository;

@Service
public class ProprietarioService {

    @Autowired
    private ProprietarioRepository proprietarioRepository;
    
    public ProprietarioModel salvarProprietario(ProprietarioModel proprietario) {
        try {
            if (proprietario.getNome() == null || proprietario.getNome().isEmpty()) {
            	throw new IllegalArgumentException("Informe um nome válido.");
            }
            if (proprietario.getSobrenome() == null || proprietario.getSobrenome().isEmpty()) {
                throw new IllegalArgumentException("Informe um sobrenome válido.");
            }
            if (proprietario.getTipoPessoa() == null || (proprietario.getTipoPessoa() != 'F' && proprietario.getTipoPessoa() != 'J'))  {
                throw new IllegalArgumentException("Informe um tipo de pessoa válido.");
            }
            if (proprietario.getCnpjCpf() == null || proprietario.getCnpjCpf().isEmpty()) {
                throw new IllegalArgumentException("Informe um cpf ou cnpj válido.");
            }
           
            if (proprietario.getEmail() == null || proprietario.getEmail().isEmpty()) {
                throw new IllegalArgumentException("Informe um email válido.");
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
        try {
            return proprietarioRepository.save(proprietario);
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar proprietario: " + ex.getMessage(), ex);
        }
    }
    
    public ProprietarioModel encontrarPorId(Integer id) {
    	try {
    		if(id == null || id <= 0) {
    			throw new IllegalArgumentException("Informe um id válido");
    		}
    		Optional<ProprietarioModel> proprietario = proprietarioRepository.findById(id);
    		return proprietario.orElseThrow(() ->
    			new IllegalArgumentException("Proprietario não encontrado")
    		);
    	} catch (Exception ex) {
    		throw new RuntimeException("Erro ao encontrar proprietario:" + ex.getMessage(), ex);
    	}
    }
    
    public ProprietarioModel encontrarPorCnpjCpf(String cnpjCpf) {
    	try {
    		if(cnpjCpf == null || cnpjCpf.isEmpty()) {
    			throw new IllegalArgumentException("Erro: CPF ou CNPJ inválido");
    		}
    		Optional<ProprietarioModel> proprietario = proprietarioRepository.findByCnpjCpf(cnpjCpf);
    		return proprietario.orElseThrow(() ->
    			new IllegalArgumentException("Proprietario não encontrado")
    		);
    	} catch (Exception ex) {
    		throw new RuntimeException("Erro ao encontrar proprietario:" + ex.getMessage(), ex);
    	}
    }
    
    public String apagarProprietarioPorId(Integer id) {
    	try {
    		if(id == null || id <= 0) {
    			throw new IllegalArgumentException("Informe um id válido");
    		}
    		proprietarioRepository.deleteById(id);
    		return "Proprietario apagado com sucesso";
    	} catch (Exception ex) {
    		throw new RuntimeException("Erro ao apagar proprietario:" + ex.getMessage(), ex);
    	}
    }
}
