package com.erp.autenticador.model.response;

import java.util.UUID;

public class PrimeiroAcessoResponse {
    private UUID id;

    public PrimeiroAcessoResponse() {
    }

    public PrimeiroAcessoResponse(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
