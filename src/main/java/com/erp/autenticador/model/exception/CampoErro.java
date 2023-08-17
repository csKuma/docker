package com.erp.autenticador.model.exception;

public class CampoErro {
    private String nome;
    private String mensagem;

    public CampoErro() {
    }

    public CampoErro(String nome, String mensagem) {
        this.nome = nome;
        this.mensagem = mensagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
