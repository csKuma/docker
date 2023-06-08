package com.erp.autenticador.model.request;

import javax.validation.constraints.Email;

public record UsuarioEdicaoRequest(
        String nome,
        String cpfCnpj,
        @Email
        String email,
        String telefone,
        String usuario
        ) {
}
