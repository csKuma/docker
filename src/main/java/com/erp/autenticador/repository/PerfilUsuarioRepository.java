package com.erp.autenticador.repository;

import com.erp.autenticador.model.PerfilUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface PerfilUsuarioRepository extends JpaRepository<PerfilUsuario, UUID> {
    @Modifying
    @Query(value = "UPDATE ur04_usuario_perfil SET ur04_data_desabilitacao=now(), ur04_ativo=false WHERE fkur04ur05_cod_usuario =:usuario", nativeQuery = true)
    void desabilitarPerfilAnterior(@Param("usuario") UUID usuario);


}