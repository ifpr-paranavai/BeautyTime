package com.gustavoorelio.beautytime.controller;

import com.gustavoorelio.beautytime.model.Usuario;
import com.gustavoorelio.beautytime.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beautytime/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/")
    public List<Usuario> buscarTodos() {
        return usuarioService.buscarTodos();
    }

    @PostMapping(value = "/")
    public Usuario inserir(@RequestBody Usuario usuario) {
        return usuarioService.inserir(usuario);
    }

    @PutMapping(value = "/")
    public Usuario alterar(@RequestBody Usuario usuario) {
        return usuarioService.alterar(usuario);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        usuarioService.excuir(id);
        return ResponseEntity.ok().build();
    }
}

