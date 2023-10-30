package com.gustavoorelio.beautytime.service;

import com.gustavoorelio.beautytime.model.Agendamento;
import com.gustavoorelio.beautytime.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public List<Agendamento> buscarTodos() {
        return agendamentoRepository.findAll();
    }

    public Agendamento buscarPorId(Long id) {
        return agendamentoRepository.findById(id).get();
    }

    public Agendamento inserir(Agendamento agendamento) {
        Agendamento agendamentoNovo = agendamentoRepository.saveAndFlush(agendamento);
        return agendamentoNovo;
    }

    public Agendamento alterar(Agendamento agendamento) {
        return agendamentoRepository.saveAndFlush(agendamento);
    }

    public void excluir(Long id) {
        Agendamento agendamento = agendamentoRepository.findById(id).get();
        agendamentoRepository.delete(agendamento);
    }
}