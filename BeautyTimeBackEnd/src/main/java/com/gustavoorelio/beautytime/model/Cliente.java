package com.gustavoorelio.beautytime.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@DiscriminatorValue("C")
@Data
public class Cliente extends Usuario {
}
