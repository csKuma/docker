package com.erp.autenticador.model;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "ur01_aplicacao")
public class Aplicacao {
    @Id
    @GeneratedValue
    @Column(name = "ur01_cod_aplicacao", columnDefinition = "uuid DEFAULT gen_random_uuid()")
    private UUID id;
    @Column(name = "ur01_nome")
    private String nome;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
