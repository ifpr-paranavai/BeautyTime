package com.gustavoorelio.beautytime.repository;


import com.gustavoorelio.beautytime.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioClienteRepository extends JpaRepository<Usuario, Long> {

}