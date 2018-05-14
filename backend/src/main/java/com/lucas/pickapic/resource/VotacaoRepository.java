package com.lucas.pickapic.resource;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lucas.pickapic.model.Votacao;

@Repository
public interface VotacaoRepository extends CrudRepository<Votacao, Integer> {

}
