package br.com.invistaix.InvistaIX.repository;

import br.com.invistaix.InvistaIX.model.ProprietarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProprietarioRepository  extends JpaRepository<ProprietarioModel, Integer> {

}
