package com.erp.autenticador.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "erp05_perfil_modulo")
@SequenceGenerator(name = "erp05_perfil_modulo_erp05_cod_perfil_modulo_seq", sequenceName = "erp05_perfil_modulo_erp05_cod_perfil_modulo_seq", initialValue = 1, allocationSize = 1)

public class PerfilModulo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "erp05_perfil_modulo_erp05_cod_perfil_modulo_seq")
    @Column(name = "erp05_cod_perfil_modulo")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "fkerp05erp04_cod_modulo")

    private Modulo modulo;
    @ManyToOne
    @JoinColumn(name = "fkerp05erp02_cod_perfil")
    private Perfil perfil;
    @Column(name = "erp05_data_cadastro")
    private LocalDate dataCadastro;
    @Column(name = "erp05_data_exclusao")
    private LocalDate dataExclusao;

    @Column(name = "erp05_ativo")
    private Boolean ativo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
