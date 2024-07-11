package com.associacao.votacao.mapper;

import com.associacao.votacao.dto.VotoResponse;
import com.associacao.votacao.model.Voto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper()
public interface VotoMapper {

    VotoMapper INSTANCE = Mappers.getMapper(VotoMapper.class);


    VotoResponse toResponse(Voto voto);
}
