package com.erp.autenticador.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "ur02_modulo")
public class Modulo {
    @Id
    @GeneratedValue
    @Column(name = "ur02_cod_modulo", columnDefinition = "uuid DEFAULT gen_random_uuid()")
    private UUID id;
    @Column(name = "ur02_descricao")
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "fkur02ur02_modulo_pai")
    private Modulo moduloPai;
    @ManyToOne
    @JoinColumn(name = "fkur02ur01_cod_aplicacao")
    private Aplicacao aplicacao;
    @Column(name = "ur02_icone")
    private String icone;
    @Column(name = "ur02_path")
    private String path;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Modulo getModuloPai() {
        return moduloPai;
    }

    public void setModuloPai(Modulo moduloPai) {
        this.moduloPai = moduloPai;
    }

    public Aplicacao getAplicacao() {
        return aplicacao;
    }

    public void setAplicacao(Aplicacao aplicacao) {
        this.aplicacao = aplicacao;
    }

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
