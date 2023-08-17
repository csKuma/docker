package com.erp.autenticador.model.request;

public record RecuperarSenha(
        String email,
        String telefone,
        String usuario,
        String senha
) {
}
