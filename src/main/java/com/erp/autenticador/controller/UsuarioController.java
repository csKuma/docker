package com.erp.autenticador.controller;

import com.erp.autenticador.model.exception.UUIDValide;
import com.erp.autenticador.model.request.*;
import com.erp.autenticador.model.response.PrimeiroAcessoResponse;
import com.erp.autenticador.model.response.UsuarioResponse;
import com.erp.autenticador.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
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

    @PostMapping("/primeiro-acesso")
    public ResponseEntity<PrimeiroAcessoResponse> primeiroAcesso(@RequestBody @Valid PrimeiroAcessoDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.primeiroAcesso(dto));
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<UsuarioResponse> buscarUsuarioPorId(@PathVariable("usuarioId") UUID usuarioId) {
        return ResponseEntity.ok().body(usuarioService.buscarUsuarioPorId(usuarioId));
    }

    @PutMapping("/{usuarioId}")
    public ResponseEntity atualizarUsuario(@PathVariable("usuarioId") @UUIDValide String usuarioId,
                                           @RequestBody @Valid UsuarioAlteracaoRequest dto) {
        usuarioService.editarUsuario(usuarioId, dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/resetarSenha/{usuarioId}")
    public ResponseEntity<Void> resetarSenha(@PathVariable("usuarioId") @UUIDValide UUID usuarioId) {
        usuarioService.resetarSenha(usuarioId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/recuperarSenha")
    @ApiOperation("recuperar senha em construção")
    public ResponseEntity recuperarSenha(@RequestBody RecuperarSenha dto) {
        usuarioService.RecuperarSenha(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/alterarSenha")
    public ResponseEntity<Void> atualizar(@RequestBody AlterarSenhaRequest dto) {
        usuarioService.alterarSenhaLogado(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/vincular-perfil")
    public ResponseEntity<Void> vincularPerfil(@RequestBody @Valid PerfilUsuarioRequest dto) {
        usuarioService.vincularPerfilAoUsuario(dto);
        return ResponseEntity.noContent().build();
    }

}
