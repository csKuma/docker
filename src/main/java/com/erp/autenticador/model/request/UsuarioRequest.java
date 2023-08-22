package com.erp.autenticador.model.request;

import com.erp.autenticador.util.StringPadronization;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public class UsuarioRequest {
    @JsonDeserialize(using = StringPadronization.class)
    @NotBlank(message = "nome não pode ser nulo ou vazio")
    private String nome;
    @NotBlank(message = "cpf não deve ser nulo ou vazio")
    private String cpf;
    @Email
    @NotBlank(message = "email não deve ser nulo ou vazio")
    private String email;
    private String telefone;
    private UUID empresa;
    @NotNull
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

    public UUID getEmpresa() {
        return empresa;
    }

    public void setEmpresa(UUID empresa) {
        this.empresa = empresa;
    }

    public UUID getPerfil() {
        return perfil;
    }

    public void setPerfil(UUID perfil) {
        this.perfil = perfil;
    }
}
