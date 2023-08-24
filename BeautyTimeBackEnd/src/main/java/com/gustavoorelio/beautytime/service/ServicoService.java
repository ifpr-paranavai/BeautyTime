package com.gustavoorelio.beautytime.service;

import com.gustavoorelio.beautytime.model.Servico;
import com.gustavoorelio.beautytime.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    public List<Servico> buscarTodos() {
        return servicoRepository.findAll();
    }

    public Servico buscarPorId(Long id) {
        return servicoRepository.findById(id).get();
    }

    public Servico inserir(Servico servico) {
        Servico servicoNovo = servicoRepository.saveAndFlush(servico);
        return servicoNovo;
    }

    public Servico alterar(Servico servico) {
        return servicoRepository.saveAndFlush(servico);
    }

    public void excluir(Long id) {
        Servico servico = servicoRepository.findById(id).get();
        servicoRepository.delete(servico);
    }
}