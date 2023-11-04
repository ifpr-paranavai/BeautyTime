package com.gustavoorelio.beautytime.service;

import com.gustavoorelio.beautytime.model.Venda;
import com.gustavoorelio.beautytime.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    public List<Venda> buscarTodos() {
        return vendaRepository.findAll();
    }

    public Venda buscarPorId(Long id) {
        return vendaRepository.findById(id).get();
    }

    public Venda inserir(Venda venda) {
        Venda vendaNovo = vendaRepository.saveAndFlush(venda);
        return vendaNovo;
    }

    public Venda alterar(Venda venda) {
        return vendaRepository.saveAndFlush(venda);
    }

    public void excluir(Long id) {
        Venda venda = vendaRepository.findById(id).get();
        vendaRepository.delete(venda);
    }
}