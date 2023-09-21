package com.gustavoorelio.beautytime.controller;

import com.gustavoorelio.beautytime.model.Estado;
import com.gustavoorelio.beautytime.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beautytime/estado")
@CrossOrigin
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/")
    @CrossOrigin("http://localhost:3000")
    public List<Estado> buscarTodos() {
        return estadoService.buscarTodos();
    }

    @PostMapping("/")
    @CrossOrigin("http://localhost:3000")
    public Estado inserir(@RequestBody Estado estado) {
        return estadoService.inserir(estado);
    }

    @PutMapping("/")
    @CrossOrigin("http://localhost:3000")
    public Estado alterar(@RequestBody Estado estado) {
        return estadoService.alterar(estado);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        estadoService.excluir(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Estado> buscarPorId(@PathVariable("id") Long id) {

        return ResponseEntity.ok(estadoService.buscarPorId(id));
    }

}
