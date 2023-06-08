package com.erp.autenticador.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "erp02_perfil")
@SequenceGenerator(name = "erp02_perfil_erp02_cod_perfil_seq", sequenceName = "erp02_perfil_erp02_cod_perfil_seq", initialValue = 1, allocationSize = 1)

public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "erp02_perfil_erp02_cod_perfil_seq")
    @Column(name = "erp02_cod_perfil")
    private Long id;
    @Column(name = "erp02_descricao")
    private String descricao;
    @Column(name = "erp02_nome")
    private String nome;
    @Column(name = "erp02_ativo")
    private Boolean ativo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
