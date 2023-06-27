package com.gustavoorelio.beautytime.controller;

import com.gustavoorelio.beautytime.model.Funcionario;
import com.gustavoorelio.beautytime.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beautytime/funcionario")
@CrossOrigin
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping(value = "/")
    @CrossOrigin("http://localhost:3000")
    public List<Funcionario> buscarTodos() {
        return funcionarioService.buscarTodos();
    }

    @PostMapping(value = "/")
    @CrossOrigin("http://localhost:3000")
    public Funcionario inserir(@RequestBody Funcionario funcionario) {
        return funcionarioService.inserir(funcionario);
    }

    @PutMapping(value = "/")
    @CrossOrigin("http://localhost:3000")
    public Funcionario alterar(@RequestBody Funcionario funcionario) {
        return funcionarioService.alterar(funcionario);
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        funcionarioService.excluir(id);
        return ResponseEntity.ok().build();
    }
}
