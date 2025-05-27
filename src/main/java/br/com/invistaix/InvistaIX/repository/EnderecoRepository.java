package br.com.invistaix.InvistaIX.repository;

import br.com.invistaix.InvistaIX.model.EnderecoModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EnderecoRepository extends JpaRepository<EnderecoModel, Integer> {
	@Query("SELECT e FROM EnderecoModel e WHERE e.rua = :rua AND e.numero = :numero AND e.loteamento = :loteamento AND e.cidade = :cidade AND e.estado = :estado AND e.CEP = :CEP")
    Optional<EnderecoModel> findEndereco(@Param("rua") String rua, @Param("numero") int numero, @Param("loteamento") String loteamento, @Param("cidade") String cidade, @Param("estado") String estado, @Param("CEP") String CEP);
}
