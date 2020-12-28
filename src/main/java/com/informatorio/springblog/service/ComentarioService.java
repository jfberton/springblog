package com.informatorio.springblog.service;

import com.informatorio.springblog.domain.Comentario;
import com.informatorio.springblog.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Date;

@Service
public class ComentarioService {

    private ComentarioRepository comentarioRepository;

    @Autowired
    public ComentarioService(ComentarioRepository comentarioRepository){this.comentarioRepository = comentarioRepository;}

    public Iterable<Comentario> findAll() { return comentarioRepository.findAll(); }


    public Iterable<Comentario> findPorPost (Long post_id){
        return comentarioRepository.getComentarioByPostId(post_id);
    }

    public Iterable<Comentario> findPorPostLimite(Long post_id, int limite){
        return comentarioRepository.getComentarioByPostIdLimit(post_id, limite);
    }

    public Comentario findById(Long id) { return comentarioRepository.getComentarioById(id); }

    public Comentario saveOrUpdate(Comentario comentario)
    {
        if(comentario.getId() == null)
        {
            Date date = new Date(System.currentTimeMillis());
            comentario.setFechaCreacion(date);
        }
        else
        {
            Comentario comentarioOriginal = comentarioRepository.getComentarioById(comentario.getId());
            comentario.setFechaCreacion(comentarioOriginal.getFechaCreacion());
        }

        return comentarioRepository.save(comentario);
    }

    public void delete(Long id){
        Comentario comentario = comentarioRepository.getComentarioById(id);
        comentarioRepository.delete(comentario);
    }
}
