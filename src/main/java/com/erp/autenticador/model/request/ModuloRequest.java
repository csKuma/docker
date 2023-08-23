package com.erp.autenticador.model.request;

import com.erp.autenticador.util.StringPadronization;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import jakarta.validation.constraints.*;
import java.util.UUID;

public class ModuloRequest {
    @NotNull(message = "nome do modulo não pode ser nulo ou vazio")
    @JsonDeserialize(using = StringPadronization.class)
    private String nome;
    @JsonDeserialize(using = StringPadronization.class)
    private String descricao;
    private UUID moduloPai;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public UUID getModuloPai() {
        return moduloPai;
    }

    public void setModuloPai(UUID moduloPai) {
        this.moduloPai = moduloPai;
    }
}
