package com.erp.autenticador.util.validacao;

import com.erp.autenticador.model.exception.BadRequest;

import java.util.UUID;

public class ValidacaoUUID {
    public static void validarUUID(String uuid) {
        try {
            UUID uuid1 = UUID.fromString(uuid);
        } catch (IllegalArgumentException ex) {
            throw new BadRequest("id no formato incorreto");
        }
    }
}
