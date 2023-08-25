package com.gustavoorelio.beautytime.repository;

import com.gustavoorelio.beautytime.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
