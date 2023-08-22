package com.erp.autenticador.controller;

import com.erp.autenticador.model.exception.UUIDValide;
import com.erp.autenticador.model.response.ModuloResponse;
import com.erp.autenticador.service.ModuloService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modulo")
public class ModuloController {
    private final ModuloService moduloService;

    public ModuloController(ModuloService moduloService) {
        this.moduloService = moduloService;
    }

    @GetMapping
//    @ApiOperation("listar todos os modulos junto com submodulos")
    public ResponseEntity<List<ModuloResponse>> listarModulos(
            @RequestParam(value = "descricao", required = false) String descricao
    ) {
        return ResponseEntity.ok().body(moduloService.listarModulos(descricao));
    }

    @GetMapping("/empresa")
//    @ApiOperation("rota para listar Modulos de uma empresa (em consturção)")
    public ResponseEntity<List<ModuloResponse>> listarModulos(@RequestParam("empresaId") @UUIDValide String empresaId,
                                                              @RequestParam(value = "descricao", required = false) String descricao
    ) {
        return ResponseEntity.ok().body(moduloService.listarModulosDaEmpresa(empresaId, descricao));
    }

}
