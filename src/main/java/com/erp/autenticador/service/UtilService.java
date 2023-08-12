package com.erp.autenticador.service;

import com.erp.autenticador.model.Modulo;
import com.erp.autenticador.model.Perfil;
import com.erp.autenticador.model.Permissao;
import com.erp.autenticador.model.response.DtoSimples;
import com.erp.autenticador.model.response.ModuloResponse;
import com.erp.autenticador.model.response.PerfilResponse;
import com.erp.autenticador.repository.ModuloRepository;
import com.erp.autenticador.repository.PerfilRepository;
import com.erp.autenticador.repository.PermissaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UtilService {
    private final PerfilRepository perfilRepository;
    private final ModuloRepository moduloRepository;
    private final PermissaoRepository permissaoRepository;

    public UtilService(PerfilRepository perfilRepository, ModuloRepository moduloRepository,
                       PermissaoRepository permissaoRepository) {
        this.perfilRepository = perfilRepository;
        this.moduloRepository = moduloRepository;
        this.permissaoRepository = permissaoRepository;
    }

    public List<PerfilResponse> listarPerfils() {
        return perfilRepository.findAll().stream().map(p -> MontarPerfilResponse(p)).collect(Collectors.toList());
    }



    public List<DtoSimples> listarPermissoes() {
        return permissaoRepository.findAll().stream().map(permissao -> montarDtoSimples(permissao)).collect(Collectors.toList());
    }

    private DtoSimples montarDtoSimples(Permissao permissao) {
        return new DtoSimples(permissao.getId(), permissao.getDescricao());
    }



    private PerfilResponse MontarPerfilResponse(Perfil p) {
        return new PerfilResponse(p.getId(), p.getNome(), p.getDescricao());
    }


}
