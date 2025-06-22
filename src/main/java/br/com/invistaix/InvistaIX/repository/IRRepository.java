package br.com.invistaix.InvistaIX.repository;

import br.com.invistaix.InvistaIX.model.IRModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRRepository extends JpaRepository<IRModel, Long> {
    List<IRModel> findByImovelId(Long idImovel);
}
