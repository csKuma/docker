package com.erp.autenticador.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "ur10_empresa_modulo")
public class EmpresaModulo {

    @Id
    @GeneratedValue
    @Column(name = "ur10_cod_empresa_modulo",columnDefinition = "uuid DEFAULT gen_random_uuid()")
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "fkur10ur02_cod_modulo")
    private Modulo modulo;
    @Column(name = "ur10_cod_empresa")
    private UUID empresa;
    @Column(name = "ur10_data_cadastro")
    private LocalDate dataCadastro;
    @Column(name = "ur10_data_alteracao")
    private LocalDate dataAlteracao;
    @Column(name = "ur10_ativo")
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

    public UUID getEmpresa() {
        return empresa;
    }

    public void setEmpresa(UUID empresa) {
        this.empresa = empresa;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
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


    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
