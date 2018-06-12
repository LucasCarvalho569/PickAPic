package com.lucas.pickapic.resource;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lucas.pickapic.model.Foto;

@Repository
public interface FotoRepository extends CrudRepository<Foto, Integer>{
	
	@Query("SELECT DISTINCT f FROM Foto f "
			+ "JOIN FETCH Votacao v "
			+ "ON f.votacao.id = ?1 ")
	List<Foto> getFotosVotacao(Integer id);
}
