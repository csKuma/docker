package com.erp.autenticador.service;

import com.erp.autenticador.model.Perfil;
import com.erp.autenticador.model.PerfilUsuario;
import com.erp.autenticador.model.Usuario;
import com.erp.autenticador.model.exception.CampoErro;
import com.erp.autenticador.model.exception.ConflitoException;
import com.erp.autenticador.model.exception.NotFound;
import com.erp.autenticador.model.request.*;
import com.erp.autenticador.model.response.PrimeiroAcessoResponse;
import com.erp.autenticador.model.response.UsuarioResponse;
import com.erp.autenticador.repository.PerfilRepository;
import com.erp.autenticador.repository.PerfilUsuarioRepository;
import com.erp.autenticador.repository.UsuarioRepository;
import com.erp.autenticador.util.StringPadronization;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.*;

@Service
public class UsuarioService {
    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);


    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;
    private final PerfilUsuarioRepository perfilUsuarioRepository;
    private final ModelMapper modelMapper;

    public UsuarioService(PasswordEncoder passwordEncoder,
                          UsuarioRepository usuarioRepository,
                          PerfilRepository perfilRepository,
                          PerfilUsuarioRepository perfilUsuarioRepository,
                          ModelMapper modelMapper) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
        this.perfilRepository = perfilRepository;
        this.perfilUsuarioRepository = perfilUsuarioRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public UsuarioResponse criarUsuario(UsuarioRequest dto) {
        validarCadastroUsuario(dto);
        Usuario usuario = MontarUsuario(dto);
        Usuario save = salvarUsuario(usuario);
        salvarPerfilUsuario(save, dto.getPerfil());
        return modelMapper.map(save, UsuarioResponse.class);
    }

    private void validarCadastroUsuario(UsuarioRequest dto) {
        List<CampoErro> erros = new ArrayList<>();
        if (usuarioRepository.existsByCpfCnpj(dto.getCpf())) {
            erros.add(new CampoErro("cpfCnpj", "cpfCnpj ja cadastrado"));
        }
        if (usuarioRepository.existsByEmail(dto.getCpf())) {
            erros.add(new CampoErro("email", "email ja cadastrado"));
        }
        if (usuarioRepository.existsByTelefone(dto.getTelefone())) {
            erros.add(new CampoErro("telefone", "telefone ja cadastrado"));
        }
        if (!erros.isEmpty()) throw new ConflitoException("Os dados a seguir estão cadastrados:", erros);
    }

    @Transactional
    public void editarUsuario(String idUsuario, UsuarioAlteracaoRequest dto) {
        Usuario old = usuarioRepository.findById(UUID.fromString(idUsuario)).orElseThrow(new NotFound("Usuario não encontrado"));
        Usuario usuario = modelMapper.map(dto, Usuario.class);
        validaAtualizacao(old, usuario);
        atualizarUsuario(old, usuario);
        alterarPerfil(old, dto.getPerfil());
    }

    public void atualizarUsuario(Usuario usuario, Usuario entrada) {
        try {
            Class<?> clazz = Usuario.class;
            Field[] campos = clazz.getDeclaredFields();
            for (Field us : campos) {
                us.setAccessible(true);
                Object valor = us.get(entrada);
                Boolean b = StringPadronization.verificarSeEString(valor) ? !((String) valor).trim().isEmpty() : true;
                if (Objects.nonNull(valor) && b) {
                    us.set(usuario, valor);
                }
            }

        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }


    }

    public UsuarioResponse buscarUsuarioPorId(UUID idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .map(u -> modelMapper.map(u,UsuarioResponse.class))
                .orElseThrow(new NotFound("Usuario não encontrado"));
    }

    public Page<UsuarioResponse> ListarUsuariosPaginado(Pageable pageable) {
        return usuarioRepository.findAll(pageable).map(u -> modelMapper.map(u,UsuarioResponse.class));
    }

    public PrimeiroAcessoResponse primeiroAcesso(PrimeiroAcessoDto dto) {
        Optional<Usuario> usuario = usuarioRepository.buscarUsuarioPorCpfandEmail(dto.getCpf(), dto.getEmail());
        if (usuario.isPresent()) {
            logger.debug("usuario presente");
            validarUsuarioBloqueado(usuario.get());
            valicarPrimeiroAcesso(usuario.get());
            return new PrimeiroAcessoResponse(usuario.get().getId());
        }
        throw new NotFound("Usuario não encontrado");
    }

    @Transactional
    public void vincularPerfilAoUsuario(PerfilUsuarioRequest dto) {
        Perfil perfil = buscarPerfil(dto.getPerfil());
        Usuario usuario = buacarUsuario(dto.getUsuario());
        alterarPerfilAnterior(perfil, usuario);
    }

    public String resetarSenha(UUID usuarioId) {
        Usuario usuario = buacarUsuario(usuarioId);
        usuario.setSenha(passwordEncoder.encode(usuario.getCpfCnpj()));
        usuario.setPrimeiroAcesso(true);
        salvarUsuario(usuario);
        return "Senha redefinida com sucesso, o usuário poderá acessar o sistema utilizando seu CPF como senha";
    }

    public void alterarSenhaLogado(AlterarSenhaRequest dto) {
        Usuario usuario = buacarUsuario(dto.getId());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setPrimeiroAcesso(false);
        salvarUsuario(usuario);
    }


    public void RecuperarSenha(RecuperarSenha dto) {
        Optional<Usuario> usuario = usuarioRepository.buscarUsuarioPorCpfandEmail(dto.getCpf(), dto.getEmail());
        if (usuario.isPresent()) {
//            usuario.get().setSenha(passwordEncoder.encode(dto.senha()));
            salvarUsuario(usuario.get());
        } else {
            throw new NotFound("usuario não Encontrado");
        }
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

    private void valicarPrimeiroAcesso(Usuario usuario) {
        if (Objects.nonNull(usuario.getPrimeiroAcesso()) && Boolean.FALSE.equals(usuario.getPrimeiroAcesso())) {
            logger.error("usuario com primeiro acesso realizado");
            throw new ConflitoException("Usuario com primeiro acesso ja realizado ");
        }
    }

    private void validarUsuarioBloqueado(Usuario usuario) {
        if (Objects.nonNull(usuario.getBloqueado()) && Boolean.TRUE.equals(usuario.getBloqueado())) {
            System.out.println("usuario Bloqueado");
            logger.error("usuario Bloqueado");
            throw new ConflitoException("Usuario Bloqueado, favor entre em contato com sua empresa para regularizar sintuação");
        }
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

    private void validaAtualizacao(Usuario old, Usuario entrada) {
        List<CampoErro> eros = new ArrayList<>();
        if (Objects.nonNull(entrada.getEmail()) && !old.getEmail().equals(entrada.getEmail())) {
            if (usuarioRepository.existsByEmail(entrada.getCpfCnpj()))
                eros.add(new CampoErro("email", "email ja cadastrado"));
        }
        if (Objects.nonNull(entrada.getCpfCnpj()) && !old.getCpfCnpj().equals(entrada.getCpfCnpj())) {
            if (usuarioRepository.existsByCpfCnpj(entrada.getCpfCnpj()))
                eros.add(new CampoErro("cpf", "cpf ja cadastrado"));
        }
        if (!eros.isEmpty()) throw new ConflitoException("Os dados a seguir estão em uso: ", eros);


    }

    private Usuario montarUsuario(UsuarioAlteracaoRequest dto) {
        return new Usuario(dto);
    }

    private void salvarPerfilUsuario(Usuario usuario, UUID Idperfil) {
        if (!Objects.isNull(Idperfil)) {
            Perfil perfil = perfilRepository.findById(Idperfil).orElseThrow(new NotFound("Perfil nao encontrado"));
            perfilUsuarioRepository.save(new PerfilUsuario(usuario, perfil));
        }
    }
}
