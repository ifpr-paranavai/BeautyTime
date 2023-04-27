package com.gustavoorelio.beautytime.repository;

import com.gustavoorelio.beautytime.model.Permissao;
import com.gustavoorelio.beautytime.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByPermissoes(Permissao permissao);
}