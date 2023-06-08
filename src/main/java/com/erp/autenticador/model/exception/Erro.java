package com.erp.autenticador.model.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Erro {
    private String titulo;
    private Integer status;
    private String excecao;
    private String mensagemUsuario;
    private String mensagemDesenvolvedor;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss. zzz", timezone = "GMT-0300")
    private Date timestamp;

    public Erro() {}

    public Erro(String titulo, Integer status, String excecao, String msgUser, String msgDev) {
        this.titulo = titulo;
        this.status = status;
        this.excecao = excecao;
        this.mensagemUsuario = msgUser;
        this.mensagemDesenvolvedor = msgDev;
        this.timestamp = new Date();
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getExcecao() {
        return excecao;
    }

    public String getMensagemUsuario() {
        return mensagemUsuario;
    }


    public String getMensagemDesenvolvedor() {
        return mensagemDesenvolvedor;
    }


    public Date getTimestamp() {
        return timestamp;
    }

//    public static Erro getErros(FeignException e) throws JsonProcessingException {
//        e.printStackTrace();
//        String json = new String(e.responseBody().get().array(), StandardCharsets.UTF_8);
//        ObjectMapper objectMapper = new ObjectMapper();
//        Erro result = objectMapper.readValue(json, Erro.class);
//        return result;
//    }

}
