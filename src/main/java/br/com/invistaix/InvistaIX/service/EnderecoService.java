package br.com.invistaix.InvistaIX.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.invistaix.InvistaIX.model.EnderecoModel;
import br.com.invistaix.InvistaIX.repository.EnderecoRepository;

@Service
public class EnderecoService {
	
	@Autowired
	EnderecoRepository enderecoRepository;
	
	public EnderecoModel salvarEndereco(EnderecoModel endereco) {
        try {
            if (endereco.getRua() == null || endereco.getRua().isEmpty()) {
            	throw new IllegalArgumentException("Informe um valor da matrícula válido.");
            }
            if (endereco.getNumero() == null || endereco.getNumero() <= 0) {
                throw new IllegalArgumentException("Informe um valor da matrícula válido.");
            }
            if (endereco.getLoteamento() == null || endereco.getLoteamento().isEmpty()) {
                throw new IllegalArgumentException("Informe um nome válido.");
            }
            if (endereco.getCidade() == null || endereco.getCidade().isEmpty()) {
                throw new IllegalArgumentException("Informe o número da matrícula válido.");
            }
            if (endereco.getEstado() == null || endereco.getEstado().isEmpty()) {
                throw new IllegalArgumentException("Informe a área do imóvel válido.");
            }
            if (endereco.getCEP() == null || endereco.getCEP().isEmpty()) {
            	throw new  IllegalArgumentException("Informe um CEP valido");
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
        try {
            return enderecoRepository.save(endereco);
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar endereco: " + ex.getMessage(), ex);
        }
    }
	
	public EnderecoModel encontrarPorId(Integer id) {
		try {
			if(id == null || id <= 0) {
				throw new IllegalArgumentException("ID do endereço inválido.");
			}
			Optional<EnderecoModel> optional = enderecoRepository.findById(id);
			return optional.orElseThrow(() ->
            	new IllegalArgumentException("Endereço com ID " + id + " não encontrado.")
			);
		} catch (Exception ex) {
			throw new RuntimeException("Erro ao buscar endereco: " + ex.getMessage(), ex);
		}
	}
	
	public EnderecoModel encontrarEndereco(String rua, Integer numero, String loteamento, String cidade, String estado, String CEP) {
		try {
			Optional<EnderecoModel> endereco = enderecoRepository.findEndereco(rua, numero, loteamento, cidade, estado, CEP);
			return endereco.orElseThrow(() ->
            	new IllegalArgumentException("Endereço não encontrado.")
			);
		} catch (Exception ex) {
			throw new RuntimeException("Erro ao buscar enderecç: " + ex.getMessage(), ex);
		}
	}
	
	public String apagarEnderecoPorId(Integer id) {
		try {
			if(id == null || id <= 0) {
				throw new IllegalArgumentException("ID do endereço inválido.");
			}
			enderecoRepository.deleteById(id);
			return "Endereço apagado com sucesso";
		} catch (Exception ex) {
			throw new RuntimeException("Erro ao apagar endereço: " + ex.getMessage(), ex);
		}
	}
}
