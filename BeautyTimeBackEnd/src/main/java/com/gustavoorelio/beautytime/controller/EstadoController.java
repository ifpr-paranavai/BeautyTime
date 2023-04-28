package com.gustavoorelio.beautytime.controller;

import com.gustavoorelio.beautytime.model.Estado;
import com.gustavoorelio.beautytime.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beautytime/estado")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @GetMapping(value = "/")
    public List<Estado> buscarTodos() {
        return estadoService.buscarTodos();
    }

    @PostMapping(value = "/")
    public Estado inserir(@RequestBody Estado estado) {
        return estadoService.inserir(estado);
    }

    @PutMapping(value = "/")
    public Estado alterar(@RequestBody Estado estado) {
        return estadoService.alterar(estado);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        estadoService.excluir(id);
        return ResponseEntity.ok().build();
    }
}

