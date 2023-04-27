package com.gustavoorelio.beautytime.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "usuario")
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private String cpf;

    private String telefone;

    private String endereco;

    private String numero;

    private String cep;

    private String bairro;

    private String email;

    private String senha;

    private Integer codigoAcesso;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCodigo;

    private Boolean usuarioAtivo;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

    private String fotoUsuario;

    private Long codigoEmpresa;
}
