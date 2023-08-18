package com.erp.autenticador.model.response;

import java.util.UUID;

public class DtoSimples
{
    private UUID id;
    private String descricao;

    public DtoSimples() {
    }

    public DtoSimples(UUID id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

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
