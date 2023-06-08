package com.erp.autenticador.config;

import com.erp.autenticador.model.Usuario;
import com.erp.autenticador.model.request.UsuarioRequest;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);

//        modelMapper.createTypeMap(UsuarioRequest.class, Usuario.class)
//                .addMapping(UsuarioRequest::nome, Usuario::setNome)
//                .addMapping(UsuarioRequest::cpfCnpj, Usuario::setCpfCnpj)
//                .addMapping(UsuarioRequest::usuario, Usuario::setUsuario)
//                .addMapping(UsuarioRequest::email, Usuario::setEmail)
//                .addMapping(UsuarioRequest::telefone, Usuario::setTelefone);

        return modelMapper;
    }
}
