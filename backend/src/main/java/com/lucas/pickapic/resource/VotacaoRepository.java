package com.lucas.pickapic.resource;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lucas.pickapic.model.Votacao;

@Repository
public interface VotacaoRepository extends CrudRepository<Votacao, Integer> {

	@Query("SELECT v FROM Votacao v WHERE v.usuario.id != ?1")
	List<Votacao> getVotacoes(Integer idUsuario);
	
	@Query("SELECT v FROM Votacao v WHERE v.usuario.id = ?1")
	List<Votacao> getMinhasVotacoes(Integer idUsuario);
}
