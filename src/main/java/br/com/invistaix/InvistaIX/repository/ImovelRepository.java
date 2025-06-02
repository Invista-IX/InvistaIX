package br.com.invistaix.InvistaIX.repository;

import br.com.invistaix.InvistaIX.model.ImovelModel;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImovelRepository extends JpaRepository<ImovelModel, Long> {
    @Query("SELECT i FROM ImovelModel i WHERE i.numeroMatricula = :matricula")
    Optional<ImovelModel> findByMatricula(@Param("matricula") String matricula);
    @Query("SELECT i FROM ImovelModel i where i.idGrupo = :idGrupo")
    List<ImovelModel> findAllInGrupo(@Param("idGrupo") Integer idGrupo);
}