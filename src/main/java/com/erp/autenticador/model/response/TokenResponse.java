package com.erp.autenticador.model.response;

import java.util.List;
import java.util.UUID;

public class TokenResponse {
    private String access_token;
    private UUID usuario;
    private String nome;
    private String perfil;
    private List<ModuloDtoSimples> modulos;
    private Boolean primeiroacesso;

    public TokenResponse() {
    }

    public TokenResponse(String access_token,
                         UUID usuario,
                         String nome,
                         String perfil,
                         List<ModuloDtoSimples> modulos,
                         Boolean primeiroacesso) {
        this.access_token = access_token;
        this.usuario = usuario;
        this.nome = nome;
        this.perfil = perfil;
        this.modulos = modulos;
        this.primeiroacesso = primeiroacesso;
    }

    public String getAccess_token() {
        return access_token;
    }

    public UUID getUsuario() {
        return usuario;
    }

    public String getNome() {
        return nome;
    }

    public String getPerfil() {
        return perfil;
    }

    public List<ModuloDtoSimples> getModulos() {
        return modulos;
    }

    public Boolean getPrimeiroacesso() {
        return primeiroacesso;
    }
}
