package com.informatorio.springblog.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;

    @Column(nullable = false)
    @NotBlank(message = "El apellido no puede estar vacio")
    private String apellido;

    @Column(unique = true, nullable = false)
    @Email(message = "Ingrese un email válido")
    private String email;

    @Column(nullable = false)
    @NotBlank(message = "La contraseña no puede estar en blanco")
    private String password;

    @Column(nullable = false)
    @JsonFormat(pattern = "dd-MM-yyyy")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date fechaAlta;

    @Column(nullable = false)
    @NotBlank(message = "La ciudad no puede estar en blanco")
    private String ciudad;

    @Column(nullable = false)
    @NotBlank(message = "La provincia no puede estar en blanco")
    private String provincia;

    @Column(nullable = false)
    @NotBlank(message = "El pais no puede estar en blanco")
    private String país;

    public Usuario() {}

    public Usuario(@NotBlank(message = "El nombre no puede estar vacio") String nombre, @NotBlank(message = "El apellido no puede estar vacio") String apellido, @Email(message = "Ingrese un email válido") String email, @NotBlank(message = "La contraseña no puede estar en blanco") String password, Date fechaAlta, @NotBlank(message = "La ciudad no puede estar en blanco") String ciudad, @NotBlank(message = "La provincia no puede estar en blanco") String provincia, @NotBlank(message = "El pais no puede estar en blanco") String país) {
        super();
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.fechaAlta = fechaAlta;
        this.ciudad = ciudad;
        this.provincia = provincia;
        this.país = país;
    }

    public Long getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPaís() {
        return país;
    }

    public void setPaís(String país) {
        this.país = país;
    }
}
