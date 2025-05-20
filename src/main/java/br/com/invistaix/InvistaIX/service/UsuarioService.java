package br.com.invistaix.InvistaIX.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.invistaix.InvistaIX.model.UsuarioModel;
import br.com.invistaix.InvistaIX.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<UsuarioModel> listarTodos() {
		return usuarioRepository.findAll();
	}
	
	public UsuarioModel encontrarPorId(Integer id) {
		return usuarioRepository.findById(id).orElse(null);
	}
	
	public UsuarioModel encontrarPorEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}
	
	public UsuarioModel salvarCadastro(UsuarioModel cadastro) {
		return usuarioRepository.save(cadastro);
	}
	
	public void apagarUsuario(Integer id) {
		usuarioRepository.deleteById(id);
	}
	
	public Boolean checarCPFCadastrado(UsuarioModel usuarioModel) {
		return usuarioRepository.existsByCpfCnpj(usuarioModel.getCpfCnpj());

	}
	
	public Boolean checarEmailCadastrado(UsuarioModel usuarioModel) {
		return usuarioRepository.existsByEmail(usuarioModel.getEmail());
	}
	
	public Boolean checarTelefoneCadastrado(UsuarioModel usuarioModel) {
		return usuarioRepository.existsByEmail(usuarioModel.getTelefone());
	}
}
