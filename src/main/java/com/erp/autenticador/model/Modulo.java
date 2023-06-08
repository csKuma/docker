package com.erp.autenticador.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "erp04_modulo")
@SequenceGenerator(name = "erp04_modulo_erp04_cod_modulo_seq", sequenceName = "erp04_modulo_erp04_cod_modulo_seq", initialValue = 1, allocationSize = 1)

public class Modulo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "erp04_modulo_erp04_cod_modulo_seq")
    @Column(name = "erp04_cod_modulo")
    private Long id;
    @Column(name = "erp04_descricao")
    private String descricao;
    @Column(name = "erp03_data_cadastro")
    private LocalDate dataCadastro;
    @Column(name = "erp03_data_exclusao")
    private LocalDate dataExclusao;
    @Column(name = "erp03_ativo")
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

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDate getDataExclusao() {
        return dataExclusao;
    }

    public void setDataExclusao(LocalDate dataExclusao) {
        this.dataExclusao = dataExclusao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
