package com.gustavoorelio.beautytime.controller;

import java.util.List;


import com.gustavoorelio.beautytime.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gustavoorelio.beautytime.model.Usuario;

@RestController
@RequestMapping("/beautytime/usuario")
@CrossOrigin
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    @CrossOrigin("http://localhost:3000")
    public List<Usuario> buscarTodos(){
        return usuarioService.buscarTodos();
    }

    @PostMapping("/")
    @CrossOrigin("http://localhost:3000")
    public Usuario inserir(@RequestBody Usuario objeto){
        return usuarioService.inserir(objeto);
    }

    @PutMapping("/")
    @CrossOrigin("http://localhost:3000")
    public Usuario alterar(@RequestBody Usuario objeto){
        return usuarioService.alterar(objeto);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id){
        usuarioService.excluir(id);
        return ResponseEntity.ok().build();
    }

}