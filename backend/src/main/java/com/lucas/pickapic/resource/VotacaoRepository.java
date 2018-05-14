package com.lucas.pickapic.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lucas.pickapic.model.Votacao;

@Repository
public interface VotacaoRepository extends CrudRepository<Votacao, Integer> {

		@Query("SELECT v FROM Votacao v "
			+ "JOIN Foto f "
			+ "ON f.votacao.id = v.id "
			+ "WHERE v.usuario.id != ?1")
	Optional<List<Votacao>> getVotacoes(Integer idUsuario);

}
