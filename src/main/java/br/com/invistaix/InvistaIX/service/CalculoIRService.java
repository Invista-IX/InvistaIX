package br.com.invistaix.InvistaIX.service;

import br.com.invistaix.InvistaIX.model.DespesaModel;
import br.com.invistaix.InvistaIX.model.IRModel;
import br.com.invistaix.InvistaIX.model.ImovelModel;
import br.com.invistaix.InvistaIX.model.ProprietarioModel;
import br.com.invistaix.InvistaIX.repository.DespesaRepository;
import br.com.invistaix.InvistaIX.repository.IRRepository;
import br.com.invistaix.InvistaIX.repository.ImovelRepository;
import br.com.invistaix.InvistaIX.repository.ProprietarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CalculoIRService {

    @Autowired
    private IRRepository IRRepository;

    @Autowired
    private ImovelRepository imovelRepository;

    @Autowired
    private DespesaRepository despesaRepository;

    @Autowired
    private ProprietarioRepository proprietarioRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public IRModel salvarIR(double receita, Long idImovel) {
        ImovelModel imovel = imovelRepository.findById(idImovel)
                .orElseThrow(() -> new RuntimeException("Imóvel não encontrado"));

        if (imovel.getIdProprietario() == null) {
            throw new RuntimeException("Imóvel sem proprietário associado.");
        }

        ProprietarioModel proprietario = proprietarioRepository.findById(imovel.getIdProprietario().intValue())
                .orElseThrow(() -> new RuntimeException("Proprietário não encontrado."));

        double valorIR;

        if (proprietario.getTipoPessoa() == 'F') {
            valorIR = calcularIRPessoaFisica(receita, idImovel);
        } else if (proprietario.getTipoPessoa() == 'J') {
            valorIR = calcularIRPessoaJuridica(receita);
        } else {
            throw new RuntimeException("Tipo de pessoa do proprietário inválido.");
        }

        IRModel ir = new IRModel();
        ir.setValor(valorIR);
        ir.setData(LocalDate.now());
        ir.setImovel(imovel);

        return IRRepository.save(ir);
    }

    public double calcularIRPessoaFisica(double receita, Long idImovel) {
        LocalDate inicioMes = LocalDate.now().withDayOfMonth(1);
        LocalDate fimMes = inicioMes.withDayOfMonth(inicioMes.lengthOfMonth());

        double totalDespesas = despesaRepository.findByIdImovelAndDataBetween(idImovel, inicioMes, fimMes).stream()
                .mapToDouble(d ->
                        (d.getManutencao() != null ? d.getManutencao() : 0.0) +
                                (d.getDespesaAvulsa() != null ? d.getDespesaAvulsa() : 0.0) +
                                (d.getLuz() != null ? d.getLuz() : 0.0) +
                                (d.getAgua() != null ? d.getAgua() : 0.0)
                ).sum();

        double base = receita - totalDespesas;

        if (base <= 2112.00) return 0.0;
        else if (base <= 2826.65) return (base * 0.075) - 158.40;
        else if (base <= 3751.05) return (base * 0.15) - 370.40;
        else if (base <= 4664.68) return (base * 0.225) - 651.73;
        else return (base * 0.275) - 884.96;
    }

    public double calcularIRPessoaJuridica(double receita) {
        double lucroPresumido = receita * 0.32;

        double irpj = lucroPresumido <= 20000
                ? lucroPresumido * 0.15
                : (lucroPresumido * 0.15) + ((lucroPresumido - 20000) * 0.10);

        double csll = lucroPresumido * 0.09;

        return irpj + csll;
    }
}