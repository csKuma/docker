package com.erp.autenticador.repository;

import com.erp.autenticador.model.Modulo;
import com.erp.autenticador.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public interface ModuloRepository extends JpaRepository<Modulo, UUID> {

    @Query("select distinct(d.modulo) from PerfilModulo d " +
            "where d.perfil in (:roles) and d.ativo is true " +
            "and d.modulo.moduloPai is null")
    List<Modulo> findByPerfilIn(List<Perfil> roles);
@Query("select distinct md from Modulo md where md.moduloPai is null " +
        "and (:descricao is null or md.descricao like %:descricao%)" +
        "order by md.descricao ")
    List<Modulo> buscarModulosPrincipais(String descricao);

@Query("select distinct md.descricao from Modulo md where md.moduloPai=:modulo order by md.descricao ")
    List<String> buscarSubModolos(Modulo modulo);

}
