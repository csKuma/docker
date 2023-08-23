package com.erp.autenticador.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "ur08_modulo_perfil")
public class PerfilModulo {

    @Id
    @GeneratedValue
    @Column(name = "ur08_cod_modulo_perfil",columnDefinition = "uuid DEFAULT gen_random_uuid()")
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "fkur08ur02_cod_modulo")
    private Modulo modulo;
    @ManyToOne
    @JoinColumn(name = "fkur08ur03_cod_perfil")
    private Perfil perfil;
    @Column(name = "ur08_data_cadastro")
    private LocalDate dataCadastro;
    @Column(name = "ur08_data_alteracao")
    private LocalDate dataAlteracao;
    @Column(name = "ur08_data_exclusao")
    private LocalDate dataExclusao;
    @Column(name = "ur08_ativo")
    private Boolean ativo;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
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

    public LocalDate getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(LocalDate dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
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
