package com.informatorio.springblog.repository;

import com.informatorio.springblog.domain.Comentario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository  extends CrudRepository<Comentario, Long> {

    Comentario getComentarioById(Long id);

    List<Comentario> getComentarioByPostId(Long post_id);

    @Query(value="SELECT * FROM Comentario WHERE Post_id = ?1 LIMIT ?2", nativeQuery = true)
    List<Comentario> getComentarioByPostIdLimit(Long post_id, int max);
}
