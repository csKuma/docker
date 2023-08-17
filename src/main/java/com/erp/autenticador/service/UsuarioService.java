package com.erp.autenticador.service;

import com.erp.autenticador.model.Perfil;
import com.erp.autenticador.model.PerfilUsuario;
import com.erp.autenticador.model.Usuario;
import com.erp.autenticador.model.exception.CampoErro;
import com.erp.autenticador.model.exception.ConflitoException;
import com.erp.autenticador.model.exception.NotFound;
import com.erp.autenticador.model.request.*;
import com.erp.autenticador.model.response.UsuarioResponse;
import com.erp.autenticador.repository.PerfilRepository;
import com.erp.autenticador.repository.PerfilUsuarioRepository;
import com.erp.autenticador.repository.UsuarioRepository;

import com.erp.autenticador.repository.spec.UsuarioSpec;
import com.erp.autenticador.util.validacao.ValidacaoUUID;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ClientInfoStatus;
import java.util.*;

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
        validarCadastroUsuario(dto);
        Usuario usuario = MontarUsuario(dto);
        Usuario save = salvarUsuario(usuario);
        salvarPerfilUsuario(save, dto.perfil());
        return MontarUsuarioResponse(save);
    }

    private void validarCadastroUsuario(UsuarioRequest dto) {
        List<CampoErro> erros = new ArrayList<>();
        if (usuarioRepository.existsByCpfCnpj(dto.cpf())) {
            erros.add(new CampoErro("cpfCnpj", "cpfCnpj ja cadastrado"));
        }
        if (usuarioRepository.existsByEmail(dto.email())) {
            erros.add(new CampoErro("email", "email ja cadastrado"));
        }
        if (usuarioRepository.existsByTelefone(dto.telefone())) {
            erros.add(new CampoErro("telefone", "telefone ja cadastrado"));
        }
        if (!erros.isEmpty()) throw new ConflitoException("Os dados a seguir estão cadastrados:", erros);

    }

    private void salvarPerfilUsuario(Usuario usuario, UUID Idperfil) {
        if (!Objects.isNull(Idperfil)) {
            Perfil perfil = perfilRepository.findById(Idperfil).orElseThrow(new NotFound("Perfil nao encontrado"));
            perfilUsuarioRepository.save(new PerfilUsuario(usuario, perfil));
        }
    }

    @Transactional
    public UsuarioResponse editarUsuario(String idUsuario, UsuarioAlteracaoRequest dto) {
        ValidacaoUUID.validarUUID(idUsuario);
        Usuario old = usuarioRepository.findById(UUID.fromString(idUsuario)).orElseThrow(new NotFound("Usuario não encontrado"));
        BeanUtils.copyProperties(dto, old, "id", "senha", "ultimoAcesso", "primeiroAcesso", "dataCadastro");
        alterarPerfil(old, dto.perfil());
        return MontarUsuarioResponse(salvarUsuario(old));
    }

    private void alterarPerfil(Usuario usuario, UUID idPerfil) {
        if (!Objects.isNull(idPerfil)) {
            Perfil perfil = buscarPerfil(idPerfil);
            alterarPerfilAnterior(perfil, usuario);
        }
    }

    private Perfil buscarPerfil(UUID idPerfil) {
        return perfilRepository.findById(idPerfil).orElseThrow(new NotFound("Perfil não encontrado"));
    }

    public UsuarioResponse buscarUsuarioPorId(UUID idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .map(u -> MontarUsuarioResponse(u))
                .orElseThrow(new NotFound("Usuario não encontrado"));
    }

    public Page<UsuarioResponse> ListarUsuariosPaginado(Pageable pageable) {
        return usuarioRepository.findAll(pageable).map(u -> MontarUsuarioResponse(u));
    }

    public void primeiroAcesso(PrimeiroAcessoDto dto) {

    }

    @Transactional
    public void vincularPerfilAoUsuario(PerfilUsuarioRequest dto) {
        Perfil perfil = buscarPerfil(dto.perfil());
        Usuario usuario = buacarUsuario(dto.usuario());
        alterarPerfilAnterior(perfil, usuario);
    }

    public String resetarSenha(UUID usuarioId) {
        Usuario usuario = buacarUsuario(usuarioId);
        usuario.setSenha(passwordEncoder.encode(usuario.getCpfCnpj()));
        usuario.setPrimeiroAcesso(true);
        salvarUsuario(usuario);
        return "Senha redefinida com sucesso, o usuário poderá acessar o sistema utilizando seu CPF como senha";
    }

    public String alterarSenhaLogado(AlterarSenhaRequest dto) {
        Usuario usuario = buacarUsuario(dto.id());
        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        Usuario save = salvarUsuario(usuario);
        return "Senha Alterada Com sucesso";
    }


    private UsuarioResponse MontarUsuarioResponse(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getCpfCnpj(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getUsername(),
                null);
    }

    private Usuario MontarUsuario(UsuarioRequest dto) {
        return new Usuario(dto);
    }

    private void alterarPerfilAnterior(Perfil perfil, Usuario usuario) {
        perfilUsuarioRepository.desabilitarPerfilAnterior(usuario.getId());
        perfilUsuarioRepository.save(new PerfilUsuario(usuario, perfil));
    }

    private Usuario buacarUsuario(UUID usuarioId) {
        return usuarioRepository.findById(usuarioId).orElseThrow(new NotFound("Usuario não encontrado"));
    }


    private Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public UsuarioResponse RecuperarSenha(RecuperarSenha dto) {
        Optional<Usuario> usuario = usuarioRepository.buscarUsuarioPorEmailOuTelefone(dto.email(), dto.telefone());
        if (usuario.isPresent()) {
            usuario.get().setSenha(passwordEncoder.encode(dto.senha()));
            salvarUsuario(usuario.get());
            return MontarUsuarioResponse(usuario.get());
        } else {
            throw new NotFound("usuario não Encontrado");
        }
    }
}
