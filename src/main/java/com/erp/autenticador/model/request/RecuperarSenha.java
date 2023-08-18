package com.erp.autenticador.model.request;

public class RecuperarSenha {
    private  String email;
    private String cpf;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
