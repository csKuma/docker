package com.erp.autenticador.model.response;

import java.util.UUID;

public record DtoSimples(
        UUID id,
        String descricao
) {
}
