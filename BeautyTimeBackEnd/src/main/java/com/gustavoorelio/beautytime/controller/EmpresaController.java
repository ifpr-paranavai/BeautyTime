package com.gustavoorelio.beautytime.controller;

import com.gustavoorelio.beautytime.model.Empresa;
import com.gustavoorelio.beautytime.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beautytime/empresa")
@CrossOrigin
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping(value = "/")
    @CrossOrigin("http://localhost:3000")
    public List<Empresa> buscarTodos() {
        return empresaService.buscarTodos();
    }

    @PostMapping(value = "/")
    @CrossOrigin("http://localhost:3000")
    public Empresa inserir(@RequestBody Empresa empresa) {
        return empresaService.inserir(empresa);
    }

    @PutMapping(value = "/")
    @CrossOrigin("http://localhost:3000")
    public Empresa alterar(@RequestBody Empresa empresa) {
        return empresaService.alterar(empresa);
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id) {
        empresaService.excluir(id);
        return ResponseEntity.ok().build();
    }
}

