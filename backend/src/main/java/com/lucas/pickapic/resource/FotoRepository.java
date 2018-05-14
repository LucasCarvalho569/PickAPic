package com.lucas.pickapic.resource;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lucas.pickapic.model.Foto;

@Repository
public interface FotoRepository extends CrudRepository<Foto, Integer>{

}
