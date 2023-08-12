package com.erp.autenticador.model;


import com.erp.autenticador.model.request.UsuarioRequest;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "ur05_usuario")
public class Usuario implements UserDetails {

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "ur05_cod_usuario", columnDefinition = "uuid DEFAULT gen_random_uuid()")
    private UUID id ;
    @Column(name = "ur05_nome")
    private String nome;
    @Column(name = "ur05_cpf")
    private String cpfCnpj;
    @Column(name = "ur05_email")
    private String email;
    @Column(name = "ur05_telefone")
    private String telefone;
    @Column(name = "ur05_id")
    private String usuario;
    @Column(name = "ur05_senha")
    private String senha;
    @Column(name = "ur05_resetar_senha")
    private Boolean primeiroAcesso;
    @CreationTimestamp
    @Column(name = "ur05_data_cadastro")
    private LocalDate dataCadastro;
    @Column(name = "ur05_ultimo_acesso")
    private LocalDate ultimoAcesso;
    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER)
    private List<PerfilUsuario> roles;

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
        this.primeiroAcesso = true;
    }

    public Usuario(UsuarioRequest dto) {
        this.nome = dto.nome();
        this.cpfCnpj = dto.cpfCnpj();
        this.usuario = dto.usuario();
        this.telefone = dto.telefone();
        this.email = dto.email();
        this.primeiroAcesso = true;
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
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

    public Boolean getPrimeiroAcesso() {
        return primeiroAcesso;
    }

    public void setPrimeiroAcesso(Boolean primeiroAcesso) {
        this.primeiroAcesso = primeiroAcesso;
    }

    public void setRoles(List<PerfilUsuario> roles) {
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        this.roles.removeIf(r->r.getAtivo() == null || !r.getAtivo());
        return Collections.unmodifiableList(
                roles.stream().map(r->r.getPerfil()).collect(Collectors.toList())
        );
//        return null;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<Perfil> getRoles() {
        this.roles.removeIf(r->!r.getAtivo());
        return roles.stream().map(r->r.getPerfil()).collect(Collectors.toList());
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDate getUltimoAcesso() {
        return ultimoAcesso;
    }

    public void setUltimoAcesso(LocalDate ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }
}
