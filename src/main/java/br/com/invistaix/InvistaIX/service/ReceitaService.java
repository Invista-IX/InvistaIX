package br.com.invistaix.InvistaIX.service;

import br.com.invistaix.InvistaIX.model.ReceitaModel;
import br.com.invistaix.InvistaIX.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReceitaService {

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private CalculoIRService calculoIRService;

    public ReceitaModel criarReceita(ReceitaModel receita) {
        try {
            if (receita.getAluguel() != null) {
                if (receita.getAluguel() < 0) {
                    throw new IllegalArgumentException("Valores negativos não são permitidos.");
                } else if (receita.getAluguel() == 0) {
                    receita.setAluguel(null);
                }
            }

            if (receita.getReceitaAvulsa() != null) {
                if (receita.getReceitaAvulsa() < 0) {
                    throw new IllegalArgumentException("Valores negativos não são permitidos.");
                } else if (receita.getReceitaAvulsa() == 0) {
                    receita.setReceitaAvulsa(null);
                }
            }

            if (receita.getData() == null) {
                throw new IllegalArgumentException("A data deve ser informada.");
            }
            if (receita.getAluguel() == null && receita.getReceitaAvulsa() == null) {
                throw new IllegalArgumentException("Informe ao menos um valor de receita.");
            }
            if (receitaRepository.existsByImovelAndMesAndAno(receita.getIdImovel(), receita.getData().getMonthValue(), receita.getData().getYear())) {
                throw new IllegalArgumentException("Já existe receita no mês.");
            }

            double totalReceita =
                    (receita.getAluguel() != null ? receita.getAluguel() : 0.0) +
                            (receita.getReceitaAvulsa() != null ? receita.getReceitaAvulsa() : 0.0);

            calculoIRService.salvarIR(totalReceita, receita.getIdImovel());

            return receitaRepository.save(receita);

        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao criar receita: " + ex.getMessage(), ex);
        }
    }

    public List<ReceitaModel> listarPorImovel(Long idImovel) {
        try {
            if (idImovel == null || idImovel <= 0) {
                throw new IllegalArgumentException("ID do imóvel inválido.");
            }
            List<ReceitaModel> receitas = receitaRepository.findByIdImovel(idImovel);
            return receitas;
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao listar receitas por imóvel: " + ex.getMessage(), ex);
        }
    }

    public List<ReceitaModel> listarPorPeriodo(Long idImovel, LocalDate inicio, LocalDate fim) {
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
            List<ReceitaModel> receitas = receitaRepository.findByIdImovelAndDataBetween(idImovel, inicio, fim);
            return receitas;
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao listar receitas por período: " + ex.getMessage(), ex);
        }
    }

    public void excluir(Long idReceita) {
        try {
            if (idReceita == null || idReceita <= 0) {
                throw new IllegalArgumentException("ID da receita inválido.");
            }
            receitaRepository.deleteById(idReceita);
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao excluir receita: " + ex.getMessage(), ex);
        }
    }
}
