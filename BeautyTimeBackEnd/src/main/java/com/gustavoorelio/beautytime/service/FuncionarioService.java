package com.gustavoorelio.beautytime.service;

import com.gustavoorelio.beautytime.model.Funcionario;
import com.gustavoorelio.beautytime.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> buscarTodos() {
        return funcionarioRepository.findAll();
    }

    public Funcionario inserir(Funcionario funcionario) {
        Funcionario funcionarioNovo = funcionarioRepository.saveAndFlush(funcionario);
        return funcionarioNovo;
    }

    public Funcionario alterar(Funcionario funcionario) {
        return funcionarioRepository.saveAndFlush(funcionario);
    }

    public void excluir(Long id) {
        Funcionario funcionario = funcionarioRepository.findById(id).get();
        funcionarioRepository.delete(funcionario);
    }
}
