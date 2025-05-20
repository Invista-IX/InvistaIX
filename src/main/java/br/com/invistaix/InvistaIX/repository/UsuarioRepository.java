package br.com.invistaix.InvistaIX.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.invistaix.InvistaIX.model.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Integer> {
    boolean existsByEmail(String email);
    UsuarioModel findByEmail(String email);
    boolean existsByTelefone(String telefone);
    UsuarioModel findByTelefone(String telefone);
    boolean existsByCpfCnpj(String CpfCnpj);
    UsuarioModel findByCpfCnpj(String CpfCnpj);
}