package com.gustavoorelio.beautytime.controller;

import com.gustavoorelio.beautytime.model.Funcionario;
import com.gustavoorelio.beautytime.model.Usuario;
import com.gustavoorelio.beautytime.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beautytime/funcionario")
public class FuncionarioController extends UsuarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping(value = "/")
    public List<Usuario> buscarTodos() {
        return funcionarioService.buscarTodos();
    }

    @PostMapping(value = "/")
    public Funcionario inserir(@RequestBody Funcionario funcionario) {
        return funcionarioService.inserir(funcionario);
    }

    @PutMapping(value = "/")
    public Funcionario alterar(@RequestBody Funcionario funcionario) {
        return funcionarioService.alterar(funcionario);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        funcionarioService.excluir(id);
        return ResponseEntity.ok().build();
    }
}
