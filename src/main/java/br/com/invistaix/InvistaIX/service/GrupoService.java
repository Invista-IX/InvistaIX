package br.com.invistaix.InvistaIX.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.invistaix.InvistaIX.model.GrupoModel;
import br.com.invistaix.InvistaIX.model.UsuarioModel;
import br.com.invistaix.InvistaIX.repository.GrupoRepository;
import br.com.invistaix.InvistaIX.repository.UsuarioRepository;

@Service
public class GrupoService {
	
    @Autowired
    GrupoRepository grupoRepository;
    
    @Autowired
    UsuarioRepository usuarioRepository;
    
    public String salvar(GrupoModel novoGrupo) {
    	try {
    		if (novoGrupo == null) {
    			throw new NullPointerException("Grupo inválido");
    		}
    		grupoRepository.save(novoGrupo);
    		return "Grupo salvo com sucesso";
    	} catch (Exception ex) {
    		throw new RuntimeException("Erro ao salvar grupo: " + ex.getMessage(), ex);
    	}
    }
    
    public String atualizarImagem(Long idGrupo, String base64) {
    	try {
    		if (idGrupo == 0 || idGrupo == null) {
    			throw new IllegalArgumentException("ID do grupo inválido.");
    		}
    		GrupoModel grupo = grupoRepository.findById(idGrupo).orElse(null);
    		if (grupo == null) {
    			throw new NullPointerException("Grupo não encontrado.");
    		}
    		grupo.setImagem_base64(base64);
    		grupoRepository.save(grupo);
    		return "Imagem do grupo atualizada com sucesso";
    	} catch (Exception ex) {
    		throw new RuntimeException("Erro ao atualizar a imagem do grupo: " + ex.getMessage(), ex);
    	}
    }
    
    public List<GrupoModel> listarTodos() {
    	return grupoRepository.findAll();
    }
    
    public GrupoModel encontrarPorId(Long id) {
    	return grupoRepository.findById(id).orElse(null);
    }
    
    public boolean conferirExistencia(GrupoModel grupo) {
    	return grupoRepository.existsById(grupo.getId());
    }
    
    public void apagarGrupo(Long id) {
    	grupoRepository.deleteById(id);
    }
    
    public GrupoModel encontrarPorCodigo(String codigo, String senha) {
    	try {
    		if (codigo.isEmpty() || codigo == null) {
    			throw new IllegalArgumentException("Código inválido.");
    		}
    		GrupoModel grupo = grupoRepository.findByCodigo(codigo).orElse(null);
    		if (grupo == null) {
    			throw new NullPointerException("Grupo não encontrado.");
    		}
    		if (!senha.equals(grupo.getSenha())) {
    			throw new IllegalArgumentException("Senha inválida.");
    		}
    		return grupo;
    	} catch (Exception ex) {
    		throw new RuntimeException("Erro ao buscar grupo: " + ex.getMessage(), ex);
    	}
    }
    
    public String atribuirGrupo(Long idGrupo, Long idUsuario) {
    	try {
    		if (idGrupo == null || idGrupo == 0) {
    			throw new IllegalArgumentException("ID do grupo inválido.");
    		}
    		if (idUsuario == null || idUsuario == 0) {
    			throw new IllegalArgumentException("ID do usuario inválido.");
    		}
    		GrupoModel grupo = grupoRepository.findById(idGrupo).get();
    		UsuarioModel usuario = usuarioRepository.findById(idUsuario).get();
    		if (grupo == null) {
    			throw new NullPointerException("Grupo não encontrado.");
    		}
    		if (usuario == null) {
    			throw new NullPointerException("Usuario não encontrado.");
    		}
    		usuario.adicionarGrupo(grupo);
    		usuarioRepository.save(usuario);
    		System.out.println(usuario);
    		System.out.println(grupo);
    		return "Usuario atribuido ao grupo com sucesso.";
    	} catch (Exception ex) {
    		throw new RuntimeException("Erro ao atribuir usuario: " + ex.getMessage(), ex);
    	}
    }
    
    public String desatribuirGrupo(Long idGrupo, Long idUsuario) {
    	try {
    		if (idGrupo == null || idGrupo == 0) {
    			throw new IllegalArgumentException("ID do grupo inválido.");
    		}
    		if (idUsuario == null || idUsuario == 0) {
    			throw new IllegalArgumentException("ID do usuario inválido.");
    		}
    		GrupoModel grupo = grupoRepository.findById(idGrupo).get();
    		UsuarioModel usuario = usuarioRepository.findById(idUsuario).get();
    		if (grupo == null) {
    			throw new NullPointerException("Grupo não encontrado.");
    		}
    		if (usuario == null) {
    			throw new NullPointerException("Usuario não encontrado.");
    		}
    		usuario.removerGrupo(grupo);
    		usuarioRepository.save(usuario);
    		System.out.println(usuario);
    		System.out.println(grupo);
    		return "Usuario atribuido ao grupo com sucesso.";
    	} catch (Exception ex) {
    		throw new RuntimeException("Erro ao atribuir usuario: " + ex.getMessage(), ex);
    	}
    }
    
    public Set<GrupoModel> encontrarGrupos(Long usuarioId) {
		try {
			if(usuarioId == null || usuarioId == 0) {
				throw new IllegalArgumentException("ID do usuário inválido.");
			}
			UsuarioModel usuario = usuarioRepository.findById(usuarioId).orElse(null);
			if(usuario == null) {
				throw new NullPointerException("Usuario não encontrado.");
			}
			return usuario.getGrupos();
		} catch (Exception ex) {
			throw new RuntimeException("Erro ao buscar grupos: " + ex.getMessage(), ex); 
		}
	}
    
}
