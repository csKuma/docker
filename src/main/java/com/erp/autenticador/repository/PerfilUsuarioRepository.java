package com.erp.autenticador.repository;

import com.erp.autenticador.model.Perfil;
import com.erp.autenticador.model.PerfilUsuario;
import com.erp.autenticador.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface PerfilUsuarioRepository extends JpaRepository<PerfilUsuario, UUID> {
    @Modifying
    @Query(value = "UPDATE ur04_usuario_perfil SET ur04_data_desabilitacao=now(), ur04_ativo=false WHERE fkur04ur05_cod_usuario =:usuario", nativeQuery = true)
    void desabilitarPerfilAnterior(@Param("usuario") UUID usuario);



    @Query("select pu from PerfilUsuario pu where pu.perfil.id=:perfil and pu.usuario.id=:usuario and pu.ativo is true and pu.dataDesabilitacao is null ")
    Optional<PerfilUsuario> buscarPerfilAnterior(UUID usuario);
}