package com.gustavoorelio.beautytime.service;

import com.gustavoorelio.beautytime.model.Funcionario;
import com.gustavoorelio.beautytime.model.Usuario;
import com.gustavoorelio.beautytime.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService extends UsuarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Usuario> buscarTodos() {
        return funcionarioRepository.findAll();
    }

    public Funcionario inserir(Funcionario funcionario) {
        return funcionarioRepository.saveAndFlush(funcionario);
    }

    public Funcionario alterar(Funcionario funcionario) {
        return funcionarioRepository.saveAndFlush(funcionario);
    }

    public void excluir(Long id) {
        Funcionario funcionario = (Funcionario) funcionarioRepository.findById(id).get();
        funcionarioRepository.delete(funcionario);
    }
}
