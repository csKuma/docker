package com.erp.autenticador.model.request;

import com.erp.autenticador.model.exception.UUIDValide;
import com.erp.autenticador.util.StringPadronization;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

public record UsuarioRequest(
        @JsonDeserialize(using = StringPadronization.class)
        @NotBlank
        String nome,
        @NotBlank
        String cpf,
        @Email
        @NotBlank
        String email,
        @NotBlank
        String telefone,

        UUID empresa,
//        @UUIDValide
//        @NotBlank
        UUID perfil
) {

}
