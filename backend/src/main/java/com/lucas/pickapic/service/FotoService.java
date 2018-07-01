package com.lucas.pickapic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.pickapic.model.Foto;
import com.lucas.pickapic.resource.FotoRepository;

@Service
public class FotoService {

	@Autowired
	FotoRepository fotoRepository;

	public Foto computarVoto(Integer id) {
		Foto fotoAtualizada = fotoRepository.findById(id).get();
		fotoAtualizada.setVotos(fotoAtualizada.getVotos() + 1);
		return fotoRepository.save(fotoAtualizada);
	}

	public List<Foto> getFotosDeVotacao(Integer id) {
		return fotoRepository.getFotosVotacao(id);
    }
    
    public Foto save(Foto foto) {
        return this.fotoRepository.save(foto);
    }
}
