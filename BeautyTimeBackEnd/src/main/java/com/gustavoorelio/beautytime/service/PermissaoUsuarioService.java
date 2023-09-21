package com.gustavoorelio.beautytime.service;

import com.gustavoorelio.beautytime.model.Permissao;
import com.gustavoorelio.beautytime.model.PermissaoUsuario;
import com.gustavoorelio.beautytime.model.Usuario;
import com.gustavoorelio.beautytime.repository.PermissaoRepository;
import com.gustavoorelio.beautytime.repository.PermissaoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class PermissaoUsuarioService {

    @Autowired
    private PermissaoUsuarioRepository permissaoUsuarioRepository;

    @Autowired
    private PermissaoRepository permissaoRepository;

    public void vincularUsuarioPermissaoCliente(Usuario usuario) {
        List<Permissao> listaPermissao = permissaoRepository.findByNome("cliente");
        if (listaPermissao.size() > 0) {
            PermissaoUsuario permissaoUsuario = new PermissaoUsuario();
            permissaoUsuario.setUsuario(usuario);
            permissaoUsuario.setPermissao(listaPermissao.get(0));
            permissaoUsuario.setDataCriacao(new Date());
            permissaoUsuarioRepository.saveAndFlush(permissaoUsuario);
        }
    }

}