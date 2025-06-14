package br.com.invistaix.InvistaIX.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.time.YearMonth;
import java.util.*;

import br.com.invistaix.InvistaIX.DTO.PerformanceDTO;
import br.com.invistaix.InvistaIX.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.invistaix.InvistaIX.repository.ImovelRepository;

@Service
public class ImovelService {

    @Autowired
    private ImovelRepository imovelRepository;

    @Autowired
    private DespesaService despesaService;

    @Autowired
    private AvaliacaoService avaliacaoService;

    @Autowired
    private InccService inccService;

    @Autowired
    private ReceitaService receitaService;

    public ImovelModel salvarImovel(ImovelModel imovel) {
        try {
            imovel.setDataCadastro(LocalDateTime.now());

            if (imovel.getNome() == null || imovel.getNome().isEmpty()) {
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
            if (imovel.getIdGrupo() == null || imovel.getIdGrupo() <= 0) {
                throw new IllegalArgumentException("Informe um id de grupo válido");
            }
            if (imovel.getEndereco().getId() == null || imovel.getEndereco().getId() <= 0) {
                throw new IllegalArgumentException("Informe um id endereço válido");
            }
            if (imovel.getIdProprietario() == null || imovel.getIdProprietario() <= 0) {
                throw new IllegalArgumentException("Informe um id de proprietario válido");
            }
            return imovelRepository.save(imovel);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar imóvel: " + ex.getMessage(), ex);
        }
    }

    public List<PerformanceDTO> buscaPerformance(Long imovelid) {
        try {
            if (imovelid <= 0) {
                throw new IllegalArgumentException("id do imovel invalido.");
            }
            Optional<ImovelModel> imovel = imovelRepository.findById(imovelid);
            if (imovel.isPresent()) {
                return retornaPerformance(imovel.get());
            }
            throw new RuntimeException("Imovel não encontrado.");
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public List<PerformanceDTO> retornaPerformance(ImovelModel imovel) {
        try {

            LocalDate hoje = LocalDate.now();
            LocalDate inicio = hoje.minusYears(1).plusDays(1);


            List<DespesaModel> despesas = despesaService.listarPorPeriodo(imovel.getId(), inicio, hoje);
            List<ReceitaModel> receitas = receitaService.listarPorPeriodo(imovel.getId(), inicio, hoje);
            List<AvaliacaoModel> avaliacoes = avaliacaoService.listarPorPeriodo(imovel.getId(), inicio, hoje);
            List<InccModel> inccs = inccService.listarPorPeriodo(inicio, hoje);


            Collections.sort(avaliacoes, Comparator.comparing(AvaliacaoModel::getDataAvaliacao));

            List<PerformanceDTO> resultado = new ArrayList<>();
            double baseMi = 0.0;

            for (int i = 1; i <= 12; i++) {
                YearMonth ym = YearMonth.from(inicio.plusMonths(i - 1));
                LocalDate primeiroDia = ym.atDay(1);


                double somaReceita = 0.0;
                for (ReceitaModel r : receitas) {
                    if (YearMonth.from(r.getData()).equals(ym)) {
                        somaReceita += r.getSoma();
                    }
                }

                double somaDespesa = 0.0;
                for (DespesaModel d : despesas) {
                    if (YearMonth.from(d.getData()).equals(ym)) {
                        somaDespesa += d.getSoma();
                    }
                }

                double valorIncc = 0.0;
                for (InccModel in : inccs) {
                    if (YearMonth.from(in.getData()).equals(ym)) {
                        valorIncc = in.getPorcentagem();
                        break;
                    }
                }

                double valorImovel = imovel.getValorMatricula();
                for (AvaliacaoModel aval : avaliacoes) {
                    YearMonth ymAval = YearMonth.from(aval.getDataAvaliacao());
                    if (ymAval.compareTo(ym) <= 0) {
                        valorImovel = aval.getValorAvaliacao();
                    } else {
                        break;
                    }
                }

                double mi;
                if (somaReceita == 0.0 && somaDespesa == 0.0) {
                    mi = valorImovel * valorIncc;
                } else {
                    mi = somaReceita - somaDespesa + (valorImovel * valorIncc);
                }

                if (i == 1) {
                    baseMi = (mi != 0.0) ? mi : 1.0;
                }

                double porcentagem = (mi / baseMi);
                resultado.add(new PerformanceDTO(Math.round((porcentagem / 1000) * 100.0) / 100.0, primeiroDia, i));
            }

            return resultado;
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
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

    public List<ImovelModel> buscarImoveisNoGrupo(Integer idGrupo) {
        try {
            if (idGrupo == null || idGrupo <= 0) {
                throw new IllegalArgumentException("ID do imóvel inválido.");
            }
            List<ImovelModel> imoveis = imovelRepository.findAllInGrupo(idGrupo);

            return imoveis;
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao buscar imóveis: " + ex.getMessage(), ex);
        }
    }

    public String apagarImovelPorId(Long idImovel) {
        try {
            if (idImovel == null || idImovel <= 0) {
                throw new IllegalArgumentException("ID do imóvel inválido.");
            }
            imovelRepository.deleteById(idImovel);
            return "Imóvel apagado com sucesso";
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao apagar o imóvel: " + ex.getMessage(), ex);
        }
    }

}
