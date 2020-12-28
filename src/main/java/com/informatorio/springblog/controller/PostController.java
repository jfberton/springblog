package com.informatorio.springblog.controller;


import com.informatorio.springblog.domain.Post;
import com.informatorio.springblog.domain.Usuario;
import com.informatorio.springblog.service.PostService;
import com.informatorio.springblog.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    private PostService postService;
    private UsuarioService usuarioService;

    public PostController(PostService postService, UsuarioService usuarioService) { this.postService = postService; this.usuarioService = usuarioService; }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Post> getAll() {
        return postService.findAll();
    }

    @RequestMapping(value = "/titulo/{titulo}", method = RequestMethod.GET)
    public Iterable<Post> getByTitulo(@PathVariable String titulo) {
        return postService.findByTituloContiene(titulo);
    }

    @RequestMapping(value = "/sinPublicar", method = RequestMethod.GET)
    public Iterable<Post> getByPublicado() {
        return postService.findByPublicado(false);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getPostById(@PathVariable Long id) {
        Post post = postService.findById(id);
        return new ResponseEntity<Post>(post, HttpStatus.OK);
    }

    @PostMapping("/autor/{autor_id}")
    public ResponseEntity<?> addPost(@PathVariable Long autor_id, @Valid @RequestBody Post post) {
        Usuario usuario = usuarioService.findById(autor_id);
        post.setAutor(usuario);
        Post newPost = postService.saveOrUpdate(post);

        return new ResponseEntity<Post>(newPost, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        postService.delete(id);
        return new ResponseEntity<String>("Post Eliminado", HttpStatus.OK);
    }



}
