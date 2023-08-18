package com.erp.autenticador.model.request;

import com.erp.autenticador.util.StringPadronization;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.UUID;

public class UsuarioAlteracaoRequest {
    @JsonDeserialize(using = StringPadronization.class)
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
    private UUID perfil;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public UUID getPerfil() {
        return perfil;
    }

    public void setPerfil(UUID perfil) {
        this.perfil = perfil;
    }
}
