package com.erp.autenticador.repository;

import com.erp.autenticador.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findByUsuarioIgnoreCase(String usuario);

    @Query("select us from Usuario us where us.id=:id")
    Optional<Usuario> findByUuid(UUID id);

    @Transactional
    @Modifying
    @Query("update Usuario us set us.ultimoAcesso=now() where us=:usuario")
    void setUltimoAcesso(Usuario usuario);
}
