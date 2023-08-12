package com.erp.autenticador.controller;

import com.erp.autenticador.model.response.ModuloResponse;
import com.erp.autenticador.service.ModuloService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/modulo")
public class ModuloController {
    private final ModuloService moduloService;

    public ModuloController(ModuloService moduloService) {
        this.moduloService = moduloService;
    }

    @GetMapping
    public ResponseEntity<List<ModuloResponse>> listarModulos(
            @RequestParam(value = "descricao", required = false) String descricao
    ) {
        return ResponseEntity.ok().body(moduloService.listarModulos(descricao));
    }
}
