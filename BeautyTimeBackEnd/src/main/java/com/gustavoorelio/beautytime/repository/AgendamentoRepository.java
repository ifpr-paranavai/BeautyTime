package com.gustavoorelio.beautytime.repository;

import com.gustavoorelio.beautytime.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
