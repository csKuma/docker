package com.erp.autenticador.model.request;

import javax.validation.constraints.NotBlank;

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
