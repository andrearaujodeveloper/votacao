package com.associacao.votacao.mapper;

import com.associacao.votacao.dto.UsuarioResponse;
import com.associacao.votacao.model.Usuario;
import org.mapstruct.Mapper;
import org.springframework.security.core.userdetails.UserDetails;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(String login, String senha);

    UsuarioResponse toResponse(Usuario usuario);
}
