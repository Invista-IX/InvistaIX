package br.com.invistaix.InvistaIX.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.invistaix.InvistaIX.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    boolean existsByEmail(String email);
    Usuario findByEmail(String email);
    boolean existsByTelefone(String telefone);
    Usuario findByTelefone(String telefone);
    boolean existsByCpfCnpj(String CpfCnpj);
    Usuario findByCpfCnpj(String CpfCnpj);
}