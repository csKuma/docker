package com.erp.autenticador.controller;

import com.erp.autenticador.model.response.DtoSimples;
import com.erp.autenticador.model.response.ModuloResponse;
import com.erp.autenticador.model.response.PerfilResponse;
//import com.erp.autenticador.service.UtilService;
import com.erp.autenticador.service.UtilService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/utils")
public class UtilController {

    private final UtilService utilService;

    public UtilController(UtilService utilService) {
        this.utilService = utilService;
    }

    @GetMapping("/perfils")
    public ResponseEntity<List<PerfilResponse>> listarPerfis(){
        return ResponseEntity.ok().body(utilService.listarPerfils());
    }
       @GetMapping("/permissoes")
    public ResponseEntity<List<DtoSimples>> listarPermissoes(){
        return ResponseEntity.ok().body(utilService.listarPermissoes());
    }

}
