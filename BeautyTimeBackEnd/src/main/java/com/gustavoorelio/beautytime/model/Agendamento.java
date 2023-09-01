package com.gustavoorelio.beautytime.model;

import lombok.Data;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "agendamento")
@Data
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date dataAgendamento;

    @Temporal(TemporalType.TIME)
    private Date horarioAgendamento;

    private String observacao;

    @ManyToMany
    @JoinTable(
            name = "agendamento_servico",
            joinColumns = @JoinColumn(name = "agendamento_id"),
            inverseJoinColumns = @JoinColumn(name = "servico_id")
    )
    private List<Servico> servicos;

    @Enumerated(EnumType.STRING)
    private Situacao situacao;

    public enum Situacao {
        AGENDADO, CANCELADO, FINALIZADO
    }

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;
}
