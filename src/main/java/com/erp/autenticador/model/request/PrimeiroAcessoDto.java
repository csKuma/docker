package com.erp.autenticador.model.request;

import jakarta.validation.constraints.*;

public class PrimeiroAcessoDto {
    @NotBlank
    private String cpf;
    @NotBlank
    private String email;

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
