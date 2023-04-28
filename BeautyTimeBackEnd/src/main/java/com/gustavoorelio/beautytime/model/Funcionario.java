package com.gustavoorelio.beautytime.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "funcionario")
@Data
@PrimaryKeyJoinColumn(name = "id")
public class Funcionario extends Usuario {

    private String cargo;

    private String salario;

}
