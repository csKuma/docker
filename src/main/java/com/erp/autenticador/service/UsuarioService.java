package com.erp.autenticador.service;

import com.erp.autenticador.model.Usuario;
import com.erp.autenticador.model.exception.NotFound;
import com.erp.autenticador.model.request.UsuarioEdicaoRequest;
import com.erp.autenticador.model.request.UsuarioRequest;
import com.erp.autenticador.model.response.UsuarioResponse;
import com.erp.autenticador.repository.UsuarioRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {


    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioResponse criarUsuario(UsuarioRequest dto) {
        Usuario usuario = MontarUsuario(dto);
        return MontarUsuarioResponse(usuarioRepository.save(usuario));
    }

    public UsuarioResponse editarUsuario(Long idUsuario, UsuarioEdicaoRequest dto){
        Usuario old = usuarioRepository.findById(idUsuario).orElseThrow(new NotFound("Usuario não encontrado"));
        BeanUtils.copyProperties(dto,old,"id","senha","senhaPadrao","ativo");
        return MontarUsuarioResponse(usuarioRepository.save(old));
    }

    public UsuarioResponse buscarUsuarioPorId(Long idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .map(u -> MontarUsuarioResponse(u))
                .orElseThrow(new NotFound("Usuario não encontrado"));
    }

    public Page<UsuarioResponse> ListarUsuariosPaginado(Pageable pageable) {
        return  usuarioRepository.findAll(pageable).map(u -> MontarUsuarioResponse(u));
    }




    private UsuarioResponse MontarUsuarioResponse(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getCpfCnpj(),
                usuario.getEmail(),
                usuario.getTelefone(),
                usuario.getUsuario());
    }

    private Usuario MontarUsuario(UsuarioRequest dto) {

        return new Usuario(dto.nome(),
                dto.cpfCnpj(),
                dto.email(),
                dto.telefone(),
                dto.usuario(),
                passwordEncoder.encode(dto.senha()));
    }


}
