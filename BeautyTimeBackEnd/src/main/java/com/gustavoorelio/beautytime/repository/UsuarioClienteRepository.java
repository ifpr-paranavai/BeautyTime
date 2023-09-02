package com.gustavoorelio.beautytime.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.gustavoorelio.beautytime.model.Usuario;

public interface UsuarioClienteRepository extends JpaRepository<Usuario, Long>{

}