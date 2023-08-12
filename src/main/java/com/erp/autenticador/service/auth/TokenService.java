package com.erp.autenticador.service.auth;

import com.erp.autenticador.model.Usuario;
import com.erp.autenticador.model.exception.NotFound;
import com.erp.autenticador.repository.UsuarioRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TokenService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Value("${jwt.token.key}")
    private String secret;
    @Value("${jwt.expiration}")
    private String expiration;


    //TODO: CRIAR HANDLER PARA ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException
    public boolean isTokenValido(String token) {
        try{
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public UserDetails getUserDatails(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        //return new Usuario(Long.valueOf(claims.getSubject()), (String) claims.get("username"), new HashSet<>((List<String>) claims.get("authorities")));
        return null;
    }

    public Usuario getUsuario(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        var sub = claims.getSubject();
        return usuarioRepository.findById(UUID.fromString(sub))
                .orElseThrow(new NotFound("Usuario não encontrador"));
    }

    public Date getDataExpiracao(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return claims.getExpiration();
    }

    public String gerarToken(Authentication authentication){
        Usuario usuario = (Usuario) authentication.getPrincipal();
        Map<String,Object> claims = new HashMap<>();
        claims.put("usuario",usuario.getUsername());
        claims.put("authorities", usuario.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()));

        return Jwts.builder()
                .setIssuer("API AUTENTICACAO")
                .setSubject(usuario.getId().toString())
                .addClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+Long.parseLong(expiration)))
                .signWith(SignatureAlgorithm.HS256,this.secret)
                .compact();
    }


}
