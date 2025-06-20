package br.com.invistaix.InvistaIX.service;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.invistaix.InvistaIX.model.EnderecoModel;
import br.com.invistaix.InvistaIX.model.ImovelModel;
import br.com.invistaix.InvistaIX.repository.EnderecoRepository;
import br.com.invistaix.InvistaIX.repository.ImovelRepository;
import jakarta.transaction.Transactional;

@Service
public class ImovelService {

    @Autowired
    private ImovelRepository imovelRepository;
    
    @Autowired 
    private EnderecoRepository enderecoRepository;

    public ImovelModel salvarImovel(ImovelModel imovel) {
        try {
            imovel.setDataCadastro(LocalDateTime.now());
            System.out.println(imovel.toString());
            if (imovel.getNome() == null || imovel.getNome().isEmpty() ) {
                throw new IllegalArgumentException("Informe um nome válido.");
            }
            if (imovel.getValorMatricula() == null || imovel.getValorMatricula() <= 0) {
                throw new IllegalArgumentException("Informe um valor da matrícula válido.");
            }
            if (imovel.getNumeroMatricula() == null || imovel.getNumeroMatricula().isEmpty()) {
                throw new IllegalArgumentException("Informe o número da matrícula válido.");
            }
            if (imovel.getArea() == null || imovel.getArea() <= 0) {
                throw new IllegalArgumentException("Informe a área do imóvel válido.");
            }
            if(imovel.getIdGrupo() == null || imovel.getIdGrupo() <= 0) {
            	throw new IllegalArgumentException("Informe um id de grupo válido");
            }
            if(imovel.getEndereco().getId() == null || imovel.getEndereco().getId() <=0) {
            	throw new IllegalArgumentException("Informe um id endereço válido");
            }
            if(imovel.getIdProprietario() == null || imovel.getIdProprietario() <=0) {
            	throw new IllegalArgumentException("Informe um id de proprietario válido");
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage(), ex);
        }
        try {
            return imovelRepository.save(imovel);
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar imóvel: " + ex.getMessage(), ex);
        }

    }

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

    public ImovelModel buscarPorMatricula(String matricula) {
    	try {
    		if (matricula == null || matricula.isEmpty()) {
                throw new IllegalArgumentException("Matricula do imóvel inválido.");
            }
    		Optional<ImovelModel> imovel = imovelRepository.findByMatricula(matricula);
    		return imovel.orElseThrow(() ->
    			new IllegalArgumentException("Imóvel com matricula " + matricula + " não encontrado")
    		);
    	} catch (Exception ex) {
    		throw new RuntimeException("Erro ao buscar imóvel: " + ex.getMessage(), ex);
    	}
    }
    
    @Transactional
    public List<ImovelModel> buscarImoveisNoGrupo(Long idGrupo) {
    	try {
    		if (idGrupo == null || idGrupo <= 0) {
                throw new IllegalArgumentException("ID do imóvel inválido.");
            }
    		List<ImovelModel> imoveis = imovelRepository.findAllInGrupo(idGrupo);
    		System.out.println(imoveis);
    		System.out.println("pindamonahngaba");
    		return imoveis;
    	} catch (Exception ex) {
    		throw new RuntimeException("Erro ao buscar imóveis: " + ex.getMessage(), ex);
    	}
    }
    
    public List<ImovelModel> buscarTodos() {
    	return imovelRepository.findAll();
    }
    
    public String apagarImovelPorId(Long idImovel) {
    	try {
    		if (idImovel == null || idImovel <= 0) {
                throw new IllegalArgumentException("ID do imóvel inválido.");
            }
    		ImovelModel imovel = imovelRepository.findById(idImovel).orElse(null);
    		if (imovel == null) {
    			throw new IllegalArgumentException("Imóvel não encontrado");
    		}
    		EnderecoModel endereco = enderecoRepository.findById(imovel.getEndereco().getId()).orElse(null);
    		if (endereco == null) {
    			throw new IllegalArgumentException("Endereco não encontrado");
    		}
    		imovelRepository.deleteById(idImovel);
    		enderecoRepository.deleteById(endereco.getId());
    		return "Imóvel apagado com sucesso";
    	} catch (Exception ex) {
    		throw new RuntimeException("Erro ao apagar o imóvel: " + ex.getMessage(), ex);
    	}
    }

}
