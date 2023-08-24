package com.erp.autenticador.repository;

import com.erp.autenticador.model.EmpresaModulo;
import com.erp.autenticador.model.Modulo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface EmpresaModuloRepository extends JpaRepository<EmpresaModulo, UUID> {
    @Query("select em from EmpresaModulo em " +
            "inner join Modulo md on md = em.modulo " +
            "where em.empresa=:empresaId " +
            "and md.moduloPai is null " +
            "and (:descricao is null or md.descricao like %:descricao%) " +
            "order by md.descricao")
    List<Modulo> buscarModulosDaEmpresa(UUID empresaId, String descricao);
}