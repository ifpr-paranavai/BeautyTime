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
    private EmpresaRepository empresaRepository;

    public List<Empresa> buscarTodos() {
        return empresaRepository.findAll();
    }

    public Empresa inserir(Empresa empresa) {
        empresa.setDataCadastro(new Date());
        Empresa empresaNovo = empresaRepository.saveAndFlush(empresa);
        return empresaNovo;
    }

    public Empresa alterar(Empresa empresa) {
        empresa.setDataCadastro(new Date());
        return empresaRepository.saveAndFlush(empresa);
    }

    public void excluir(Long id) {
        Empresa empresa = empresaRepository.findById(id).get();
        empresaRepository.delete(empresa);
    }
}
