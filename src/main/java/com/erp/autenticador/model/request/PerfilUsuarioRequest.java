package com.erp.autenticador.model.request;

import jakarta.validation.constraints.*;
import java.util.UUID;

public class PerfilUsuarioRequest {
    @NotBlank
    UUID perfil;
    @NotBlank()
    UUID usuario;

    public UUID getPerfil() {
        return perfil;
    }

    public void setPerfil(UUID perfil) {
        this.perfil = perfil;
    }

    public UUID getUsuario() {
        return usuario;
    }

    public void setUsuario(UUID usuario) {
        this.usuario = usuario;
    }
}
