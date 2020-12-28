package com.informatorio.springblog.controller;


import com.informatorio.springblog.domain.Comentario;
import com.informatorio.springblog.domain.Post;
import com.informatorio.springblog.domain.Usuario;
import com.informatorio.springblog.service.ComentarioService;
import com.informatorio.springblog.service.PostService;
import com.informatorio.springblog.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;

@RestController
@RequestMapping("/api/v1/comentario")
public class ComentarioController {

    private ComentarioService comentarioService;
    private UsuarioService usuarioService;
    private PostService postService;

    @Autowired
    public ComentarioController(ComentarioService comentarioService, UsuarioService usuarioService, PostService postService) { this.comentarioService = comentarioService; this.usuarioService = usuarioService; this.postService = postService; }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Comentario> getAll() {
        return comentarioService.findAll();
    }

    @RequestMapping(value = "/post/{post_id}", method = RequestMethod.GET)
    public Iterable<Comentario> getByPost(@PathVariable Long post_id) {
        return comentarioService.findPorPost(post_id);
    }

    @RequestMapping(value = "/post/{post_id}/limite/{limite}", method = RequestMethod.GET)
    public Iterable<Comentario> getByPost(@PathVariable Long post_id, @PathVariable int limite) {
        return comentarioService.findPorPostLimite(post_id, limite);
    }

    @PostMapping("/post/{post_id}/autor/{autor_id}")
    public ResponseEntity<?> addPost(@PathVariable Long post_id, @PathVariable Long autor_id, @Valid @RequestBody Comentario comentario) {
        Post post = postService.findById(post_id);
        Usuario usuario = usuarioService.findById(autor_id);
        comentario.setPost(post);
        comentario.setAutorEmail(usuario.getEmail());
        comentario.setAutor(usuario);

        Comentario newComentario = comentarioService.saveOrUpdate(comentario);

        return new ResponseEntity<Comentario>(newComentario, HttpStatus.CREATED);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        comentarioService.delete(id);
        return new ResponseEntity<String>("Post Eliminado", HttpStatus.OK);
    }

}
