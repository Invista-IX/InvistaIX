package br.com.invistaix.InvistaIX.repository;

import br.com.invistaix.InvistaIX.model.ImpostoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface ImpostoRepository extends JpaRepository<ImpostoModel, Integer> {
    List<ImpostoModel> findByidimovel(Long idimovel);

}

