package br.com.invistaix.InvistaIX.repository;

import br.com.invistaix.InvistaIX.model.AvaliacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AvaliacaoRepository extends JpaRepository<AvaliacaoModel, Long> {
    List<AvaliacaoModel> findByidimovel(Long idimovel);
    @Query("SELECT COUNT(a) > 0 FROM AvaliacaoModel a WHERE a.idimovel = :idimovel AND EXTRACT(YEAR FROM a.dataAvaliacao) = :ano")
    boolean existsByidimovelAndAno(@Param("idimovel") Long idimovel, @Param("ano") int ano);
}
