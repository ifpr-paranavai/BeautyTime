package com.gustavoorelio.beautytime.repository;

import com.gustavoorelio.beautytime.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
