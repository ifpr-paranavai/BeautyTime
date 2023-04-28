package com.gustavoorelio.beautytime.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "cliente")
@Data
@PrimaryKeyJoinColumn(name = "id")
public class Cliente extends Usuario {

}
