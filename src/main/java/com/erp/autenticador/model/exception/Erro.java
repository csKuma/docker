package com.erp.autenticador.model.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Erro {
    private String titulo;
    private Integer status;

    private String mensagemUsuario;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<CampoErro> erros = new ArrayList<>();
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy hh:mm:ss. zzz", timezone = "GMT-0300")
    private Date timestamp;

    public Erro() {
    }

    public Erro(String titulo, Integer status, String msgUser) {
        this.titulo = titulo;
        this.status = status;
        this.mensagemUsuario = msgUser;
        this.timestamp = new Date();
    }

    public Erro(String titulo, Integer status, String mensagemUsuario, List<CampoErro> erros) {
        this.titulo = titulo;
        this.status = status;
        this.mensagemUsuario = mensagemUsuario;
        this.erros = erros;
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

    public String getMensagemUsuario() {
        return mensagemUsuario;
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

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public void setMensagemUsuario(String mensagemUsuario) {
        this.mensagemUsuario = mensagemUsuario;
    }
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public List<CampoErro> getErros() {
        return erros;
    }

    public void setErros(List<CampoErro> erros) {
        this.erros = erros;
    }
}
