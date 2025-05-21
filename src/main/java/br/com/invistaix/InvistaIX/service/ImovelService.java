package br.com.invistaix.InvistaIX.service;

import br.com.invistaix.InvistaIX.model.DespesaModel;
import br.com.invistaix.InvistaIX.model.ImovelModel;
import br.com.invistaix.InvistaIX.repository.ImovelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ImovelService {

    @Autowired
    private ImovelRepository imovelRepository;

    public ImovelModel salvarImovel(ImovelModel imovel) {
        try {
            imovel.setDataCadastro(LocalDateTime.now());
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
            if (imovel.getEndereco().getBairro() == null || imovel.getEndereco().getBairro().isEmpty()) {
                throw new IllegalArgumentException("Informe um bairro válido.");
            }
            if (imovel.getEndereco().getLoteamento() == null || imovel.getEndereco().getLoteamento().isEmpty()) {
                throw new IllegalArgumentException("Informe um loteamento válido.");
            }
            if (imovel.getEndereco().getRua() == null || imovel.getEndereco().getRua().isEmpty()) {
                throw new IllegalArgumentException("Informe uma rua válida.");
            }
            if (imovel.getEndereco().getMunicipio() == null || imovel.getEndereco().getMunicipio().isEmpty()) {
                throw new IllegalArgumentException("Informe um município válido.");
            }
            if (imovel.getEndereco().getNumero() == null || imovel.getEndereco().getNumero() <= 0) {
                throw new IllegalArgumentException("Informe um número válido.");
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
}
