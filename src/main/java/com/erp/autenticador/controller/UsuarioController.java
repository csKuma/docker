package com.erp.autenticador.controller;

import com.erp.autenticador.model.request.UsuarioEdicaoRequest;
import com.erp.autenticador.model.request.UsuarioRequest;
import com.erp.autenticador.model.response.UsuarioResponse;
import com.erp.autenticador.service.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioResponse>> listarUsuariosPaginado(Pageable pageable) {
        return ResponseEntity.ok().body(usuarioService.ListarUsuariosPaginado(pageable));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> criarusuario(@RequestBody @Valid UsuarioRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.criarUsuario(dto));
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<UsuarioResponse> criarusuario(@PathVariable("usuarioId") Long usuarioId) {
        return ResponseEntity.ok().body(usuarioService.buscarUsuarioPorId(usuarioId));
    }
    @PutMapping("/{usuarioId}")
    public ResponseEntity<UsuarioResponse> atualizarUsuario(@PathVariable("usuarioId") Long usuarioId,
                                                            @RequestBody @Valid UsuarioEdicaoRequest dto){
        usuarioService.editarUsuario(usuarioId,dto);
        return ResponseEntity.noContent().build();
    }

}
