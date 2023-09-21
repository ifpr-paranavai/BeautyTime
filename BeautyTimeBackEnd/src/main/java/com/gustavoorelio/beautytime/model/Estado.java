package com.gustavoorelio.beautytime.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "estado")
@Data
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private String uf;

    private Integer ibge;

    private String ddd;

}