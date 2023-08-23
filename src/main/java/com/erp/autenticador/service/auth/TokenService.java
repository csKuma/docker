package com.erp.autenticador.service.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.erp.autenticador.model.Usuario;
import com.erp.autenticador.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
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
//    public boolean isTokenValido(String token) {
//        try{
//            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
//            return true;
//        }catch (Exception e){
//            return false;
//        }
//    }

//    public UserDetails getUserDatails(String token) {
//        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
//        //return new Usuario(Long.valueOf(claims.getSubject()), (String) claims.get("username"), new HashSet<>((List<String>) claims.get("authorities")));
//        return null;
//    }


//    public Date getDataExpiracao(String token) {
//        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
//        return claims.getExpiration();
//    }

//    public String gerarToken(Authentication authentication){
//        Usuario usuario = (Usuario) authentication.getPrincipal();
//        Map<String,Object> claims = new HashMap<>();
//        claims.put("usuario",usuario.getUsername());
//        claims.put("authorities", usuario.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()));
//
//        return Jwts.builder()
//                .setIssuer("API AUTENTICACAO")
//                .setSubject(usuario.getId().toString())
//                .addClaims(claims)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(new Date().getTime()+Long.parseLong(expiration)))
//                .signWith(SignatureAlgorithm.HS256,this.secret)
//                .compact();
//    }


    public String generateToken(Usuario user) {
        try {
            Map<String, Object> claims = new HashMap<>();
            claims.put("usuario", user.getUsername());
            claims.put("authorities", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet()));
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("api-autenticador")
                    .withSubject(user.getId().toString())
                    .withExpiresAt(geraInstantDataExpiracao())
                    .withClaim("usuario", user.getUsername())
                    .withClaim("authorities", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException ex) {
            ex.printStackTrace();
            throw new RuntimeException("error ao gerar o token ", ex);
        }
    }

    private Instant geraInstantDataExpiracao() {
        return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00"));
    }

    public String validarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("api-autenticador")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException ex) {
            return "";
        }
    }

//    public Usuario getUsuario(String token) {
//        var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return usuarioRepository.findById()
//                .orElseThrow(new NotFound("Usuario não encontrador"));
//    }

}
