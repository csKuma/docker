package com.erp.autenticador.model;


import com.erp.autenticador.model.request.UsuarioRequest;

import javax.persistence.*;

@Entity
@Table(name = "erp01_usuario")
@SequenceGenerator(name = "public.erp01_usuario_erp01_cod_usuario_seq", sequenceName = "public.erp01_usuario_erp01_cod_usuario_seq", initialValue = 1, allocationSize = 1)

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "public.erp01_usuario_erp01_cod_usuario_seq")
    @Column(name = "erp01_cod_usuario")
    private Long id;
    @Column(name = "erp01_nome")
    private String nome;
    @Column(name = "erp01_cpfcnpj")
    private String cpfCnpj;
    @Column(name = "erp01_email")
    private String email;
    @Column(name = "erp01_telefone")
    private String telefone;
    @Column(name = "erp01_usuario")
    private String usuario;
    @Column(name = "erp01_senha")
    private String senha;
    @Column(name = "erp01_ativo")
    private Boolean ativo;
    @Column(name = "erp01_senha_padrao")
    private Boolean senhaPadrao;

    public Usuario() {
    }

    public Usuario(String nome,
                   String cpfCnpj,
                   String email,
                   String telefone,
                   String usuario,
                   String senha) {
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.telefone = telefone;
        this.usuario = usuario;
        this.senha = senha;
        this.ativo = true;
        this.senhaPadrao = true;
    }

    public Usuario(UsuarioRequest dto) {
        this.nome = dto.nome();
        this.cpfCnpj = dto.cpfCnpj();
        this.usuario = dto.usuario();
        this.telefone = dto.telefone();
        this.email = dto.email();
        this.ativo = true;
        this.senhaPadrao = true;


    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Boolean getSenhaPadrao() {
        return senhaPadrao;
    }

    public void setSenhaPadrao(Boolean senhaPadrao) {
        this.senhaPadrao = senhaPadrao;
    }
}
