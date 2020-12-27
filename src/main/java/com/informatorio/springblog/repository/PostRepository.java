package com.informatorio.springblog.repository;

import com.informatorio.springblog.domain.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    Post getPostById(Long id);

    List<Post> getPostsByAutor(Long autor_id);

    List<Post> getPostByTitulo(String titulo);

}
