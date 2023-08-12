//package com.erp.autenticador.config;
//
//import com.erp.autenticador.service.auth.TokenAuthenticationFilter;
//import com.erp.autenticador.service.auth.TokenService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.CorsConfigurationSource;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
//
//
//import javax.crypto.SecretKey;
//import javax.crypto.spec.SecretKeySpec;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.Collections;
//import java.util.List;
//import java.util.stream.Collectors;
//
//
//@Configuration
//@EnableWebSecurity
//public class ResourceServerConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private TokenService tokenService;
//    private final PasswordEncoder passwordEncoder;
//
//    public ResourceServerConfig(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .addFilterBefore(new TokenAuthenticationFilter(tokenService), OAuth2ResourceServerConfigurer.class)
//                .authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/login/**").permitAll()
//                .anyRequest().permitAll()
//                .and()
//                .csrf().disable()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().cors().configurationSource(corsConfigurationSource())
//                .and().oauth2ResourceServer()
//                .jwt();// opaqueToken significa token opaco. jwt() significa token transparente        .jwtAuthenticationConverter(jwtAuthenticationConverter());
//    }
//
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.setAllowCredentials(true);
//        //corsConfiguration.setAllowedOrigins(Collections.singletonList("*"));
//        corsConfiguration.addAllowedOriginPattern("*");
//        corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
//        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT"));
//        source.registerCorsConfiguration("/**", corsConfiguration);
//        return source;
//    }
//
//    /*
//    O TOKEN JWT QUE É RECEBIDO DO AUTHORIZATION SERVER VEM COM UMA LISTA
//    DE [AUTHORITIES]. EU PRECISO PEGAR ESSA LISTA DE AUTHORITIES E CONVERTER
//    ELAS PARA UMA LISTA DE GRANT_AUTHORITIES PRA PODER USAR NO CONTORLE DE ACESSO DA API
//     */
//    private JwtAuthenticationConverter jwtAuthenticationConverter() {
//        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
//
//        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
//            List<String> authorities = jwt.getClaimAsStringList("authorities");
//
//            if (authorities == null) {
//                authorities = Collections.emptyList();
//            }
//
//            //PEGAR OS ESCOPOS DO TOKEN JWT.
//            //OS ESCOPOS TBM VAO SER TRATADOS COMO AUTHORITIES. ESSE CÓDIGO PEGA A LISTA DE ESCOPOS
//            //E CONCATENA COM A LISTA DE AUTHORITIES PARA PODEREM SER USADOS NA HORA DE DETERMINAR AS PERMISSOES DOS ENDPOINTS
//            JwtGrantedAuthoritiesConverter scopesAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
//            Collection<GrantedAuthority> grantedAuthorities = scopesAuthoritiesConverter.convert(jwt);
//
//            grantedAuthorities.addAll(authorities.stream().map(SimpleGrantedAuthority::new)
//                    .collect(Collectors.toList()));
//
//            return grantedAuthorities;
//
//        });
//
//        return jwtAuthenticationConverter;
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/api/**",
//                "/configuration/**", "/swagger-ui.html", "/webjars/**", "/api/swagger-ui.html");
//    }
//
////    /*
////    * CONFIGURAÇÃO PARA FAZER O SERVIDOR DE RECURSOS RECEBER UM TOKEN TRANSPARENTE
////    * E FAZER A VALIDAÇÃO SEM PRECISAR ENVIAR UMA REQUISICAO PARA O SERVIDOR DE AUTORIZACAO.
////    * O PRÓPRIO SERVIDOR DE RECURSO VERIFICA SE O TOKEN É VÁLIDO!
////    * */
//
//    @Bean
//    public JwtDecoder jwtDecoder() {
//        SecretKey secretKey = new SecretKeySpec("nNnrAgTqQMhn37mk$vMrxj!Y180&ACJOQ#n8S3mTRP#DYhkTDbZcu1&NFkobkHTMQ51GIbl1Lpk&5Kl@Cx#!lvJ4nUOBOY1o2bM#".getBytes(), "HmacSHA256");
//
//        return NimbusJwtDecoder.withSecretKey(secretKey).build();
//    }
//
//    //APENAS PARA SER USADA COMO DEPENDENCIA NO ARQUIVO DO AUTHORIZATION SERVER.
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManager() throws Exception {
//        return super.authenticationManager();
//    }
//
//    // É OBRIGATÓRIO PARA PODER FAZER A REQUISIÇÃO COM O REFRESH TOKEN
//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        return super.userDetailsService();
//    }
//}
