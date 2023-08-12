package com.erp.autenticador.controller;

import com.erp.autenticador.model.exception.UUIDValide;
import com.erp.autenticador.model.request.PerfilUsuarioRequest;
import com.erp.autenticador.model.request.UsuarioAlteracaoRequest;
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
import java.util.UUID;

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
    public ResponseEntity<UsuarioResponse> criarusuario(@PathVariable("usuarioId") @UUIDValide String usuarioId) {
        return ResponseEntity.ok().body(usuarioService.buscarUsuarioPorId(usuarioId));
    }

    @PutMapping("/{usuarioId}")
    public ResponseEntity<UsuarioResponse> atualizarUsuario(@PathVariable("usuarioId") String usuarioId,
                                                            @RequestBody @Valid UsuarioAlteracaoRequest dto) {
        usuarioService.editarUsuario(usuarioId, dto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/vincular-perfil")
    public ResponseEntity vincularPerfil(@RequestBody @Valid PerfilUsuarioRequest dto){
        usuarioService.vincularPerfilAoUsuario(dto);
        return ResponseEntity.noContent().build();
    }

}
