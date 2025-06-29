package br.com.invistaix.InvistaIX.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.invistaix.InvistaIX.model.GrupoModel;

@Repository
public interface GrupoRepository extends JpaRepository<GrupoModel, Long> {
	boolean existsByCodigo(String codigo);
	Optional<GrupoModel> findByCodigo(String codigo);
}
