package br.com.invistaix.InvistaIX.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.invistaix.InvistaIX.model.Grupo;

public interface GrupoRepository extends JpaRepository<Grupo, Integer> {
	boolean existsByCodigo(String codigo);
	Grupo findByCodigo(String codigo);
}
