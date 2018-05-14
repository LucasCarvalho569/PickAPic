package com.lucas.pickapic.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.pickapic.model.Usuario;
import com.lucas.pickapic.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("{id}")
	public Optional<Usuario> getUsuario(@PathVariable("id") Integer id) {
		return usuarioService.getUsuario(id);
	}
	
	@PostMapping()
	public Usuario setUsuario(@RequestBody Usuario usuario) {
		return usuarioService.setUsuario(usuario);
	}
	
}
