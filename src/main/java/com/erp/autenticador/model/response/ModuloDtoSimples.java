package com.erp.autenticador.model.response;

import java.util.List;

public record ModuloDtoSimples(
        String descricao,
        List<String> subModulo
) {


}
