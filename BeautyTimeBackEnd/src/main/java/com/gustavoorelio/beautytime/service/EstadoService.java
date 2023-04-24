package com.gustavoorelio.beautytime.service;

import com.gustavoorelio.beautytime.model.Estado;
import com.gustavoorelio.beautytime.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> getAllEstados() {
        return estadoRepository.findAll();
    }

    public Estado getEstadoById(Long id) {
        return estadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estado não encontrado com o id " + id));
    }

    public Estado createEstado(Estado estado) {
        return estadoRepository.save(estado);
    }

    public Estado updateEstado(Long id, Estado estadoAtualizado) {
        Estado estado = estadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estado não encontrado com o id " + id));

        estado.setNome(estadoAtualizado.getNome());
        estado.setSigla(estadoAtualizado.getSigla());

        return estadoRepository.save(estado);
    }

    public void deleteEstado(Long id) {
        Estado estado = estadoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Estado não encontrado com o id " + id));

        estadoRepository.delete(estado);
    }

}

