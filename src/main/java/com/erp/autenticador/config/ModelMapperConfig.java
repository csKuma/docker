package com.erp.autenticador.config;

import com.erp.autenticador.model.Modulo;
import com.erp.autenticador.model.Usuario;
import com.erp.autenticador.model.request.ModuloRequest;
import com.erp.autenticador.model.request.UsuarioAlteracaoRequest;
import com.erp.autenticador.model.request.UsuarioRequest;
import com.erp.autenticador.model.response.ModuloResponse;
import com.erp.autenticador.model.response.UsuarioResponse;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);


        modelMapper.createTypeMap(UsuarioRequest.class, Usuario.class)
                .addMapping(UsuarioRequest::getNome, Usuario::setNome)
                .addMapping(UsuarioRequest::getCpf, Usuario::setCpfCnpj)
                .addMapping(UsuarioRequest::getTelefone, Usuario::setTelefone)
                .addMapping(UsuarioRequest::getEmail, Usuario::setEmail)
                .addMapping(UsuarioRequest::getEmail, Usuario::setUsuario);

        modelMapper.createTypeMap(UsuarioAlteracaoRequest.class, Usuario.class)
                .addMapping(UsuarioAlteracaoRequest::getNome, Usuario::setNome)
                .addMapping(UsuarioAlteracaoRequest::getCpf, Usuario::setCpfCnpj)
                .addMapping(UsuarioAlteracaoRequest::getTelefone, Usuario::setTelefone)
                .addMapping(UsuarioAlteracaoRequest::getEmail, Usuario::setEmail)
                .addMapping(UsuarioAlteracaoRequest::getEmail, Usuario::setUsuario);

        modelMapper.createTypeMap(Usuario.class, UsuarioResponse.class)
                .addMapping(Usuario::getNome, UsuarioResponse::setNome)
                .addMapping(Usuario::getCpfCnpj, UsuarioResponse::setCpfCnpj)
                .addMapping(Usuario::getEmail, UsuarioResponse::setEmail);

        modelMapper.createTypeMap(Modulo.class, ModuloResponse.class)
                .addMapping(Modulo::getId, ModuloResponse::setId)
                .addMapping(Modulo::getDescricao, ModuloResponse::setDescricao);

        modelMapper.createTypeMap(ModuloRequest.class, Modulo.class)
                .addMapping(ModuloRequest::getDescricao, Modulo::setDescricao)
                .addMapping(ModuloRequest::getNome, Modulo::setNome);

        return modelMapper;
    }
}
