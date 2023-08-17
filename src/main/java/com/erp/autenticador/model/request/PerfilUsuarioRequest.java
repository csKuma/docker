package com.erp.autenticador.model.request;

import com.erp.autenticador.model.exception.UUIDValide;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

public record PerfilUsuarioRequest(
        @NotBlank
        @UUIDValide
        UUID perfil,
        @NotBlank()
        @UUIDValide
        UUID usuario
) {
}
