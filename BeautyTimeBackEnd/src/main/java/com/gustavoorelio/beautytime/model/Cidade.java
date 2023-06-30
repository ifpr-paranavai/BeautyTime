package com.gustavoorelio.beautytime.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cidade")
@Data
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name="uf")
    private Estado uf;
}
