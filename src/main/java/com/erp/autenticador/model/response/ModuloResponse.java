package com.erp.autenticador.model.response;

import java.util.UUID;
import java.util.List;

public record ModuloResponse(
        UUID id,
        String descricao,
        List<String> submodulo
) {
}
