package br.com.invistaix.InvistaIX.repository;

import br.com.invistaix.InvistaIX.model.ImpostoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
    public interface ImpostoRepository extends JpaRepository<ImpostoModel, Integer> {
    List<ImpostoModel> findByidimovel(Long idimovel);

    Optional<ImpostoModel> findByIdimovelAndDataBetween(Long idimovel, LocalDate inicioAno, LocalDate fimAno);

}

