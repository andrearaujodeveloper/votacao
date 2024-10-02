package com.associacao.votacao.mapper;

import com.associacao.votacao.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    Usuario toEntity(String login, String senha);


}
