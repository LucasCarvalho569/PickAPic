package com.lucas.pickapic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.pickapic.model.Votacao;
import com.lucas.pickapic.resource.VotacaoRepository;

@Service
public class VotacaoService {
	
	@Autowired
	VotacaoRepository votacaoRepository;

	public List<Votacao> getVotacoes(Integer idUsuario) {
		return votacaoRepository.getVotacoes(idUsuario);
	}

	public Votacao setVotacao(Votacao votacao) {
		return votacaoRepository.save(votacao);
	}

	public void deleteVotacao(Votacao votacao) {
		votacaoRepository.delete(votacao);
	}
}
