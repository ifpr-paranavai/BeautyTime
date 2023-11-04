package com.gustavoorelio.beautytime.controller;

import com.gustavoorelio.beautytime.model.Venda;
import com.gustavoorelio.beautytime.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beautytime/venda")
@CrossOrigin
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @GetMapping("/")
    @CrossOrigin("http://localhost:3000")
    public List<Venda> buscarTodos() {
        return vendaService.buscarTodos();
    }

    @PostMapping("/")
    @CrossOrigin("http://localhost:3000")
    public Venda inserir(@RequestBody Venda venda) {
        return vendaService.inserir(venda);
    }

    @PutMapping("/")
    @CrossOrigin("http://localhost:3000")
    public Venda alterar(@RequestBody Venda venda) {
        return vendaService.alterar(venda);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        vendaService.excluir(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Venda> buscarPorId(@PathVariable("id") Long id) {

        return ResponseEntity.ok(vendaService.buscarPorId(id));
    }

}
