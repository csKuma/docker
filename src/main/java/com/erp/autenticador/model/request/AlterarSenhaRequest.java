package com.erp.autenticador.model.request;

import com.erp.autenticador.model.exception.UUIDValide;

import java.util.UUID;

public record AlterarSenhaRequest(
        @UUIDValide
        UUID id,
        String senha
) {
}
