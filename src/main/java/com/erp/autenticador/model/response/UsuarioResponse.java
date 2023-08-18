package com.erp.autenticador.model.response;

import javax.validation.constraints.Email;
import java.util.UUID;

public class UsuarioResponse {
    private UUID id;
    private String nome;
    private String cpfCnpj;
    private String email;
    private String empresa;

    public UsuarioResponse() {
    }

    public UsuarioResponse(UUID id,
                           String nome,
                           String cpfCnpj,
                           String email,
                           String empresa) {
        this.id = id;
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.empresa = empresa;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
}
