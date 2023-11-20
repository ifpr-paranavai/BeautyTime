package com.gustavoorelio.beautytime.service;

import com.gustavoorelio.beautytime.model.Cliente;
import com.gustavoorelio.beautytime.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente inserir(Cliente cliente) {
        Cliente clienteNovo = clienteRepository.saveAndFlush(cliente);
        return clienteNovo;
    }

    public Cliente alterar(Cliente cliente) {
        return clienteRepository.saveAndFlush(cliente);
    }

    public void excluir(Long id) {
        Cliente cliente = clienteRepository.findById(id).get();
        clienteRepository.delete(cliente);
    }
}
