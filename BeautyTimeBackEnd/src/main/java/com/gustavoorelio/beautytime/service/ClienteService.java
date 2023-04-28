package com.gustavoorelio.beautytime.service;

import com.gustavoorelio.beautytime.model.Cliente;
import com.gustavoorelio.beautytime.model.Usuario;
import com.gustavoorelio.beautytime.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService extends UsuarioService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Usuario> buscarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente inserir(Cliente cliente) {
        return clienteRepository.saveAndFlush(cliente);
    }

    public Cliente alterar(Cliente cliente) {
        return clienteRepository.saveAndFlush(cliente);
    }

    public void excluir(Long id) {
        Cliente cliente = (Cliente) clienteRepository.findById(id).get();
        clienteRepository.delete(cliente);
    }
}
