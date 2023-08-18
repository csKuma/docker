package com.erp.autenticador.model.response;

import java.util.List;
import java.util.UUID;

public class TokenResponse {
    private String access_token;
    //     String token_type="Bearer", // tirar
    private UUID usuario;
    private String nome;
    private String perfil;
    private List<ModuloDtoSimples> modulos;
    private Boolean primeiroacesso; //mudar pra primeiro acesso

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

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public UUID getUsuario() {
        return usuario;
    }

    public void setUsuario(UUID usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public List<ModuloDtoSimples> getModulos() {
        return modulos;
    }

    public void setModulos(List<ModuloDtoSimples> modulos) {
        this.modulos = modulos;
    }

    public Boolean getPrimeiroacesso() {
        return primeiroacesso;
    }

    public void setPrimeiroacesso(Boolean primeiroacesso) {
        this.primeiroacesso = primeiroacesso;
    }
}
