package com.erp.autenticador.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "ur04_usuario_perfil")
//@SequenceGenerator(name = "erp03_perfil_usuario_erp03_cod_perfil_usuario_seq", sequenceName = "erp03_perfil_usuario_erp03_cod_perfil_usuario_seq", initialValue = 1, allocationSize = 1)

public class PerfilUsuario {
    @Id
    @GeneratedValue
    @Column(name = "ur04_cod_usuario_perfil", columnDefinition = "uuid DEFAULT gen_random_uuid()")
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "fkur04ur05_cod_usuario")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "fkur04ur03_cod_perfil")
    private Perfil perfil;
    @CreationTimestamp
    @Column(name = "ur04_data_habilitacao")
    private LocalDate dataHabilitacao;
    @Column(name = "ur04_data_desabilitacao")
    private LocalDate dataDesabilitacao;

    @Column(name = "ur04_ativo")
    private Boolean ativo;

    @Deprecated
    public PerfilUsuario() {
    }

    public PerfilUsuario(Usuario usuario, Perfil perfil) {
        this.usuario = usuario;
        this.perfil = perfil;
        this.ativo = Boolean.TRUE;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public LocalDate getDataHabilitacao() {
        return dataHabilitacao;
    }

    public void setDataHabilitacao(LocalDate dataHabilitacao) {
        this.dataHabilitacao = dataHabilitacao;
    }

    public LocalDate getDataDesabilitacao() {
        return dataDesabilitacao;
    }

    public void setDataDesabilitacao(LocalDate dataDesabilitacao) {
        this.dataDesabilitacao = dataDesabilitacao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public void desabilitarPerfil() {
        this.ativo = false;
        this.dataDesabilitacao = LocalDate.now();
    }
}
