package com.erp.autenticador.model.response;


import java.util.List;

public class ModuloDTO {
    private String modulo;
    private List<String> funcionalidades;

    @Deprecated
    public ModuloDTO() {
    }
    public ModuloDTO(String modulo) {
        this.modulo = modulo;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }
}
