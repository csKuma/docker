package com.erp.autenticador.service;

import com.erp.autenticador.model.Perfil;
import com.erp.autenticador.model.PerfilUsuario;
import com.erp.autenticador.model.Usuario;
import com.erp.autenticador.model.exception.NotFound;
import com.erp.autenticador.model.request.PerfilUsuarioRequest;
import com.erp.autenticador.model.request.UsuarioAlteracaoRequest;
import com.erp.autenticador.model.request.UsuarioRequest;
import com.erp.autenticador.model.response.UsuarioResponse;
import com.erp.autenticador.repository.PerfilRepository;
import com.erp.autenticador.repository.PerfilUsuarioRepository;
import com.erp.autenticador.repository.UsuarioRepository;

import com.erp.autenticador.util.validacao.ValidacaoUUID;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

@Service
public class UsuarioService {


    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;
    private final PerfilUsuarioRepository perfilUsuarioRepository;

    public UsuarioService(PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository,
                          PerfilRepository perfilRepository,
                          PerfilUsuarioRepository perfilUsuarioRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
        this.perfilRepository = perfilRepository;
        this.perfilUsuarioRepository = perfilUsuarioRepository;
    }

    @Transactional
    public UsuarioResponse criarUsuario(UsuarioRequest dto) {
        Usuario usuario = MontarUsuario(dto);
        Usuario save = usuarioRepository.save(usuario);
        salvarPerfilUsuario(save, dto.perfil());
        return MontarUsuarioResponse(save);
    }

    private void salvarPerfilUsuario(Usuario usuario, String Idperfil) {
        if (!Objects.isNull(Idperfil)) {
            Perfil perfil = perfilRepository.findById(UUID.fromString(Idperfil)).orElseThrow(new NotFound("Perfil nao encontrado"));
            perfilUsuarioRepository.save(new PerfilUsuario(usuario, perfil));
        }
    }

    @Transactional
    public UsuarioResponse editarUsuario(String idUsuario, UsuarioAlteracaoRequest dto) {
        ValidacaoUUID.validarUUID(idUsuario);
        Usuario old = usuarioRepository.findById(UUID.fromString(idUsuario)).orElseThrow(new NotFound("Usuario não encontrado"));
        BeanUtils.copyProperties(dto, old, "id", "senha", "ultimoAcesso", "primeiroAcesso", "dataCadastro");
        alterarPerfil(old, dto.perfil());
        return MontarUsuarioResponse(usuarioRepository.save(old));
    }

    private void alterarPerfil(Usuario usuario, String idPerfil) {
        if (!Objects.isNull(idPerfil)) {
            Perfil perfil = buscarPerfil(idPerfil);
            alterarPerfilAnterior(perfil, usuario);
        }
    }

    private Perfil buscarPerfil(String idPerfil) {
        return perfilRepository.findById(UUID.fromString(idPerfil)).orElseThrow(new NotFound("Perfil não encontrado"));
    }

    public UsuarioResponse buscarUsuarioPorId(String idUsuario) {
        return usuarioRepository.findById(UUID.fromString(idUsuario))
                .map(u -> MontarUsuarioResponse(u))
                .orElseThrow(new NotFound("Usuario não encontrado"));
    }

    public Page<UsuarioResponse> ListarUsuariosPaginado(Pageable pageable) {
        return usuarioRepository.findAll(pageable).map(u -> MontarUsuarioResponse(u));
    }


    private UsuarioResponse MontarUsuarioResponse(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getCpfCnpj(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getUsername());
    }

    private Usuario MontarUsuario(UsuarioRequest dto) {
        return new Usuario(dto.nome(),
                dto.cpfCnpj(),
                dto.email(),
                dto.telefone(),
                dto.usuario(),
                passwordEncoder.encode(dto.senha()));
    }

    @Transactional
    public void vincularPerfilAoUsuario(PerfilUsuarioRequest dto) {
        Perfil perfil = buscarPerfil(dto.perfil());
        Usuario usuario = buacarUsuario(dto);
        alterarPerfilAnterior(perfil, usuario);
    }

    private void alterarPerfilAnterior(Perfil perfil, Usuario usuario) {
        perfilUsuarioRepository.desabilitarPerfilAnterior(usuario.getId());
        perfilUsuarioRepository.save(new PerfilUsuario(usuario, perfil));
    }

    private Usuario buacarUsuario(PerfilUsuarioRequest dto) {
        return usuarioRepository.findById(UUID.fromString(dto.usuario())).orElseThrow(new NotFound("Usuario não encontrado"));
    }
}
