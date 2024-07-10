package com.associacao.votacao.mapper;

import com.associacao.votacao.dto.AssociadoDTO;
import com.associacao.votacao.dto.AssociadoResponse;
import com.associacao.votacao.model.Associado;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(imports = {java.util.ArrayList.class})
public interface AssociadoMapper {

    AssociadoMapper INSTANCE = Mappers.getMapper(AssociadoMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "ativo", constant = "true")
    @Mapping(target = "votos", expression = "java(new ArrayList<>())")
    Associado toEntity(AssociadoDTO associadoDTO);

    AssociadoResponse toResponse(Associado associado);
}
