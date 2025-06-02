package br.com.invistaix.InvistaIX.repository;

import br.com.invistaix.InvistaIX.model.InccModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InccRepository extends JpaRepository<InccModel, Long> {

    List<InccModel> findByDataBetween(LocalDate start, LocalDate end);
}
