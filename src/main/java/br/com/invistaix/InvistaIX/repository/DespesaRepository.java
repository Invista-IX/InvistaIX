package br.com.invistaix.InvistaIX.repository;

import br.com.invistaix.InvistaIX.model.DespesaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<DespesaModel, Long> {

    List<DespesaModel> findByIdImovel(Long idImovel);

    List<DespesaModel> findByIdImovelAndDataBetween(Long idImovel, LocalDate startDate, LocalDate endDate);
}
