package com.lucas.pickapic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lucas.pickapic.model.Foto;
import com.lucas.pickapic.service.FotoService;

@RestController
@RequestMapping("/foto")
public class FotoController {

	@Autowired
	private FotoService fotoService;
	
	@PutMapping("{id}")
	public Foto computarVoto(@PathVariable("id") Integer id) {
		return fotoService.computarVoto(id);
	}
	
	@GetMapping("{id}")
	public List<Foto> getFotosDeVotacao(@PathVariable("id") Integer id) {
		return fotoService.getFotosDeVotacao(id);
    }
    
    @PostMapping()
    public Foto saveFoto(@RequestBody Foto foto) {
        return this.fotoService.save(foto);
    }
}