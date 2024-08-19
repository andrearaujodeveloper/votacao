package com.associacao.votacao.mapper;

import com.associacao.votacao.dto.VotoDTO;
import com.associacao.votacao.dto.VotoResponse;
import com.associacao.votacao.model.Associado;
import com.associacao.votacao.model.Pauta;
import com.associacao.votacao.model.Voto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface VotoMapper {

    @Mapping(target = "id", ignore = true)
    Voto toEntity(VotoDTO voto, Pauta pauta, Associado associado);
    VotoResponse toResponse(Voto voto);
}
