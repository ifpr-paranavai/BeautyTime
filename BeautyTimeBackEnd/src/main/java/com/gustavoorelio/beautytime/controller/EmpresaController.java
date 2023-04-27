package com.gustavoorelio.beautytime.controller;

import com.gustavoorelio.beautytime.model.Empresa;
import com.gustavoorelio.beautytime.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beautytime/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping(value = "/")
    public List<Empresa> buscarTodos() {
        return empresaService.buscarTodos();
    }

    @PostMapping(value = "/")
    public Empresa inserir(@RequestBody Empresa empresa) {
        return empresaService.inserir(empresa);
    }

    @PutMapping(value = "/")
    public Empresa alterar(@RequestBody Empresa empresa) {
        return empresaService.alterar(empresa);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        empresaService.excuir(id);
        return ResponseEntity.ok().build();
    }
}

