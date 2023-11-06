package com.gustavoorelio.beautytime.model;

import javax.persistence.*;
import java.util.Date;

public class VendaProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double quantidade;
    private Double valor;

    @OneToMany
    @JoinColumn(name = "idVenda")
    private Venda venda;

    @ManyToOne
    @JoinColumn(name = "idProduto")
    private Produto produto;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;

}
