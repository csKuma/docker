package com.erp.autenticador.model.exception;

public class UsuarioDesativadoException extends RuntimeException {
    public UsuarioDesativadoException(String message) {
        super(message);
    }
}
