package com.gustavoorelio.beautytime.controller;

import com.gustavoorelio.beautytime.model.Servico;
import com.gustavoorelio.beautytime.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beautytime/servico")
@CrossOrigin
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @GetMapping("/")
    @CrossOrigin("http://localhost:3000")
    public List<Servico> buscarTodos() {
        return servicoService.buscarTodos();
    }

    @PostMapping("/")
    @CrossOrigin("http://localhost:3000")
    public Servico inserir(@RequestBody Servico servico) {
        return servicoService.inserir(servico);
    }

    @PutMapping("/")
    @CrossOrigin("http://localhost:3000")
    public Servico alterar(@RequestBody Servico servico) {
        return servicoService.alterar(servico);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        servicoService.excluir(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Servico> buscarPorId(@PathVariable("id") Long id) {

        return ResponseEntity.ok(servicoService.buscarPorId(id));
    }

}
