package br.com.invistaix.InvistaIX.repository;

import br.com.invistaix.InvistaIX.model.ImpostoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
    public interface ImpostoRepository extends JpaRepository<ImpostoModel, Long> {
    List<ImpostoModel> findByidimovel(Long idimovel);
    Optional<ImpostoModel> findByIdimovelAndDataBetween(Long idimovel, LocalDate inicioAno, LocalDate fimAno);
    @Query("SELECT COUNT(i) > 0 FROM ImpostoModel i WHERE i.idimovel = :idimovel AND EXTRACT(YEAR FROM i.data) = :ano")
    boolean existsByidimovelAndAno(@Param("idimovel") Long idimovel, @Param("ano") int ano);
}

