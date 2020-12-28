package com.informatorio.springblog.domain;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name="post")
public class Post {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "El título no puede estar vacio")
    private String titulo;

    @Column(nullable = false)
    @NotBlank(message = "La descripción no puede estar vacia")
    private String descripcion;

    @Column(nullable = false)
    @NotBlank(message = "El contenido no puede estar vacio")
    private String contenido;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date fechaCreacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="usuario_id", nullable = false)
    @JsonIgnore
    private Usuario autor;

    @Column(nullable = false)
    private boolean publicado;

    public Post() {}

    public Post(@NotBlank(message = "El título no puede estar vacio") String titulo, @NotBlank(message = "La descripción no puede estar vacia") String descripcion, @NotBlank(message = "El contenido no puede estar vacio") String contenido, Date fechaCreacion, Usuario autor, boolean publicado) {
        super();
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.contenido = contenido;
        this.fechaCreacion = fechaCreacion;
        this.autor = autor;
        this.publicado = publicado;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public boolean isPublicado() {
        return publicado;
    }

    public void setPublicado(boolean publicado) {
        this.publicado = publicado;
    }
}
