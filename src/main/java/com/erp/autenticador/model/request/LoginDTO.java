package com.erp.autenticador.model.request;

import com.erp.autenticador.util.StringPadronization;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginDTO{
    private String usuario;
    private String senha;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
