package com.lucas.pickapic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.pickapic.model.Votacao;
import com.lucas.pickapic.service.VotacaoService;

@RestController
@RequestMapping("/votacao")
public class VotacaoController {

	@Autowired
	private VotacaoService votacaoService;
	
	@GetMapping("minhas-votacoes/{idUsuario}")
	public List<Votacao> getMinhasVotacoes(@PathVariable("idUsuario") Integer idUsuario) {
		return votacaoService.getMinhasVotacoes(idUsuario);
	}
	
	@GetMapping("todas-votacoes/{idUsuario}")
	public List<Votacao> getVotacoes(@PathVariable("idUsuario") Integer idUsuario) {
		return votacaoService.getVotacoes(idUsuario);
	}
	
	@PostMapping
	public Votacao setVotacao(@RequestBody Votacao votacao) {
		return votacaoService.setVotacao(votacao);
	}
	
	@DeleteMapping("{id}")
	public void deleteVotacao(@PathVariable("id") Integer id) {
		votacaoService.deleteVotacao(id);
	}
}
