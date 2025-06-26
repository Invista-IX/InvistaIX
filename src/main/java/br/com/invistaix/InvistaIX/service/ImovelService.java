package br.com.invistaix.InvistaIX.service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;
import java.time.YearMonth;
import java.util.*;

import br.com.invistaix.InvistaIX.DTO.PerformanceDTO;
import br.com.invistaix.InvistaIX.DTO.ValorizacaoDTO;
import br.com.invistaix.InvistaIX.model.*;
import br.com.invistaix.InvistaIX.DTO.PerformanceDTO;
import br.com.invistaix.InvistaIX.model.DespesaModel;
import br.com.invistaix.InvistaIX.model.ReceitaModel;
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
    
    public List<ValorizacaoDTO> buscaValorizacao(Long imovelid) {
        try {
            if (imovelid <= 0) {
                throw new IllegalArgumentException("id do imovel invalido.");
            }
            ImovelModel imovel = imovelRepository.findById(imovelid).orElse(null);
            if (imovel == null) {
            	throw new RuntimeException("Imovel não encontrado.");
            }
            return retornaValorizacao(imovel);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
    
    public List<ValorizacaoDTO> retornaValorizacao(ImovelModel imovel) {
        try {
            LocalDate hoje = LocalDate.now();
            YearMonth inicioAno = YearMonth.from(hoje);
            YearMonth dataCadastro = YearMonth.from(imovel.getDataCadastro());
            
            while (inicioAno.getMonth().getValue() != 1) {
            	inicioAno = inicioAno.minusMonths(1);
            }
            
            List<AvaliacaoModel> avaliacoes = avaliacaoService.listarPorPeriodo(imovel.getId(), inicioAno.atDay(1), hoje);
            List<InccModel> inccs = inccService.listarPorPeriodo(inicioAno.atDay(1), hoje);

            Collections.sort(avaliacoes, Comparator.comparing(AvaliacaoModel::getDataAvaliacao));
            Collections.sort(inccs, Comparator.comparing(InccModel::getData));
            
            /*
            System.out.println("------------------\n data-referência: " + inicioAno + "\n------------------");
            for (InccModel in : inccs) {
            	System.out.println("incc=[valor: " + in.getPorcentagem() + ", data: " + YearMonth.from(in.getData()) + "];");
            }
            */

            List<ValorizacaoDTO> resultado = new ArrayList<>();
            double base = imovel.getValorMatricula();

            for (int i = 1; i <= 12; i++) {
                double valorIncc = 0.0;
                for (InccModel in : inccs) {
                    if (YearMonth.from(in.getData()).equals(inicioAno.plusMonths(i-1))) {
                        valorIncc = in.getPorcentagem();
                        break;
                    }
                }

                double valorImovel = base;
                for (AvaliacaoModel aval : avaliacoes) {
                    YearMonth ymAval = YearMonth.from(aval.getDataAvaliacao());
                    if (ymAval.compareTo(inicioAno.plusMonths(i)) <= 0 && valorImovel <= aval.getValorAvaliacao() && imovel.getId() == aval.getIdimovel()) {
                    	//System.out.println("###############\n data-referencia: " + inicioAno.plusMonths(i) + "\n data-avaliacao: " + ymAval + "\n id-imovel: " + imovel.getId() + "\n avaliacao-id-imovel: " + aval.getIdimovel() + "\n###############");
                    	valorImovel = aval.getValorAvaliacao();
                    } else {
                        break;
                    }
                }
                
                //System.out.println("[data-cadastro: " + dataCadastro + "| data-mes: " + inicioAno.plusMonths(i-1) + "]");
                if(dataCadastro.compareTo(inicioAno.plusMonths(i-1)) >= 0) {
                    //System.out.println("$$$$$$$$$$$$$$$$\n calculo mes " + i + ":\n valor base: " + valorImovel + "\n valor incc: " + (valorIncc / 100) + "\n calculo: " + valorImovel + " + (" + valorImovel + " * " + (valorIncc / 100) + ") \n$$$$$$$$$$$$$$$$");
                    valorImovel = valorImovel + (valorImovel * (valorIncc / 100));
                }
                
                DecimalFormat formatador = new DecimalFormat("#0.00");
                //System.out.println("valor mes " + i + ": " + formatador.format(valorImovel).replace(',', '.'));
                
                if (valorImovel != base) {
                	resultado.add(new ValorizacaoDTO(formatador.format(valorImovel).replace(',', '.'), inicioAno.plusMonths(i).atDay(1), i));
                } else {
                	resultado.add(new ValorizacaoDTO(formatador.format(0.00).replace(',', '.'), inicioAno.plusMonths(i).atDay(1), i));
                }
                
                base = valorImovel;
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
    
    @Transactional
    public List<ImovelModel> buscarImoveisNoGrupo(Long idGrupo) {
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
