package com.gustavoorelio.beautytime.repository;

import com.gustavoorelio.beautytime.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
