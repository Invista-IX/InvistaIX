package br.com.invistaix.InvistaIX.service;

import br.com.invistaix.InvistaIX.model.DespesaModel;
import br.com.invistaix.InvistaIX.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    public DespesaModel criarDespesa(DespesaModel despesa) {
        try {
            if (despesa.getManutencao() != null) {
                if (despesa.getManutencao() < 0) {
                    throw new IllegalArgumentException("Valores negativos não são permitidos.");
                } else if (despesa.getManutencao() == 0) {
                    despesa.setManutencao(null);
                }
            }

            if (despesa.getAgua() != null) {
                if (despesa.getAgua() < 0) {
                    throw new IllegalArgumentException("Valores negativos não são permitidos.");
                } else if (despesa.getAgua() == 0) {
                    despesa.setAgua(null);
                }
            }

            if (despesa.getLuz() != null) {
                if (despesa.getLuz() < 0) {
                    throw new IllegalArgumentException("Valores negativos não são permitidos.");
                } else if (despesa.getLuz() == 0) {
                    despesa.setLuz(null);
                }
            }

            if (despesa.getDespesaAvulsa() != null) {
                if (despesa.getDespesaAvulsa() < 0) {
                    throw new IllegalArgumentException("Valores negativos não são permitidos.");
                } else if (despesa.getDespesaAvulsa() == 0) {
                    despesa.setDespesaAvulsa(null);
                }
            }
            if (despesa.getData() == null) {
                throw new IllegalArgumentException("A data deve ser informada.");
            }
            if (despesa.getManutencao() == null
                    && despesa.getAgua() == null
                    && despesa.getLuz() == null
                    && despesa.getDespesaAvulsa() == null) {
                throw new IllegalArgumentException("Informe ao menos um valor de despesa.");
            }

            if(despesaRepository.existsByImovelAndMesAndAno(despesa.getIdImovel(), despesa.getData().getMonthValue(), despesa.getData().getYear())){
                throw new IllegalArgumentException("Já existe despesa no mês.");
            }
            return despesaRepository.save(despesa);

        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao criar despesa: " + ex.getMessage(), ex);
        }
    }

    public List<DespesaModel> listarPorImovel(Long idImovel) {
        try {
            if (idImovel == null || idImovel <= 0) {
                throw new IllegalArgumentException("ID do imóvel inválido.");
            }
            List<DespesaModel> despesas = despesaRepository.findByIdImovel(idImovel);
            return despesas;
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao listar despesas por imóvel: " + ex.getMessage(), ex);
        }
    }

    public List<DespesaModel> listarPorPeriodo(Long idImovel, LocalDate inicio, LocalDate fim) {
        try {
            if (idImovel == null || idImovel <= 0) {
                throw new IllegalArgumentException("ID do imóvel inválido.");
            }
            if (inicio == null || fim == null) {
                throw new IllegalArgumentException("Período inválido: datas devem ser informadas.");
            }
            if (fim.isBefore(inicio)) {
                throw new IllegalArgumentException("Data final deve ser igual ou posterior à data inicial.");
            }
            List<DespesaModel> despesas = despesaRepository.findByIdImovelAndDataBetween(idImovel, inicio, fim);
            return despesas;

        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao listar despesas por período: " + ex.getMessage(), ex);
        }
    }

    public void excluir(Long idDespesa) {
        try {
            if (idDespesa == null || idDespesa <= 0) {
                throw new IllegalArgumentException("ID da despesa inválido.");
            }
            despesaRepository.deleteById(idDespesa);
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao excluir despesa: " + ex.getMessage(), ex);
        }
    }
}
