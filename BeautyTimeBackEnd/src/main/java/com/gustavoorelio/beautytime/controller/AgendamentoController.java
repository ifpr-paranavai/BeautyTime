package com.gustavoorelio.beautytime.controller;

import com.gustavoorelio.beautytime.model.Agendamento;
import com.gustavoorelio.beautytime.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/beautytime/agendamento")
@CrossOrigin
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping("/")
    @CrossOrigin("http://localhost:3000")
    public List<Agendamento> buscarTodos(){
        return agendamentoService.buscarTodos();
    }

    @PostMapping("/")
    @CrossOrigin("http://localhost:3000")
    public Agendamento inserir(@RequestBody Agendamento agendamento){
        return agendamentoService.inserir(agendamento);
    }

    @PutMapping("/")
    @CrossOrigin("http://localhost:3000")
    public Agendamento alterar(@RequestBody Agendamento agendamento){
        return agendamentoService.alterar(agendamento);
    }

    @DeleteMapping("/{id}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id){
        agendamentoService.excluir(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @CrossOrigin("http://localhost:3000")
    public ResponseEntity<Agendamento> buscarPorId(@PathVariable("id") Long id){

        return ResponseEntity.ok(agendamentoService.buscarPorId(id));
    }

}
