package com.informatorio.springblog.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name="comentario")
public class Comentario {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String autorEmail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="usuario_id", nullable = false)
    @JsonIgnore
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id", nullable = false)
    @JsonIgnore
    private Post post;


    @Column(nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date fechaCreacion;

    @Column(nullable = false, length = 200)
    @NotBlank(message = "El comentario no puede ir vacío")
    private String comentario;

    public Comentario() {

    }

    public Comentario(@NotBlank(message = "El email del autor no debe estar vacío") String autorEmail, Usuario autor, Date fechaCreacion, @NotBlank(message = "El comentario no puede ir vacío") String comentario) {
        super();
        this.autorEmail = autorEmail;
        this.autor = autor;
        this.fechaCreacion = fechaCreacion;
        this.comentario = comentario;
    }

    public Long getId() {
        return id;
    }

    public String getAutorEmail() {
        return autorEmail;
    }

    public void setAutorEmail(String autorEmail) {
        this.autorEmail = autorEmail;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
