package com.erp.autenticador.model.exception;

import java.util.function.Supplier;

public class BadRequest extends RuntimeException implements Supplier<BadRequest> {
    private String mensagem;
    public BadRequest(String message){
        super(message);
        this.mensagem = message;
    }

    @Override
    public BadRequest get() {
        return new BadRequest(mensagem);
    }
}
