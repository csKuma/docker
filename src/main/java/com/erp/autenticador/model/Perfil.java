package com.erp.autenticador.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "ur03_perfil")
//@SequenceGenerator(name = "erp02_perfil_erp02_cod_perfil_seq", sequenceName = "erp02_perfil_erp02_cod_perfil_seq", initialValue = 1, allocationSize = 1)

public class Perfil implements GrantedAuthority {
    @Id
    @GeneratedValue
    @Column(name = "ur03_cod_perfil",columnDefinition = "uuid DEFAULT gen_random_uuid()")
    private UUID id;
    @Column(name = "ur03_descricao")
    private String descricao;
    @Column(name = "ur03_nome")
    private String nome;
    @Column(name = "ur03_ativo")
    private Boolean ativo;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Perfil)) return false;
        Perfil perfil = (Perfil) o;
        return Objects.equals(nome, perfil.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Override
    public String getAuthority() {
        return null;
    }
}
