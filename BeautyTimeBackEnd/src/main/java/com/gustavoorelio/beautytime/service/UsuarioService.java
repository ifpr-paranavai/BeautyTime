package com.gustavoorelio.beautytime.service;

import com.gustavoorelio.beautytime.model.Usuario;
import com.gustavoorelio.beautytime.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    public Usuario inserir(Usuario objeto) {
        objeto.setDataCriacao(new Date());
        Usuario objetoNovo = usuarioRepository.saveAndFlush(objeto);
        return objetoNovo;
    }

    public Usuario alterar(Usuario objeto) {
        objeto.setDataAtualizacao(new Date());
        return usuarioRepository.saveAndFlush(objeto);
    }

    public void excluir(Long id) {
        Usuario objeto = usuarioRepository.findById(id).get();
        usuarioRepository.delete(objeto);
    }
}