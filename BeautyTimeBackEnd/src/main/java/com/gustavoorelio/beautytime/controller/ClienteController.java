package com.gustavoorelio.beautytime.controller;

import com.gustavoorelio.beautytime.model.Cliente;
import com.gustavoorelio.beautytime.model.Cliente;
import com.gustavoorelio.beautytime.model.Usuario;
import com.gustavoorelio.beautytime.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beautytime/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/")
    public List<Cliente> buscarTodos() {
        return clienteService.buscarTodos();
    }

    @PostMapping(value = "/")
    public Cliente inserir(@RequestBody Cliente cliente) {
        return clienteService.inserir(cliente);
    }

    @PutMapping(value = "/")
    public Cliente alterar(@RequestBody Cliente cliente) {
        return clienteService.alterar(cliente);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        clienteService.excluir(id);
        return ResponseEntity.ok().build();
    }
}
