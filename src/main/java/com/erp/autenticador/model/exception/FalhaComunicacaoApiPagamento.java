package com.erp.autenticador.model.exception;

import java.util.function.Supplier;

public class FalhaComunicacaoApiPagamento extends RuntimeException implements Supplier<FalhaComunicacaoApiPagamento> {
    private final String mensagem;
    public FalhaComunicacaoApiPagamento(String message) {
        super(message);
        this.mensagem = message;
    }

    @Override
    public FalhaComunicacaoApiPagamento get() {
        return new FalhaComunicacaoApiPagamento(mensagem);
    }
}
