package com.gustavoorelio.beautytime.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.gustavoorelio.beautytime.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    Usuario findByEmail(String email);

    Usuario findByEmailAndCodigoRecuperacaoSenha(String email, String codigoRecuperacaoSenha);
}