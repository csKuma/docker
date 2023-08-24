package com.erp.autenticador.repository;

import com.erp.autenticador.model.Usuario;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findByUsuarioIgnoreCase(String usuario);

    @Query("select us from Usuario us where us.id=:id")
    Optional<Usuario> findByUuid(UUID id);

    @Transactional
    @Modifying
    @Query("update Usuario us set us.ultimoAcesso=current date where us=:usuario")
    void setUltimoAcesso(Usuario usuario);

//    @Query("select us from Usuario us where (:email is null or us.email=:email) and (:telefone is null or us.telefone=:telefone)")
//    Optional<Usuario> buscarUsuarioPorEmailOuTelefone(String email, String telefone);


    List<Usuario> findAll(Specification<Usuario> and);


    boolean existsByCpfCnpj(String cpfCnpj);

    boolean existsByEmail(String cpfCnpj);

    boolean existsByTelefone(String cpfCnpj);

    @Query("select us from Usuario us where (:cpf is null or us.cpfCnpj=:cpf) and (:email is null or us.email=:email)")
    Optional<Usuario> buscarUsuarioPorCpfandEmail(String cpf, String email);


    Usuario findByUsuario(String login);
}
