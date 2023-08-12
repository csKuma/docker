package com.erp.autenticador.config;

import com.erp.autenticador.model.Usuario;
import com.erp.autenticador.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    //TODO: CRIAR HANDLER PARA UsernameNotFoundException
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<Usuario> result = usuarioRepository.findByUsernameIgnoreCase(username);
        Optional<Usuario> result = usuarioRepository.findByUsuarioIgnoreCase(username);
        if(!result.isPresent()) throw new BadCredentialsException("Nome de usuário ou senha invalidos");
//        if(!result.get().getAtivo()) throw new UsuarioDesativadoException("Usuário desativado, por favor, procure a oab para regularizar sua acesso");
        return result.get();
    }

}
