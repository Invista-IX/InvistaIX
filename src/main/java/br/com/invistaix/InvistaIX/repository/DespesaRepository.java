package br.com.invistaix.InvistaIX.repository;

import br.com.invistaix.InvistaIX.model.DespesaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<DespesaModel, Long> {
    List<DespesaModel> findByIdImovel(Long idImovel);
    List<DespesaModel> findByIdImovelAndDataBetween(Long idImovel, LocalDate startDate, LocalDate endDate);
    @Query("SELECT COUNT(d) > 0 FROM DespesaModel d WHERE d.idImovel = :idImovel AND EXTRACT(MONTH FROM d.data) = :mes AND EXTRACT(YEAR FROM d.data) = :ano")
    boolean existsByImovelAndMesAndAno(@Param("idImovel") Long idImovel, @Param("mes") int mes, @Param("ano") int ano);
}
