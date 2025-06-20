package br.com.invistaix.InvistaIX.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.invistaix.InvistaIX.model.ProprietarioModel;

@Repository
public interface ProprietarioRepository  extends JpaRepository<ProprietarioModel, Long> {
	@Query("SELECT p FROM ProprietarioModel p WHERE p.cnpjCpf = :cnpjCpf")
    Optional<ProprietarioModel> findByCnpjCpf(@Param("cnpjCpf") String cnpjCpf);
}
