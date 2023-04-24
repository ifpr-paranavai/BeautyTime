package com.gustavoorelio.beautytime.controller;

import com.gustavoorelio.beautytime.model.Estado;
import com.gustavoorelio.beautytime.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping
    public List<Estado> getAllEstados() {
        return estadoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Estado getEstadoById(@PathVariable Long id) {
        return estadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estado não encontrado com o id " + id));
    }

    @PostMapping
    public Estado createEstado(@RequestBody Estado estado) {
        return estadoRepository.save(estado);
    }

    @PutMapping("/{id}")
    public Estado updateEstado(@PathVariable Long id, @RequestBody Estado estadoAtualizado) {
        Estado estado = estadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estado não encontrado com o id " + id));

        estado.setNome(estadoAtualizado.getNome());
        estado.setSigla(estadoAtualizado.getSigla());

        return estadoRepository.save(estado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEstado(@PathVariable Long id) {
        Estado estado = estadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estado não encontrado com o id " + id));

        estadoRepository.delete(estado);

        return ResponseEntity.ok().build();
    }

}

