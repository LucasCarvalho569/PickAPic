package com.lucas.pickapic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.pickapic.model.Usuario;
import com.lucas.pickapic.resource.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;

	public List<Usuario> getUsuarios() {
		return (List<Usuario>) usuarioRepository.findAll();
	}

	public Optional<Usuario> getUsuario(Integer id) {
		return usuarioRepository.findById(id);
	}

	public Usuario setUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
}
