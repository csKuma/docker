package com.erp.autenticador.model.request;

import com.erp.autenticador.model.exception.UUIDValide;
import com.erp.autenticador.util.StringPadronization;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public record UsuarioAlteracaoRequest(
        @JsonDeserialize(using = StringPadronization.class)
        @NotBlank
        String nome,
        @NotBlank
        String cpfCnpj,
        @Email
        @NotBlank
        String email,
        @NotBlank
        String telefone,
        @NotBlank
        String usuario,
        @UUIDValide
        @NotBlank
        String perfil
) {

}
