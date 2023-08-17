package com.erp.autenticador.service;

import com.erp.autenticador.model.Modulo;
import com.erp.autenticador.model.response.ModuloResponse;
import com.erp.autenticador.repository.EmpresaModuloRepository;
import com.erp.autenticador.repository.ModuloRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ModuloService {
    private final ModuloRepository moduloRepository;
    private final EmpresaModuloRepository empresaModuloRepository;

    public ModuloService(ModuloRepository moduloRepository,
                         EmpresaModuloRepository empresaModuloRepository) {
        this.moduloRepository = moduloRepository;
        this.empresaModuloRepository = empresaModuloRepository;
    }


    public List<ModuloResponse> listarModulos(String descricao) {
        descricao = Objects.isNull(descricao)?null:descricao.toUpperCase();
        return moduloRepository.buscarModulosPrincipais(descricao)
                .stream().map(m -> montarModuloResponse(m)).collect(Collectors.toList());
    }

    private ModuloResponse montarModuloResponse(Modulo modulo) {
        return new ModuloResponse(modulo.getId(),
                modulo.getDescricao(),
                getSubmodulo(modulo));
    }

    private List<String> getSubmodulo(Modulo modulo) {
        return moduloRepository.buscarSubModolos(modulo);
    }

    public List<ModuloResponse> listarModulosDaEmpresa(String empresaId, String descricao) {
         descricao = Objects.isNull(descricao)?null:descricao.toUpperCase();
        return empresaModuloRepository.buscarModulosDaEmpresa(UUID.fromString(empresaId), descricao)
                .stream().map(md -> montarModuloResponse(md)).collect(Collectors.toList());
    }
}
