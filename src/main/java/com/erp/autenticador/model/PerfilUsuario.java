package com.erp.autenticador.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "erp03_perfil_usuario")
@SequenceGenerator(name = "erp03_perfil_usuario_erp03_cod_perfil_usuario_seq", sequenceName = "erp03_perfil_usuario_erp03_cod_perfil_usuario_seq", initialValue = 1, allocationSize = 1)

public class PerfilUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "erp03_perfil_usuario_erp03_cod_perfil_usuario_seq")
    @Column(name = "erp03_cod_perfil_usuario")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "fkerp03erp01_cod_usuario")
    private Usuario modulo;
    @ManyToOne
    @JoinColumn(name = "fkerp03erp02_cod_perfil")
    private Perfil perfil;
    @Column(name = "erp03_data_cadastro")
    private LocalDate dataCadastro;
    @Column(name = "erp03_data_exclusao")
    private LocalDate dataExclusao;

    @Column(name = "erp03_ativo")
    private Boolean ativo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getModulo() {
        return modulo;
    }

    public void setModulo(Usuario modulo) {
        this.modulo = modulo;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDate getDataExclusao() {
        return dataExclusao;
    }

    public void setDataExclusao(LocalDate dataExclusao) {
        this.dataExclusao = dataExclusao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
