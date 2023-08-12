package com.erp.autenticador.model.response;

import com.erp.autenticador.model.Usuario;

import java.util.List;
import java.util.UUID;

public class TokenDTO {
    private String access_token;
    //    private String token_type="Bearer"; // tirar
    private List<ModuloDTO> modulos;  // trazer somente o id
    private UUID usuario;
    private String nome;
    private String perfil;
    private Boolean primeiroacesso; //mudar pra primeiro acesso

    @Deprecated
    public TokenDTO() {
    }

    public TokenDTO(String access_token, Usuario usuario, String perfil, List<ModuloDTO> modulos, Boolean primeiroacesso) {
        this.access_token = access_token;
//        this.token_type = "Bearer";
        this.usuario = usuario.getId();
        this.perfil = perfil;
        this.nome = usuario.getNome();
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

    public List<ModuloDTO> getModulos() {
        return modulos;
    }

    public void setModulos(List<ModuloDTO> modulos) {
        this.modulos = modulos;
    }

    public Boolean getPrimeiroacesso() {
        return primeiroacesso;
    }

    public void setPrimeiroacesso(Boolean primeiroacesso) {
        this.primeiroacesso = primeiroacesso;
    }
}
