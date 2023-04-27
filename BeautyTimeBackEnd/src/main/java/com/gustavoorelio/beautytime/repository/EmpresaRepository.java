package com.gustavoorelio.beautytime.repository;

import com.gustavoorelio.beautytime.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}