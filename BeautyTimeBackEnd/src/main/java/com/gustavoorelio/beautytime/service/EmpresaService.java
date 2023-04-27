package com.gustavoorelio.beautytime.service;

import com.gustavoorelio.beautytime.model.Empresa;
import com.gustavoorelio.beautytime.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository cidadeRepository;

    public List<Empresa> buscarTodos() {
        return cidadeRepository.findAll();
    }

    public Empresa inserir(Empresa cidade) {
        cidade.setDataCadastro(new Date());
        Empresa cidadeNovo = cidadeRepository.saveAndFlush(cidade);
        return cidadeNovo;
    }

    public Empresa alterar(Empresa cidade) {
        cidade.setDataCadastro(new Date());
        return cidadeRepository.saveAndFlush(cidade);
    }

    public void excuir(Long id) {
        Empresa cidade = cidadeRepository.findById(id).get();
        cidadeRepository.delete(cidade);
    }
}
