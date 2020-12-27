package com.informatorio.springblog.repository;

import com.informatorio.springblog.domain.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Usuario getById(Long id);

    List<Usuario> getByCiudad(String ciudad);

}
