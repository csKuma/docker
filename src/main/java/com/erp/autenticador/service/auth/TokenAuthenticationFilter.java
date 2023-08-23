package com.erp.autenticador.service.auth;

import com.erp.autenticador.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private final UsuarioRepository usuarioRepository;

    public TokenAuthenticationFilter(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

//    public TokenAuthenticationFilter(TokenService autenticacaoService) {
//        this.tokenService = autenticacaoService;
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.getToken(request);
        if (Objects.nonNull(token)) {
            var login = tokenService.validarToken(token);
            UserDetails user = usuarioRepository.findByUsuario(login);
            var autentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(autentication);
        }
        filterChain.doFilter(request, response);
    }

    private String getToken(HttpServletRequest request) {
        var token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.replace("Bearer ","");
    }

//    private String getToken(HttpServletRequest request) {
//        String token = request.getHeader("Authorization");
//        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")){
//            return null;
//        }
//        return token.substring(7, token.length());
//    }
//    private void autenticar(String token) {
//        UserDetails usuario = tokenService.getUsuario(token);
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null,usuario.getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//    }
}
