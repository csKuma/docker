package com.erp.autenticador.model.exception;

public class FalhaSalvamentoArquivoException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public FalhaSalvamentoArquivoException(String message){
        super(message);
    }
}