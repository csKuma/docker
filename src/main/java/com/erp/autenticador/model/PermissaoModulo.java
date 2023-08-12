package com.erp.autenticador.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "ur07_permissao_modulo")
//@SequenceGenerator(name = "erp03_perfil_usuario_erp03_cod_perfil_usuario_seq", sequenceName = "erp03_perfil_usuario_erp03_cod_perfil_usuario_seq", initialValue = 1, allocationSize = 1)

public class PermissaoModulo {
    @Id
    @GeneratedValue
    @Column(name = "ur07_cod_permissao_modulo",columnDefinition = "uuid DEFAULT gen_random_uuid()")
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "fkur07ur06_cod_permissao")
    private Permissao premissao;
    @ManyToOne
    @JoinColumn(name = "fkur07ur03_cod_modulo_perfil")
    private Modulo modulo;
    @Column(name = "ur07_data_cadastro")
    private LocalDate datCadastro;
    @Column(name = "ur07_data_alteracao")
    private LocalDate dataAlteracao;
    @Column(name = "ur07_ativo")
    private Boolean ativo;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Permissao getPremissao() {
        return premissao;
    }

    public void setPremissao(Permissao premissao) {
        this.premissao = premissao;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public LocalDate getDatCadastro() {
        return datCadastro;
    }

    public void setDatCadastro(LocalDate datCadastro) {
        this.datCadastro = datCadastro;
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
