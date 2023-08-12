package com.erp.autenticador.model.request;

import com.erp.autenticador.model.exception.UUIDValide;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public record PerfilUsuarioRequest(
        @NotBlank
        @UUIDValide
        String perfil,
        @NotBlank()
        @UUIDValide
        String usuario
) {
}
