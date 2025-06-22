package br.com.invistaix.InvistaIX.repository;
import br.com.invistaix.InvistaIX.model.ReceitaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReceitaRepository extends JpaRepository<ReceitaModel, Long> {
    List<ReceitaModel> findByIdImovel(Long idImovel);
    List<ReceitaModel> findByIdImovelAndDataBetween(Long idImovel, LocalDate startDate, LocalDate endDate);
    @Query("SELECT COUNT(d) > 0 FROM ReceitaModel d WHERE d.idImovel = :idImovel AND EXTRACT(MONTH FROM d.data) = :mes AND EXTRACT(YEAR FROM d.data) = :ano")
    boolean existsByImovelAndMesAndAno(@Param("idImovel") Long idImovel, @Param("mes") int mes, @Param("ano") int ano);
}
