package com.gustavoorelio.beautytime.repository;

import com.gustavoorelio.beautytime.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
