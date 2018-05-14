package com.lucas.pickapic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucas.pickapic.resource.FotoRepository;

@Service
public class FotoService {

	@Autowired
	FotoRepository fotoRepository;

}
