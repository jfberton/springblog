package com.informatorio.springblog.service;

import com.informatorio.springblog.domain.Post;
import com.informatorio.springblog.domain.Usuario;
import com.informatorio.springblog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PostService {
    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) { this.postRepository = postRepository; }

    public Iterable<Post> findAll() { return postRepository.findAll(); }

    public Iterable<Post> findByTituloContiene(String titulo) { return postRepository.findByTituloContaining(titulo); }

    public Iterable<Post> findByPublicado(boolean publicado) { return postRepository.findByPublicado(publicado); }

    public Post findById(Long id) { return postRepository.getPostById(id); }

    public Post saveOrUpdate(Post post)
    {
        if(post.getId() == null)
        {
            Date date = new Date(System.currentTimeMillis());
            post.setFechaCreacion(date);
        }
        else
        {
            Post postOriginal = postRepository.getPostById(post.getId());
            post.setFechaCreacion(postOriginal.getFechaCreacion());
        }

        return postRepository.save(post);
    }

    public void delete(Long id){
        Post post = postRepository.getPostById(id);
        postRepository.delete(post);
    }

}
