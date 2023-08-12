package com.erp.autenticador.service;

import com.erp.autenticador.model.Modulo;
import com.erp.autenticador.model.response.ModuloResponse;
import com.erp.autenticador.repository.ModuloRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModuloService {
    private final ModuloRepository moduloRepository;

    public ModuloService(ModuloRepository moduloRepository) {
        this.moduloRepository = moduloRepository;
    }


    public List<ModuloResponse> listarModulos(String descricao) {
        return moduloRepository.buscarModulosPrincipais(descricao.toUpperCase()).stream().map(m -> MontarModutoResponse(m)).collect(Collectors.toList());
    }

    private ModuloResponse MontarModutoResponse(Modulo modulo) {
        return new ModuloResponse(modulo.getId(),
                modulo.getDescricao(),
                getSubmodulo(modulo));
    }

    private List<String> getSubmodulo(Modulo modulo) {
        return moduloRepository.buscarSubModolos(modulo);
    }

}
