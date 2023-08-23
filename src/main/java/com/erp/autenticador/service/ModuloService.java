package com.erp.autenticador.service;

import com.erp.autenticador.model.Modulo;
import com.erp.autenticador.model.exception.NotFound;
import com.erp.autenticador.model.request.ModuloRequest;
import com.erp.autenticador.model.response.ModuloResponse;
import com.erp.autenticador.repository.EmpresaModuloRepository;
import com.erp.autenticador.repository.ModuloRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ModuloService {
    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);
    private final ModuloRepository moduloRepository;
    private final EmpresaModuloRepository empresaModuloRepository;
    private final ModelMapper modelMapper;

    public ModuloService(ModuloRepository moduloRepository,
                         EmpresaModuloRepository empresaModuloRepository, ModelMapper modelMapper) {
        this.moduloRepository = moduloRepository;
        this.empresaModuloRepository = empresaModuloRepository;
        this.modelMapper = modelMapper;
    }


    public List<ModuloResponse> listarModulos(String descricao) {
        descricao = Objects.isNull(descricao) ? null : descricao.toUpperCase();
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
        descricao = Objects.isNull(descricao) ? null : descricao.toUpperCase();
        return empresaModuloRepository.buscarModulosDaEmpresa(UUID.fromString(empresaId), descricao)
                .stream().map(md -> montarModuloResponse(md)).collect(Collectors.toList());
    }

    public ModuloResponse criarModulo(ModuloRequest dto) {
        logger.info("Cadastrando usuario");
        Modulo modulo = modelMapper.map(dto, Modulo.class);
        if (Objects.nonNull(dto.getModuloPai()) && !dto.getModuloPai().equals("")) {
            Modulo modPai = buscarModuloPorId(dto.getModuloPai());
            logger.info("setando modulo pai");
            modulo.setModuloPai(modPai);
        }
        return modelMapper.map(salvarModuloNaBase(modulo), ModuloResponse.class);
    }

    private Modulo salvarModuloNaBase(Modulo modulo) {
        logger.info("Cadastrando modulo na base");
        return moduloRepository.saveAndFlush(modulo);
    }

    private Modulo buscarModuloPorId(UUID moduloPai) {
        logger.info("Buscando modulo pelo id: " + moduloPai);
        return moduloRepository.findById(moduloPai).orElseThrow(new NotFound("Modulo não encontrado"));
    }
}
