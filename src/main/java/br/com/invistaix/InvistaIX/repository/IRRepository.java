package br.com.invistaix.InvistaIX.repository;

import br.com.invistaix.InvistaIX.model.IRModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRRepository extends JpaRepository<IRModel, Long> {
    List<IRModel> findByImovelId(Long idImovel);
}
