package br.com.invistaix.InvistaIX.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.invistaix.InvistaIX.model.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
    boolean existsByEmail(String email);
    UsuarioModel findByEmail(String email);
    boolean existsByTelefone(String telefone);
    UsuarioModel findByTelefone(String telefone);
    boolean existsByCpfCnpj(String CpfCnpj);
    UsuarioModel findByCpfCnpj(String CpfCnpj);
}