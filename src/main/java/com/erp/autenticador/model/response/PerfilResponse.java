package com.erp.autenticador.model.response;

import java.util.UUID;

public record PerfilResponse(
        UUID id,
        String nome,
        String descricao
) {
}
