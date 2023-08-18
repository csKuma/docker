package com.erp.autenticador.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class AlterarSenhaRequest {
    @NotNull(message = "id não pode ser vazio ou nulo")
    private UUID id;
    @NotBlank(message = "Senha não pode ser vazia ou nula")
    private String senha;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
