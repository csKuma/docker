package com.erp.autenticador.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "ur06_permissao")
public class Permissao {
    @Id
    @GeneratedValue
    @Column(name = "ur06_cod_permissao", columnDefinition = "uuid DEFAULT gen_random_uuid()")
    private UUID id;
    @Column(name = "ur06_descricao")
    private String descricao;


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
}
