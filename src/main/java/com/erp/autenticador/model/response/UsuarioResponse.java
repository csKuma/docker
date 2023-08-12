package com.erp.autenticador.model.response;

import javax.validation.constraints.Email;
import java.util.UUID;

public record UsuarioResponse(
        UUID id,
        String nome,
        String cpfCnpj,
        String email,
        String telefone,
        String usuario) {
}
