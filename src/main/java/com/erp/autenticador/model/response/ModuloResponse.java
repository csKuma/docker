package com.erp.autenticador.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;
import java.util.List;

public class ModuloResponse {
    private UUID id;
    private String descricao;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<String> submodulo;

    public ModuloResponse() {
    }

    public ModuloResponse(UUID id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public ModuloResponse(UUID id, String descricao, List<String> submodulo) {
        this.id = id;
        this.descricao = descricao;
        this.submodulo = submodulo;
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

    public List<String> getSubmodulo() {
        return submodulo;
    }

    public void setSubmodulo(List<String> submodulo) {
        this.submodulo = submodulo;
    }
}
