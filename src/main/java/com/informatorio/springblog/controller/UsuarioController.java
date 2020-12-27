package com.informatorio.springblog.controller;


import com.informatorio.springblog.domain.Post;
import com.informatorio.springblog.domain.Usuario;
import com.informatorio.springblog.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Usuario> getAll() {
        return usuarioService.findAll();
    }

    @RequestMapping(value="/ciudad/{ciudad}", method = RequestMethod.GET)
    public Iterable<Usuario> getByCiudad(@PathVariable String ciudad) {
        return usuarioService.findByCiudad(ciudad);
    }

    @RequestMapping(value="/desde/{fecha}", method = RequestMethod.GET)
    public Iterable<Usuario> getFechaAltaDesde(@PathVariable String fecha) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaAlta = formatter.parse(fecha);

        return usuarioService.findByFechaAlta(fechaAlta);
    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id);
        return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> addUsuario(@Valid @RequestBody Usuario usuario) {
        Usuario newUsuario = usuarioService.saveOrUpdate(usuario);

        return new ResponseEntity<Usuario>(newUsuario, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id) {
        usuarioService.delete(id);
        return new ResponseEntity<String>("Usuario Eliminado", HttpStatus.OK);
    }
}
