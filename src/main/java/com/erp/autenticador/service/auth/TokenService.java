package com.erp.autenticador.service.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.erp.autenticador.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class TokenService {
    @Value("${jwt.token.key}")
    private String secret;

    public String generateToken(Usuario user) {
        try {
            var t =  user.getRoles().stream().map(r->r.getDescricao()).collect(Collectors.toList());
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("api-autenticador")
                    .withSubject(user.getId().toString())
                    .withExpiresAt(geraInstantDataExpiracao())
                    .withClaim("authorities",t)
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

    public Date getDataExpiracao(String token) {
        var exp = JWT.decode(token).getExpiresAt();
        System.out.println(exp);
        return exp;
    }

}
