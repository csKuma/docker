package com.erp.autenticador.model.request;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;

public record UsuarioRequest(
        String nome,
        String cpfCnpj,
        @Email
        String email,
        String telefone,
        String usuario,
        String senha
        ) {
}
