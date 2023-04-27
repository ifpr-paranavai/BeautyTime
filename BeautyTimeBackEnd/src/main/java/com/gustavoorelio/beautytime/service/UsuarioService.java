package com.gustavoorelio.beautytime.service;

import com.gustavoorelio.beautytime.model.Permissao;
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

    public List<Usuario> buscarPorPermissao(Permissao permissao) {
        return usuarioRepository.findByPermissoes(permissao);
    }

    public Usuario inserir(Usuario usuario) {
        usuario.setDataCadastro(new Date());
        Usuario usuarioNovo = usuarioRepository.saveAndFlush(usuario);
        return usuarioNovo;
    }

    public Usuario alterar(Usuario usuario) {
        usuario.setDataCadastro(new Date());
        return usuarioRepository.saveAndFlush(usuario);
    }

    public void excuir(Long id) {
        Usuario usuario = usuarioRepository.findById(id).get();
        usuarioRepository.delete(usuario);
    }


}
