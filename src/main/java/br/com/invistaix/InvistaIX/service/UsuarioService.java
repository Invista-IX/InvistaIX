package br.com.invistaix.InvistaIX.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.invistaix.InvistaIX.model.Usuario;
import br.com.invistaix.InvistaIX.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> listarTodos() {
		return usuarioRepository.findAll();
	}
	
	public Usuario encontrarPorId(Integer id) {
		return usuarioRepository.findById(id).orElse(null);
	}
	
	public Usuario encontrarPorEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
	
	public Usuario salvarCadastro(Usuario cadastro) {
		return usuarioRepository.save(cadastro);
	}
	
	public void apagarUsuario(Integer id) {
		usuarioRepository.deleteById(id);
	}
	
	public Boolean checarCPFCadastrado(Usuario usuario) {
		return usuarioRepository.existsByCpfCnpj(usuario.getCpfCnpj());
	}
	
	public Boolean checarEmailCadastrado(Usuario usuario) {
		return usuarioRepository.existsByEmail(usuario.getEmail());
	}
	
	public Boolean checarTelefoneCadastrado(Usuario usuario) {
		return usuarioRepository.existsByEmail(usuario.getTelefone());
	}
}
