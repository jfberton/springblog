package com.informatorio.springblog.service;

import com.informatorio.springblog.domain.Usuario;
import com.informatorio.springblog.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Iterable<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        return usuarioRepository.getById(id);
    }

    public List<Usuario> findByCiudad(String ciudad){
        return usuarioRepository.getByCiudad(ciudad);
    }

    public List<Usuario> findByFechaAlta(Date fechaAlta){
        return usuarioRepository.findByFechaAltaGreaterThan(fechaAlta);
    }

    public Usuario saveOrUpdate(Usuario usuario) {
        if(usuario.getId() == null)
        {
            Date date = new Date(System.currentTimeMillis());
            usuario.setFechaAlta(date);
        }
        else
        {
            Usuario usuarioOriginal = usuarioRepository.getById(usuario.getId());
            usuario.setFechaAlta(usuarioOriginal.getFechaAlta());
        }

        return usuarioRepository.save(usuario);
    }

    public void delete(Long id) {
        Usuario usuario = usuarioRepository.getById(id);
        usuarioRepository.delete(usuario);
    }
}
