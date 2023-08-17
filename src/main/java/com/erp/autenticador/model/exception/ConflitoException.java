package com.erp.autenticador.model.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ConflitoException extends RuntimeException implements Supplier<ConflitoException> {
    private String mensagem;
    private List<CampoErro> erros = new ArrayList<>();

    public ConflitoException(String message, List<CampoErro> erros) {
        super(message);
        this.mensagem = message;
        this.erros = erros;
    }

    public List<CampoErro> getErros() {
        return erros;
    }
        @Override
    public ConflitoException get() {
        return new ConflitoException(mensagem, erros);
    }
}
