package com.associacao.votacao.mapper;

import com.associacao.votacao.dto.AssociadoDTO;
import com.associacao.votacao.dto.AssociadoResponse;
import com.associacao.votacao.model.Associado;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AssociadoMapper {

    AssociadoMapper INSTANCE = Mappers.getMapper(AssociadoMapper.class);


    Associado toEntity(AssociadoDTO associadoDTO);

    AssociadoResponse toResponse(Associado associado);
}
