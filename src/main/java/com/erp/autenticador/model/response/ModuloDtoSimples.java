package com.erp.autenticador.model.response;

import java.util.List;

public class ModuloDtoSimples {

    private String descricao;
    private List<String> subModulo;

    public ModuloDtoSimples() {
    }

    public ModuloDtoSimples(String descricao, List<String> subModulo) {
        this.descricao = descricao;
        this.subModulo = subModulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<String> getSubModulo() {
        return subModulo;
    }

    public void setSubModulo(List<String> subModulo) {
        this.subModulo = subModulo;
    }
}
