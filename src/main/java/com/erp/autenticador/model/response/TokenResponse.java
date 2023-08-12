package com.erp.autenticador.model.response;

import java.util.List;
import java.util.UUID;

public record TokenResponse(
        String access_token,
        //     String token_type="Bearer", // tirar
        UUID usuario,
        String nome,
        String perfil,
        List<ModuloDtoSimples> modulos,
        Boolean primeiroacesso //mudar pra primeiro acesso
) {
}
