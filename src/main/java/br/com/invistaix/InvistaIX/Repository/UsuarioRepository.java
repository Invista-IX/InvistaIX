package br.com.invistaix.InvistaIX.Repository;

import br.com.invistaix.InvistaIX.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);
}