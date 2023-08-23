package com.erp.autenticador.controller;


import com.erp.autenticador.model.request.ModuloRequest;
import com.erp.autenticador.model.response.ModuloResponse;
import com.erp.autenticador.service.ModuloService;

import org.springframework.http.HttpStatus;
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

    @PostMapping
    public ResponseEntity<ModuloResponse> criarModulo(@RequestBody ModuloRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(moduloService.criarModulo(dto));
    }

    @GetMapping
    public ResponseEntity<List<ModuloResponse>> listarModulos(
            @RequestParam(value = "descricao", required = false) String descricao
    ) {
        return ResponseEntity.ok().body(moduloService.listarModulos(descricao));
    }

    @GetMapping("/empresa")
    public ResponseEntity<List<ModuloResponse>> listarModulos(@RequestParam("empresaId")  String empresaId,
                                                              @RequestParam(value = "descricao", required = false) String descricao
    ) {
        return ResponseEntity.ok().body(moduloService.listarModulosDaEmpresa(empresaId, descricao));
    }


}
