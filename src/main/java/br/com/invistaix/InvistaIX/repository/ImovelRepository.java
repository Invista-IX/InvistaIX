package br.com.invistaix.InvistaIX.repository;

import br.com.invistaix.InvistaIX.model.ImovelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImovelRepository extends JpaRepository<ImovelModel, Long> {
}
