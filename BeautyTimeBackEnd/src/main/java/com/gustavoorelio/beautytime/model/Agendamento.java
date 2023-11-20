package com.gustavoorelio.beautytime.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "agendamento")
@Data
public class Agendamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "A data e hora do agendamento são obrigatórias")
    @Future(message = "A data e hora do agendamento devem ser futuras")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraAgendamento;

    private String observacao;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

}
