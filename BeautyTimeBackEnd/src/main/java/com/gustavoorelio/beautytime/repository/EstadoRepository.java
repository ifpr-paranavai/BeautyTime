package com.gustavoorelio.beautytime.repository;

import com.gustavoorelio.beautytime.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

}
