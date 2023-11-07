package com.gustavoorelio.beautytime.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@DiscriminatorValue("F")
@Data
public class Funcionario extends Usuario {

    private Boolean administrador;
}
