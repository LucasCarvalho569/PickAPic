package com.lucas.pickapic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.pickapic.service.VotacaoService;

@RestController
@RequestMapping("/votacao")
public class VotacaoController {

	@Autowired
	private VotacaoService votacaoService;
}
