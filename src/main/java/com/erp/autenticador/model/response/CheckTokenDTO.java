package com.erp.autenticador.model.response;

import com.erp.autenticador.model.Usuario;
import com.erp.autenticador.util.DateControl;
import com.fasterxml.jackson.annotation.JsonFormat;
/*import com.tout.oab.agenda.model.Usuario;
import com.tout.oab.agenda.model.dto.out.PerfilDto;
import com.tout.oab.agenda.util.DateControl;*/

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CheckTokenDTO {

    private UUID usuario;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateControl.DATA_E_HORA_SEPARADA_POR_BARRA, timezone = "GMT-0300")
    private Date expiracao;
    private String nome;
    private Boolean senhaPadrao;
    private String cpf;
//    private List<PerfilDto> prefil;

    public List<ModuloDTO> getModulos() {
        return modulos;
    }

    public void setModulos(List<ModuloDTO> modulos) {
        this.modulos = modulos;
    }

    private List<ModuloDTO> modulos;

    @Deprecated
    public CheckTokenDTO() {
    }

//    public CheckTokenDTO(Usuario usuario, Date expiracao, List<ModuloDTO> modulos) {
//        this.usuario = usuario.getId();
//        this.expiracao = expiracao;
//        this.nome = usuario.getNome();
//        this.cpf= usuario.getCpf();
//        this.senhaPadrao = usuario.getSenhaPadrao();
//        this.modulos = modulos; //TODO: adicionei aqui
//    }
    public CheckTokenDTO(Usuario usuario, Date expiracao/*, List<ModuloDTO> modulos, List<PerfilDto> prefil*/) {
        this.usuario = usuario.getId();
        this.expiracao = expiracao;
        this.nome = usuario.getNome();
        this.cpf= usuario.getCpfCnpj();
        this.senhaPadrao = usuario.getPrimeiroAcesso();
//        this.modulos = modulos;
//        this.prefil=prefil;//TODO: adicionei aqui
    }

    public UUID getUsuario() {
        return usuario;
    }

    public void setUsuario(UUID usuario) {
        this.usuario = usuario;
    }

    public Date getExpiracao() {
        return expiracao;
    }

    public void setExpiracao(Date expiracao) {
        this.expiracao = expiracao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getSenhaPadrao() {
        return senhaPadrao;
    }

    public void setSenhaPadrao(Boolean senhaPadrao) {
        this.senhaPadrao = senhaPadrao;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

//    public List<PerfilDto> getPrefil() {
//        return prefil;
//    }
//
//    public void setPrefil(List<PerfilDto> prefil) {
//        this.prefil = prefil;
//    }
}
