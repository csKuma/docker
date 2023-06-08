package com.erp.autenticador.model.response;

import javax.validation.constraints.Email;

public record UsuarioResponse(
        Long id,
        String nome,
        String cpfCnpj,
        String email,
        String telefone,
        String usuario) {

}
