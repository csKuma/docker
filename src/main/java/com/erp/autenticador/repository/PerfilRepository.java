package com.erp.autenticador.repository;

import com.erp.autenticador.model.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil,Long> {
}
