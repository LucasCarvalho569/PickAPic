package com.lucas.pickapic.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.pickapic.model.Votacao;
import com.lucas.pickapic.service.VotacaoService;

@RestController
@RequestMapping("/votacao")
public class VotacaoController {

	@Autowired
	private VotacaoService votacaoService;
	
	@GetMapping("{idUsuario}")
	public Optional<List<Votacao>> getVotacoes(@PathVariable Integer idUsuario) {
		return votacaoService.getVotacoes(idUsuario);
	}
}
