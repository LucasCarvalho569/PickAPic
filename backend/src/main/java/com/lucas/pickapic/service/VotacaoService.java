package com.lucas.pickapic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.pickapic.resource.VotacaoRepository;

@Service
public class VotacaoService {
	
	@Autowired
	VotacaoRepository votacaoRepository;
}
