package com.lucas.pickapic.resource;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.lucas.pickapic.model.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

	@Query("SELECT u from Usuario u WHERE u.facebookId = ?1")
	Usuario getUsuario(String facebookId);

}
