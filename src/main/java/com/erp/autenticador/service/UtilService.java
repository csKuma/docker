package com.erp.autenticador.service;

import com.erp.autenticador.model.Modulo;
import com.erp.autenticador.model.Perfil;
import com.erp.autenticador.model.response.ModuloResponse;
import com.erp.autenticador.model.response.PerfilResponse;
import com.erp.autenticador.repository.ModuloRepository;
import com.erp.autenticador.repository.PerfilRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UtilService {
    private final PerfilRepository perfilRepository;
    private final ModuloRepository moduloRepository;

    public UtilService(PerfilRepository perfilRepository, ModuloRepository moduloRepository) {
        this.perfilRepository = perfilRepository;
        this.moduloRepository = moduloRepository;
    }

    public List<PerfilResponse> listarPerfils() {
        return perfilRepository.findAll().stream().map(p -> MontarPerfilResponse(p)).collect(Collectors.toList());
    }

    public List<ModuloResponse> listarModulos() {
        return moduloRepository.findAll().stream().map(m -> MontarModutoResponse(m)).collect(Collectors.toList());
    }

    private ModuloResponse MontarModutoResponse(Modulo m) {
        return new ModuloResponse(m.getId(), m.getDescricao());
    }

    private PerfilResponse MontarPerfilResponse(Perfil p) {
        return new PerfilResponse(p.getId(), p.getNome(), p.getDescricao());
    }
}
