package com.erp.autenticador.service;

import com.erp.autenticador.model.Modulo;
import com.erp.autenticador.model.Usuario;
import com.erp.autenticador.model.exception.TokenInvalidoException;
import com.erp.autenticador.model.request.CheckTokenRequest;
import com.erp.autenticador.model.request.LoginDTO;
import com.erp.autenticador.model.response.*;
import com.erp.autenticador.repository.ModuloRepository;
import com.erp.autenticador.repository.UsuarioRepository;
import com.erp.autenticador.service.auth.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ModuloRepository moduloRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public TokenResponse login(LoginDTO dto) {
        Authentication authenticate = getAuthentication(dto);
        Usuario usuario= (Usuario) authenticate.getPrincipal();
        String token = tokenService.generateToken(usuario);
//        usuarioRepository.setUltimoAcesso(usuario);
        return montarTokenResponse(token, usuario);
    }

    private TokenResponse montarTokenResponse(String token, Usuario usuario) {
        return new TokenResponse(
                token,
                usuario.getId(),
                usuario.getNome(),
                getPerfis(usuario),
                getModulos(usuario),
                usuario.getPrimeiroAcesso());
    }

    private List<ModuloDtoSimples> getModulos(Usuario usuario) {
        var roles = usuario.getRoles();
        System.out.println(roles);
        return moduloRepository.findByPerfilIn(roles)
                .stream().map(f ->
                        new ModuloDtoSimples(
                                f.getDescricao(),
                                getSubModulo(f))
                ).collect(Collectors.toList());
//        return null;
    }

    private List<String> getSubModulo(Modulo f) {
        return moduloRepository.buscarSubModolos(f);
    }

    private String getPerfis(Usuario usuario) {
        var roles = usuario.getRoles();
        return roles.isEmpty() ? "" : roles.get(0).getDescricao();
    }

    private Authentication getAuthentication(LoginDTO dto) {
        try {
            Authentication authenticate = authenticationManager.authenticate(converter(dto));
            return authenticate;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadCredentialsException(e.getMessage());
        }
    }

    public UsernamePasswordAuthenticationToken converter(LoginDTO dto) {
        return new UsernamePasswordAuthenticationToken(dto.getUsuario(), dto.getSenha());
    }

    public CheckTokenDTO checkToken(CheckTokenRequest dto) {

        String token = dto.getToken();
        String s = tokenService.validarToken(token);
        if (Objects.nonNull(s) && !s.isEmpty()) {
            Usuario user = usuarioRepository.findById(UUID.fromString(s)).get();
            return new CheckTokenDTO(user, tokenService.getDataExpiracao(token), getModulos(user), getPerfis(user));
        }
        throw new TokenInvalidoException("Token expirado ou invalido");
    }


}
