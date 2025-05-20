package br.com.invistaix.InvistaIX.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.invistaix.InvistaIX.model.GrupoModel;

public interface GrupoRepository extends JpaRepository<GrupoModel, Integer> {
	boolean existsByCodigo(String codigo);
	GrupoModel findByCodigo(String codigo);
}
